package com.dreamaker.frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class UIMainPro {

	private static Shell shell;
	private static Composite leftBox;
	private static Composite rightBox;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createControl();
	}
	private static void createControl() {
		final Display display = new Display();
		final Color bkColor = new Color(Display.getCurrent(), 236, 236, 236);
		final Color redColor = new Color(Display.getCurrent(), 255, 0, 0);
		final Color greenColor = new Color(Display.getCurrent(), 0, 255, 0);
		final Color blueColor = new Color(Display.getCurrent(), 0, 0, 255);
		//Image deployIcon = new Image(display, "resource/deploy.png");
		

		shell = new Shell(display);
		GridLayout layout = new GridLayout(2,false);
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		shell.setLayout(layout);
		shell.setText("SWT");
		//shell.setImage(deployIcon);
		
		shell.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event e) {
				Rectangle rect = shell.getClientArea();
				int fullWidth = rect.width;
				int fullHeight = rect.height;
				int leftWidth = (int) (rect.width*0.3);
				int rightWidth = rect.width - leftWidth;
				System.out.println(rect); 
				if(fullWidth >1020){//最大化时可触发
					GridData leftData = new GridData();
					leftData.grabExcessHorizontalSpace = true;
					leftData.grabExcessVerticalSpace = true;
					leftData.minimumWidth = leftWidth;
					leftData.minimumHeight = fullHeight;
					leftBox.setBackground(greenColor);
					leftBox.setLayoutData(leftData);
					leftBox.pack();
					
					GridData rightData = new GridData();
					rightData.grabExcessHorizontalSpace = true;
					rightData.grabExcessVerticalSpace = true;
					rightData.minimumWidth = rightWidth;
					rightData.minimumHeight = fullHeight;
					rightBox.setBackground(blueColor);
					rightBox.setLayoutData(rightData);
					rightBox.pack();
					
					
					
					
				}else if(fullWidth < 1020){//默认尺寸
					GridData leftData = new GridData();
					leftData.grabExcessHorizontalSpace = true;
					leftData.grabExcessVerticalSpace = true;
					leftData.minimumWidth = 300;
					leftData.minimumHeight = 600;
					leftBox.setBackground(redColor);
					leftBox.setLayoutData(leftData);
					leftBox.pack();
					
					GridData rightData = new GridData();
					rightData.grabExcessHorizontalSpace = true;
					rightData.grabExcessVerticalSpace = true;
					rightData.minimumWidth = 700;
					rightData.minimumHeight = 600;
					rightBox.setBackground(redColor);
					rightBox.setLayoutData(rightData);
					rightBox.pack();
				}
				
			}
		});

		// 居中
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);

		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(bkColor); // 设置父控件的背景色
		
		
		leftBox = createLeftBox(shell);
		rightBox = createRightBox(shell);
		
		shell.pack();
		shell.setMinimumSize(1024, 600);

		//shell.setMaximized(true);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	private static Composite createLeftBox(Composite parent) {
		// 创建窗口左边面板
		Composite leftBox = new Composite(parent, SWT.NONE | SWT.Resize);
		final Color bkColor = new Color(Display.getCurrent(), 255, 0, 0);

		GridLayout leftGridLayout = new GridLayout();
		leftGridLayout.marginWidth = 2;
		leftGridLayout.marginHeight = 4;
		leftGridLayout.horizontalSpacing = 2;
		leftGridLayout.marginBottom = 4;
		leftBox.setLayout(leftGridLayout);
		leftBox.setBackgroundMode(SWT.INHERIT_DEFAULT);
		leftBox.setBackground(bkColor);
		
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumWidth = 300;
		data.minimumHeight = 600;
	
		leftBox.setLayoutData(data);
		
		return leftBox;
	}
	
	private static Composite createRightBox(Composite parent) {
		// 创建窗口左边面板
		Composite rightBox = new Composite(parent, SWT.NONE | SWT.Resize);
		final Color blueColor = new Color(Display.getCurrent(), 255, 255, 0);

		GridLayout leftGridLayout = new GridLayout();
		leftGridLayout.marginWidth = 2;
		leftGridLayout.marginHeight = 4;
		leftGridLayout.horizontalSpacing = 2;
		leftGridLayout.marginBottom = 4;
		rightBox.setLayout(leftGridLayout);
		rightBox.setBackgroundMode(SWT.INHERIT_DEFAULT);
		rightBox.setBackground(blueColor);
		
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumWidth = 700;
		data.minimumHeight = 600;
	
		rightBox.setLayoutData(data);
		
		return rightBox;
	}
}