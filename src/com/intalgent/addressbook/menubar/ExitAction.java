package com.intalgent.addressbook.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;


public class ExitAction extends Action
{
    ApplicationWindow window;

    public ExitAction(ApplicationWindow w)
    {
        window = w;
        this.setText("退出");

    }

    public void run()
    {
        window.close();
    }
}
