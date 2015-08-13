package com.dreamaker.frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test111 {

	public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Main Window");
        shell.setLayout(new FillLayout());
        Browser browser = new Browser(shell, SWT.NONE);
        initialize(display, browser);
        shell.open();
        browser.setUrl("www.baidu.com");
        while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                        display.sleep();
        }
        display.dispose();
}

static void initialize(final Display display, Browser browser) {
        browser.addOpenWindowListener(new OpenWindowListener() {
                public void open(WindowEvent event) {
                        // Certain platforms can provide a default full browser.
                        // simply return in that case if the application prefers
                        // the default full browser to the embedded one set below.
                        if (!event.required) return;

                        // Embed the new window
                        Shell shell = new Shell(display);
                        shell.setText("New Window");
                        shell.setLayout(new FillLayout());
                        Browser browser = new Browser(shell, SWT.NONE);
                        initialize(display, browser);
                        event.browser = browser;
                }
        });
        browser.addVisibilityWindowListener(new VisibilityWindowListener() {
                public void hide(WindowEvent event) {
                        Browser browser = (Browser)event.widget;
                        Shell shell = browser.getShell();
                        shell.setVisible(false);
                }
                public void show(WindowEvent event) {
                        Browser browser = (Browser)event.widget;
                        Shell shell = browser.getShell();
                        if (event.location != null) shell.setLocation(event.location);
                        if (event.size != null) {
                                Point size = event.size;
                                shell.setSize(shell.computeSize(size.x, size.y));
                        }
                        if (event.addressBar || event.menuBar || event.statusBar || event.toolBar) {
                                // Create widgets for the address bar, menu bar, status bar and/or tool bar
                                // leave enough space in the Shell to accommodate a Browser of the size
                                // given by event.size
                        }
                        shell.open();
                }
        });
        browser.addCloseWindowListener(new CloseWindowListener() {
                public void close(WindowEvent event) {
                	System.out.println(123);
                        Browser browser = (Browser)event.widget;
                        Shell shell = browser.getShell();
                        shell.close();
                }
        });
        
        browser.addDisposeListener(new DisposeListener(){  
            public void widgetDisposed(DisposeEvent event){  
            	System.out.println(2234);
              Browser browser = (Browser) event.widget;  
              Shell shell = browser.getShell();  
              shell.close();  
            }  
          }); 
}
}
