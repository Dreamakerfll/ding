package com.dreamaker.frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class LayoutTest{
    public static void main(String[] args) {
        new LayoutTest();
    }

    public void todo(Shell shell) {
        //////////////////////////////////////////////////////
        //FillLayout
        //////////////////////////////////////////////////////
//        FillLayout layout = new FillLayout();
//        shell.setLayout(layout);
//        for(int i=0;i<10;i++){
//            Button button = new Button(shell,SWT.PUSH);
//            button.setText("Button"+i);
//            
////            Image img = new Image(null,"icons\\ok.png");
////            button.setImage(img);
//        }
        //////////////////////////////////////////////////////
        //RowLayout
        //////////////////////////////////////////////////////
//        RowLayout layout = new RowLayout();
//        shell.setLayout(layout);
//        for(int i=0;i<10;i++){
//            Button button = new Button(shell,SWT.PUSH);
//            Image img = new Image(null,"icons\\ok.png");
//            button.setImage(img);
//            button.setText("Button"+i);
//        }
        //////////////////////////////////////////////////////
        //GridLayout
        //////////////////////////////////////////////////////
//        GridLayout layout = new GridLayout();
//        layout.numColumns = 3;
//        shell.setLayout(layout);
//        for(int i=0;i<10;i++){
//            Button button = new Button(shell,SWT.PUSH);
//            Image img = new Image(null,"icons\\ok.png");
//            button.setImage(img);
//            button.setText("Button"+i);
//        }
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        shell.setLayout(layout);
        Button btn1 = new Button(shell,SWT.PUSH);
        GridData gd1 = new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
        gd1.widthHint = 100;
        gd1.heightHint = 100;
        btn1.setLayoutData(gd1);
        Button btn2 = new Button(shell,SWT.PUSH);
        GridData gd2 = new GridData(SWT.FILL,SWT.FILL,false,false,1,2);
        gd2.widthHint = 100;
        gd2.heightHint = 100;
        btn2.setLayoutData(gd2);
        Button btn3 = new Button(shell,SWT.PUSH);
        GridData gd3 = new GridData(GridData.FILL_BOTH);
//        gd3.widthHint = 100;
//        gd3.heightHint = 100;
        btn3.setLayoutData(gd3);
        Button btn4 = new Button(shell,SWT.PUSH);
        GridData gd4 = new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
        gd4.widthHint = 100;
        gd4.heightHint = 100;
        btn4.setLayoutData(gd4);
        //////////////////////////////////////////////////////
        //FormLayout
        //////////////////////////////////////////////////////
//        FormLayout layout = new FormLayout();
//        shell.setLayout(layout);
//        
//        Button cancelButton = new Button(shell,SWT.PUSH);
//        cancelButton.setText("Cancel");
//        FormData formData1 = new FormData();
//        formData1.right = new FormAttachment(100,-5); //第一个数字式百分比，也就是说  【宽度-5】
//        formData1.bottom = new FormAttachment(100,-5); //第一个数字式百分比，也就是说  【高度-5】
//        cancelButton.setLayoutData(formData1);
//        
//        Button okButton = new Button(shell,SWT.PUSH);
//        okButton.setText("OK");
//        FormData formData2 = new FormData();
//        formData2.right = new FormAttachment(100,-60);
//        formData2.bottom = new FormAttachment(100,-5);
//        okButton.setLayoutData(formData2);
//        
//        Text text = new Text(shell,SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
//        FormData formData3 = new FormData();
//        formData3.top = new FormAttachment(0,5);
//        formData3.bottom = new FormAttachment(cancelButton,-5);//底部距离 【底部控件-5个像素】
//        formData3.left = new FormAttachment(0,5);
//        formData3.right = new FormAttachment(100,-5);
//        text.setLayoutData(formData3);
//        Color color = new Color(null,255,0,0);
//        text.setForeground(color);
    }
}