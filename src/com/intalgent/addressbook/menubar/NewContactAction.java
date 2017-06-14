package com.intalgent.addressbook.menubar;

import com.intalgent.addressbook.wizard.ContactWizard;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;


/**
 * 生成xls界面
 */
public class NewContactAction extends Action
{
    ApplicationWindow window;

    public NewContactAction(ApplicationWindow w)
    {
        window = w;
        this.setText("填写数据源");
    }

    public void run()
    {
        ContactWizard wizard = new ContactWizard();

        WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
        dialog.open();
    }
}
