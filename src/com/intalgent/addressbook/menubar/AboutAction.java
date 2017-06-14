package com.intalgent.addressbook.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;

import org.eclipse.swt.widgets.Display;


public class AboutAction extends Action
{
    ApplicationWindow window;

    public AboutAction(ApplicationWindow w)
    {
        window = w;
        this.setText("&关于");

    }

    public void run()
    {
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
            "提示", "RYAN数据库结构导出小工具");
    }
}
