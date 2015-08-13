package com.dreamaker.frame;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PengZhuang extends JFrame implements Runnable {  
   
    static int n = 0; //方向
    static int k = 0;
    static int g = 0;//记录小球1的方向
    static int d = 0;//记录小球2的方向
    static int fi = 300;//小球1的初始X坐标    
    static int fj = 30; //小球1的初始Y坐标   
    static int li = 10;//小球2的初始X坐标       
    static int lj = 100;//小球2的初始Y坐标   
    static double v = 10;// 速度  
    //static double w = 2 * Math.PI;  
    //static double A = 50;// 振幅  
    //static double t = 0;// 时间  
  
    public PengZhuang() {  
        this.setSize(500, 500);  
        this.setVisible(true);  
    }  
  
    public void paint(Graphics g) {  
        super.paint(g);  
        g.setColor(Color.black);  
        g.fillOval(li, lj , 40, 40);//画出小球1
        g.setColor(Color.blue);
        g.fillOval(fi, fj , 40, 40);//画出小球2
     
        
    }  
  
    public void run() {  
        while (true) {  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                // e.printStackTrace();  
            } 
          
            k=1;jisuan();//对于小球1，计算小球运动方向
            k=2;jisuan();//对于小球2，计算小球运动方向	
            jiance();//检测是否两小球相撞
           
           
            
            
            
        }
        
      
    }  
    /*
     * 小球与边缘的碰撞检测函数
     */
    public void jisuan(){  
    	
    	int i = 0,j = 0;
    	if(k==1) {i=fi;j=fj;n=g;} if(k==2) {i=li;j=lj;n=d;}//分别将小球的上一运动方向传给N
    	
    	if(i>440 ) { if(n==2) n=1;if(n==4) n=3;else n=1;}
    	if(j<40 ) { if(n==3) n=1;if(n==4) n=2;}
    	if(j>440 ) {if(n==2) n=4;if(n==1) n=3;else n=4;}
    	if(i<20 ) {if(n==1) n=2;if(n==3) n=4;}
    	
    	if(n==0) {i += v;j += v;}
      	if(n==1) {i -= v;j += v;}
      	if(n==2) {i += v;j += v;}
      	if(n==3) {i -= v;j -= v;}
      	if(n==4) {i += v;j -= v;}
        	
    	if(k==1) {fi=i;fj=j;g=n;this.repaint();}
    	if(k==2) {li=i;lj=j;d=n;this.repaint();}
    	
    }
    
    /*
     * 小球与小球的碰撞检测函数
     */
    public void jiance(){
    	
    	double f=0;int mg=0;
    	f=(fi-li)*(fi-li)+(fj-lj)*(fj-lj);//计算两小球圆心距离，如果圆心距离等于半径之和则碰撞了。
    	if((int)Math.sqrt(f)<=45){
    		
    		mg=d;
    		d=g;
    		g=mg;
    	
    	}
    	
    }
  
    public static void main(String args[]) {  
        new Thread(new PengZhuang()).start();  
    }  
}  