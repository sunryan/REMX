package com.intalgent.addressbook.wizard;

import com.intalgent.addressbook.AddressBook;
import com.intalgent.addressbook.domain.Contact;

import com.intalgent.sql.DatabaseMetaDateApplication;
import com.intalgent.util.TableXlsWrite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class ContactWizard extends Wizard
{
    private BasicContactPage page1;
    private AddressContactPage page2;
    private ISelection selection;

    public ContactWizard()
    {
        super();
        setNeedsProgressMonitor(true);
    }

    public void addPages()
    {
        page1 = new BasicContactPage(selection);//第一页
        page2 = new AddressContactPage(selection);//第二页
        addPage(page1);
//        addPage(page2);
    }



    /**
     * 点击完成事件
     * @return
     */
    public boolean performFinish()
    {
        Contact contact = new Contact(
                page1.getDriverClassText(),
                page1.getJdbcUrlText(),
                page1.getUserNameText(),
                page1.getPasswordText(),
                page1.getOutUrlText()
        );

        DatabaseMetaDateApplication metaData = null;
        try {
             metaData = new DatabaseMetaDateApplication(contact);
        } catch (SQLException e) {
            page1.updateStatus("数据量连接失败");
            return false;
        } catch (ClassNotFoundException e) {
            page1.updateStatus("驱动不存在");
            return false;
        }

        //校验地址是否存在
        if(page1.getOutUrlText() == null || page1.getOutUrlText().trim().length() == 0){
            page1.updateStatus("填写导出文件");
            return false;
        }

        try {
            String fileName = page1.getJdbcUrlText();
            String outUrl = page1.getOutUrlText();
            String dbName = fileName.substring(fileName.lastIndexOf("/") + 1);
            fileName =  dbName + "结构信息.xls";
            if(outUrl.lastIndexOf("/") == outUrl.length() - 1){
                outUrl = outUrl.substring(0, outUrl.length() - 1);
            }
            String outPath = outUrl + "/" + fileName;
            File file = new File(outUrl);
            if(!file.isDirectory()) {
                file.mkdirs();
            }
            TableXlsWrite write = new TableXlsWrite(dbName, outPath ,metaData);
            write.start();
            contact.setOutUrl(outPath);
        } catch (FileNotFoundException e) {
            page1.updateStatus("导出文件地址错误");
            return false;
        } catch (IOException e) {
            page1.updateStatus("生成文件失败");
            return false;
        } catch (SQLException e) {
            page1.updateStatus("sql 错误");
        } finally {
            if(metaData != null){
                metaData.colseCon();
            }
        }

        //成功连接，导出xls
        AddressBook.addContact(contact);


        return true;
    }

}
