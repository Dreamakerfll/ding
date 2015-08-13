package com.dreamaker.frame;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
  
  
public class TestShell {  
	static final ArrayList urls = new ArrayList(); // @jve:decl-index=0:  
	static boolean canRemove = false;  
	static Browser browser;
	public static Label text1;
	static Shell shell;
    
	public TestShell(){
		
		 /** 
         * 第一步创建Display,对应操作系统的控件，使用完必须释放 
         */  
        Display display = new Display();  
        /** 
         * 第二部创建shell 
         * @style  
         */  
        shell = new Shell(display,SWT.NO_TRIM); 
        
        //处理拖动事件
        Listener listener = new Listener() {
            int startX, startY;
            public void handleEvent(Event e) {
                if (e.type == SWT.MouseDown && e.button == 1) {
                    startX = e.x;
                    startY = e.y;
                }
                if (e.type == SWT.MouseMove && (e.stateMask & SWT.BUTTON1) != 0) {
                    Point p = shell.toDisplay(e.x, e.y);
                    p.x -= startX;
                    p.y -= startY;
                    shell.setLocation(p);
                }
            }
        };
        shell.addListener(SWT.MouseDown, listener);
        shell.addListener(SWT.MouseMove, listener);
        
        
        /** 
         * 第三部指定容器的布局类型 
         */  
//        GridLayout gl = new GridLayout(2,false);  
//        gl.marginBottom = 20;  
//        gl.marginTop = 10;  
//        gl.marginLeft=30;  
//        gl.marginRight = 30;  
//        gl.verticalSpacing = 30;  
//        gl.horizontalSpacing = 30;  
        shell.setLayout(null);  
        /**** 
         * 第四步创建容器里的控件，并指定摆放位置 
         */  
        Label label1 = new Label(shell,SWT.NONE);  
        label1.setText("姓名");  
        /** 
         * 第一个参数是操作系统资源 
         * 第二参数是字体样式 
         * 第三参数是字的高度（字号大小） 
         * 第4个参数是字显示样式 
         */  
        Font labelFont = new Font(display,"微软雅黑",20,SWT.NONE);  
          
        text1 = new Label(shell,SWT.NONE);  
        text1.setFont(labelFont);  
        text1.setBounds(0, 0, 100, 60);
        //text1.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false));  
          
          
        Button btn = new Button(shell,SWT.NONE);  
        btn.setText("设置为上海");  
        btn.setBounds(120, 0, 100, 30);
        btn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("123");
				browser.execute("$('#AreaCombo').combobox('setValue',0);");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        //btn.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,2,1));  
        
        System.setProperty("org.eclipse.swt.browser.XULRunnerPath", "C:\\Program Files\\xulrunner1"); 
        browser = new Browser(shell, SWT.MOZILLA);  
        initialize(display, browser);  
        new CallJava (browser, "callJava1");
        //browser.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,2,1));  
        browser.setBounds(0, 100, 1200, 600);
        //browser.setUrl("http://127.0.0.1/LYLYJWeb/record.do?method=page_to_tra");  
        browser.setUrl("http://127.0.0.1/LYLYJWeb/index.jsp");  
        shell.open();  
        while (!shell.isDisposed()) {  
            if (!display.readAndDispatch())  
                display.sleep();  
        }  
          
          
        display.dispose();  
        
	}
	 
	 public static void main(String[] args) {  
       new TestShell();
      
    }  
    
    static void initialize(final Display display, Browser browser) {  
//        browser.addListener(SWT.MouseDown, new Listener() {  
//          public void handleEvent(Event event) { 
//        	  System.out.println(event.text);
//            System.out.println("event.time:" + event.time);  
//          }  
//        }); 
    	browser.addListener(SWT.MouseDown, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
			}
		});
        browser.addOpenWindowListener(new OpenWindowListener() {  
          public void open(WindowEvent event) {  
            // Embed the new window  
            final Shell shell = new Shell(display);  
            shell.setText("New Window");  
            shell.setLayout(new FillLayout());  
            final Browser browser = new Browser(shell, SWT.NONE);  
            initialize(display, browser);  
            event.browser = browser;  
            event.display.asyncExec(new Runnable() {  
                public void run() {  
                  String url = browser.getUrl();  
                  System.out.println(url);  
                  System.out.println(browser.getText());  
                  if (urls.contains(url)){  
                    //flag to chek if the window is closed automatic  
                    canRemove = false;  
                    shell.close();  
                  }  
                  else{  
                    canRemove = true;  
                    urls.add(url);  
                  }  
                    
                }  
            });  
          }  
        });  
        browser.addVisibilityWindowListener(new VisibilityWindowListener() {  
          public void hide(WindowEvent event) {  
            Browser browser = (Browser) event.widget;  
            Shell shell = browser.getShell();  
            shell.setVisible(false);  
          }  
     
          public void show(WindowEvent event) {  
            Browser browser = (Browser) event.widget;  
            Shell shell = browser.getShell();  
            if (event.location != null)  
                shell.setLocation(event.location);  
            if (event.size != null) {  
                Point size = event.size;  
                shell.setSize(shell.computeSize(size.x, size.y));  
            }  
            if (event.addressBar || event.menuBar || event.statusBar  
                  || event.toolBar) {  
                // Create widgets for the address bar, menu bar, status bar and/or tool bar  
                // leave enough space in the Shell to accomodate a Browser of the size  
                // given by event.size  
            }  
            shell.open();  
          }  
        });  
        browser.addDisposeListener(new DisposeListener(){  
          public void widgetDisposed(DisposeEvent event){  
            Browser browser = (Browser) event.widget;  
            if (canRemove)  
                urls.remove(browser.getUrl());  
            Shell shell = browser.getShell();  
           // shell.close();  
          }  
        });  
  }

    

}  