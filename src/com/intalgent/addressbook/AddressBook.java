package com.intalgent.addressbook;

import com.intalgent.addressbook.domain.Contact;
import com.intalgent.addressbook.menubar.AboutAction;
import com.intalgent.addressbook.menubar.ExitAction;
import com.intalgent.addressbook.menubar.NewContactAction;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddressBook extends ApplicationWindow
{
    private static ArrayList contactList;
    private static TableViewer tableViewer;
    private Table table;

    public AddressBook()
    {
        super(null);
        this.addMenuBar();

        contactList = new ArrayList();
    }

    /**
     * 定义主界面
     * @param parent
     * @return
     */
    protected Control createContents(Composite parent)
    {
        Shell shell = this.getShell();
        shell.setText("数据库结构导出工具");
        shell.setSize(500, 300);

        SashForm form = new SashForm(parent, SWT.HORIZONTAL | SWT.NULL);

        table = new Table(form, SWT.FULL_SELECTION | SWT.BORDER);

        TableColumn column = new TableColumn(table, SWT.LEFT);
        column.setText("数据库");
        column.setWidth(150);
        table.setHeaderVisible(true);

        column = new TableColumn(table, SWT.LEFT);
        column.setText("导出时间");
        column.setWidth(125);
        table.setHeaderVisible(true);

        column = new TableColumn(table, SWT.LEFT);
        column.setText("文件地址");
        column.setWidth(200);
        table.setHeaderVisible(true);

        tableViewer = new TableViewer(table);
        return form;
    }

    public static void main(String[] args)
    {
        AddressBook book = new AddressBook();

        book.setBlockOnOpen(true);
        book.open();

        Display.getCurrent().dispose();
    }

    /**
     * 定义菜单栏
     * @return
     */
    protected MenuManager createMenuManager()
    {
        MenuManager bar = new MenuManager("");

        MenuManager fileMenu = new MenuManager("&File");
        MenuManager helpMenu = new MenuManager("&Help");

        bar.add(fileMenu);
        bar.add(helpMenu);

        fileMenu.add(new NewContactAction(this));
        fileMenu.add(new ExitAction(this));

        helpMenu.add(new AboutAction(this));

        return bar;
    }

    /**
     * 确认后生成数据到列表
     * @param c
     */
    public static void addContact(Contact c)
    {
        getContactList().add(c);

        Table table = tableViewer.getTable();

        TableItem item = new TableItem(table, SWT.NULL);
        String dbName = c.getJdbcUrl().substring(c.getJdbcUrl().lastIndexOf("/") + 1);
        item.setText(0, dbName);
        item.setText(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        item.setText(2, c.getOutUrl());
        File file = new File( c.getOutUrl());
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                    "提示", "文件打开失败");
        }
    }

    public static ArrayList getContactList()
    {
        return contactList;
    }

    public static TableViewer getTbv()
    {
        return tableViewer;
    }

    public static void setContactList(ArrayList list)
    {
        contactList = list;
    }

    public static void setTbv(TableViewer viewer)
    {
        tableViewer = viewer;
    }
}
