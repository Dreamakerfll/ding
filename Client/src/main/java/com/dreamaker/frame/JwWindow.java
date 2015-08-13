package com.dreamaker.frame;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
* Title: JwWindow
* Description: 使用SWT制作不规则窗体
* Copyright (c) 2007 Javen Wong
* Create Date: 2007.6.29
* @author: Javen Wong, javenwong@hotmail.com
* @version: 1.0
*/
public class JwWindow {

protected Shell shell;
protected Display display = Display.getDefault();
protected Image image = null;
protected ImageData imageData = null;
protected boolean currentImageFlag;
protected boolean useImage;

static final ArrayList urls = new ArrayList(); // @jve:decl-index=0:  
static boolean canRemove = false;  
static Browser browser;

/**
  * Launch the application
  * @param args
  */
public static void main(String[] args) {
  try {
   JwWindow window = new JwWindow();
   window.open();
  } catch (Exception e) {
   e.printStackTrace();
  }
}

/**
  * Open the window
  */
public void open() {
  createContents();
  shell.open();
  shell.layout();
  while (!shell.isDisposed()) {
   if (!display.readAndDispatch())
    display.sleep();
  }
}

/**
  * Create contents of the window
  */
protected void createContents() {
  shell = new Shell(display, SWT.NO_TRIM);
  shell.setSize(500, 375);
  shell.setText("SWT不规则窗体");
  createIrregularlyShell();
  Listener l = new Listener() {
   int startX, startY;

   public void handleEvent(Event e) {
    if (e.type == SWT.KeyDown && e.character == SWT.ESC) {
     shell.dispose();
    }
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
    if (e.type == SWT.Paint) {
     if (useImage) e.gc.drawImage(image, imageData.x, imageData.y);
    }
   }
  };
  shell.addListener(SWT.KeyDown, l);
  shell.addListener(SWT.MouseDown, l);
  shell.addListener(SWT.MouseMove, l);
  shell.addListener(SWT.Paint, l);

  final Button button = new Button(shell, SWT.NONE);
  button.addSelectionListener(new SelectionAdapter() {
   public void widgetSelected(final SelectionEvent e) {
    shell.dispose();
   }
  });
  button.setText("退出");
  button.setBounds(185, 105, 80, 25);

  final Button button_1 = new Button(shell, SWT.NONE);
  button_1.addSelectionListener(new SelectionAdapter() {
   public void widgetSelected(final SelectionEvent e) {
    shell.setMinimized(true);
    createIrregularlyShell();
    shell.setMinimized(false);
   }
  });
  button_1.setText("切换样式");
  button_1.setBounds(80, 105, 80, 25);

  final Button button_2 = new Button(shell, SWT.NONE);
  button_2.addSelectionListener(new SelectionAdapter() {
   public void widgetSelected(final SelectionEvent e) {
    shell.setMinimized(true);
   }
  });
  button_2.setText("最小化");
  button_2.setBounds(80, 64, 80, 25);

  final Button button_3 = new Button(shell, SWT.NONE);
  button_3.addSelectionListener(new SelectionAdapter() {
   public void widgetSelected(final SelectionEvent e) {
    shell.setMinimized(true);
    createAnnularShell();
    shell.setMinimized(false);
   }
  });
  button_3.setText("环形窗体");
  button_3.setBounds(185, 65, 80, 25);
  
  
  //设置容器
  Composite composite = new Composite(shell, SWT.NONE);
  composite.setBounds(0, 0, 1100, 660);
  composite.setBackground(new Color(display, new RGB(26, 124, 208)));
  composite.addListener(SWT.KeyDown, l);
  composite.addListener(SWT.MouseDown, l);
  composite.addListener(SWT.MouseMove, l);
  composite.addListener(SWT.Paint, l);
  
  //设置browser
  System.setProperty("org.eclipse.swt.browser.XULRunnerPath", "C:\\Program Files\\xulrunner1"); 
  browser = new Browser(composite, SWT.MOZILLA);  
  initialize(display, browser);  
  new CallJava (browser, "callJava1");
  //browser.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,2,1));  
  browser.setBounds(5, 37, 1105, 673);
  //browser.setUrl("http://127.0.0.1/LYLYJWeb/record.do?method=page_to_tra");  
  //browser.setUrl("http://127.0.0.1/LYLYJWeb/index.jsp");  
  //browser.setUrl("http://127.0.0.1/LYLYJWeb/transfer.do?method=pageToTra&isMonitor=1&account=1623532@163.com");  
//  browser.setUrl("http://www.iqiyi.com/a_19rrhbyt65.html?src=focustext_0_20130527_5");  
  browser.setUrl("http://www.baidu.com");  
  
}

protected void createIrregularlyShell(){
  long l1 = System.currentTimeMillis();//////////////////////////////////////
 
  if (currentImageFlag){
   image = new Image(display, new ImageData("images/shell_1.png"));
  }else {
   image = new Image(display, new ImageData("images/shell_2.png"));
  }
  currentImageFlag = !currentImageFlag;
  useImage = true;
 
  Region region = new Region();
  imageData = image.getImageData();
  if (imageData.alphaData != null) {
   for (int y = 0; y < imageData.height; y++) {
    for (int x = 0; x < imageData.width; x++) {
     if (imageData.getAlpha(x, y) == 255) {
      region.add(imageData.x + x,imageData.y + y,1,1);
     }
    }
   }
  } else {
   ImageData mask = imageData.getTransparencyMask();
   for (int y = 0; y < mask.height; y++) {
    for (int x = 0; x < mask.width; x++) {
     if (mask.getPixel(x, y) != 0) {
      region.add(imageData.x + x,imageData.y + y,1,1);
     }
    }
   }
  }
 
  
  shell.setRegion(region);
  shell.setSize(imageData.x + imageData.width, imageData.y
    + imageData.height);
  
 
  System.out.println("切换Shell样式耗时:" +
    ((System.currentTimeMillis() - l1)/1000D) + "秒");///////////////////////////
}

protected void createAnnularShell(){
  long l1 = System.currentTimeMillis();//////////////////////////////////////
 
  useImage = false;
 
  shell.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
  Region region = new Region();
  region.add(createCircle(200, 200, 200));
  region.subtract(createCircle(80, 200, 200));
  shell.setRegion(region);
  Rectangle regionBounds = region.getBounds();
  shell.setSize(regionBounds.width, regionBounds.height);
 
  System.out.println("制作环形Shell耗时:" +
    ((System.currentTimeMillis() - l1)/1000D) + "秒");///////////////////////////
}

protected int[] createCircle(int radius, int centerX, int centerY) {
  int[] points = new int[720 * 2];
  for (int i = 0; i < 720; i++) {
   int theX = centerX + (int) (radius * Math.cos(Math.toRadians(i/2)));
   int theY = centerY + (int) (radius * Math.sin(Math.toRadians(i/2)));
   points[i * 2] = theX;
   points[i * 2 + 1] = theY;
  }
  return points;
}










static void initialize(final Display display, Browser browser) {  
//  browser.addListener(SWT.MouseDown, new Listener() {  
//    public void handleEvent(Event event) { 
//  	  System.out.println(event.text);
//      System.out.println("event.time:" + event.time);  
//    }  
//  }); 
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


	//创建一个矩形1
	private int[] getBackRect1() {
		int[] rect = new int[2 * 4];
		// A
		rect[0] = 5;
		rect[1] = 0;
		// B
		rect[2] = 5;
		rect[3] = 5;
		// C
		rect[4] = 1095;
		rect[5] = 5;
		// D
		rect[6] = 1095;
		rect[7] = 0;
		//
		return rect;
	}
	//创建一个矩形2
	private int[] getBackRect2() {
		int[] rect = new int[2 * 4];
		// E
		rect[0] = 0;
		rect[1] = 5;
		// F
		rect[2] = 0;
		rect[3] = 660;
		// G
		rect[4] = 1100;
		rect[5] = 660;
		// H
		rect[6] = 1100;
		rect[7] = 5;
		//
		return rect;
	}

	// 创建一个圆1
	private int[] circle1(int r, int offsetX, int offsetY) {
		int[] ring = new int[8 * r + 4];
		// x^2 + y^2 = r^2
		for (int i = 0; i < 2 * r + 1; i++) {
			int x = i - r;
			int y = (int) Math.sqrt(r * r - x * x);
			ring[2 * i] = offsetX + x;
			ring[2 * i + 1] = offsetY + y;
			ring[8 * r - 2 * i - 2] = offsetX + x;
			ring[8 * r - 2 * i - 1] = offsetY - y;
		}
		return ring;
	}

	// 创建一个圆1
	private int[] circle2(int r, int offsetX, int offsetY) {
		int[] ring = new int[8 * r + 4];
		// x^2 + y^2 = r^2
		for (int i = 0; i < 2 * r + 1; i++) {
			int x = i - r;
			int y = (int) Math.sqrt(r * r - x * x);
			ring[2 * i] = offsetX + x;
			ring[2 * i + 1] = offsetY + y;
			ring[8 * r - 2 * i - 2] = offsetX + x;
			ring[8 * r - 2 * i - 1] = offsetY - y;
		}
		return ring;
	}
}
