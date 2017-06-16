package com.intalgent.addressbook.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class BasicContactPage extends WizardPage {

    private static String[] DRIVER_CLASS_STATES = {"com.mysql.jdbc.Driver"};
    private Combo  driverClassText;
    private Text jdbcUrlText;
    private Text userNameText;
    private Text passwordText;
    private Text outUrlText;
    private ISelection selection;

    public BasicContactPage(ISelection selection)
    {
        super("wizardPage");
        setTitle("生成XML文件");
        this.selection = selection;
    }

    public void createControl(Composite parent)
    {
        setPageComplete(false);
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 2;
        layout.verticalSpacing = 9;

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);

        // 驱动类
        Label label = new Label(container, SWT.NULL);
        label.setText("&driverClass:");
        driverClassText = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        driverClassText.setLayoutData(gd);
        driverClassText.setItems(DRIVER_CLASS_STATES);
        driverClassText.addModifyListener(new ModifyListener()
            {
                public void modifyText(ModifyEvent e)
                {
                    dialogChanged();
                }
            });

        // 数据库
        label = new Label(container, SWT.NULL);
        label.setText("&jdbcUrl:");

        jdbcUrlText = new Text(container, SWT.BORDER | SWT.SINGLE);
        jdbcUrlText.setLayoutData(gd);
        jdbcUrlText.addModifyListener(new ModifyListener()
            {
                public void modifyText(ModifyEvent e)
                {
                    dialogChanged();
                }
            });
        //用户名
        label = new Label(container, SWT.NULL);
        label.setText("&username:");
        userNameText = new Text(container, SWT.BORDER | SWT.SINGLE);
        userNameText.setLayoutData(gd);
        userNameText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });

        //密码
        label = new Label(container, SWT.NULL);
        label.setText("&password:");
        passwordText = new Text(container, SWT.BORDER | SWT.SINGLE);
        passwordText.setLayoutData(gd);
        passwordText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });
        createLine(container, layout.numColumns);

        //生成目录
        label = new Label(container, SWT.NULL);
        label.setText("&目录:");
        outUrlText = new Text(container, SWT.BORDER | SWT.SINGLE);
        outUrlText.setLayoutData(gd);
        outUrlText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });
        outUrlText.setText("d:/doc/db/");
        setControl(container);
    }

    private void dialogChanged()
    {
        if (this.getDriverClassText().length() == 0) {
            updateStatus("选择驱动类");
        }else if (this.getJdbcUrlText().length() == 0) {
            updateStatus("填写数据源");
        }else if (this.getUserNameText().length() == 0) {
            updateStatus("填写用户名");
        }else if (this.getPasswordText().length() == 0) {
            updateStatus("填写密码");
        }else if (this.getOutUrlText().length() == 0) {
            updateStatus("填写文件生成目录");
        }else{
            updateStatus(null);
        }
    }

    public void updateStatus(String message)
    {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    private void createLine(Composite parent, int ncol)
    {
        Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL |
                SWT.BOLD);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = ncol;
        line.setLayoutData(gridData);
    }

    public String getDriverClassText() {
        return driverClassText.getText();
    }

    public String getJdbcUrlText() {
        return jdbcUrlText.getText();
    }

    public String getUserNameText() {
        return userNameText.getText();
    }

    public String getPasswordText() {
        return passwordText.getText();
    }

    public String getOutUrlText() {
        return outUrlText.getText();
    }
}
