package com.dreamaker.frame;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.JWindow;

public class Buttons extends Application{
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		init(primaryStage);
		primaryStage.show();
		
	}
	
	public void init(Stage primaryStage){
		primaryStage.setTitle("按钮");
		Group root = new Group();
		Scene scene = new Scene(root,300,400,Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(true);	//窗口总在最前
//		primaryStage.initStyle(StageStyle.UNDECORATED);	//不加装饰的Stage
		root.getChildren().add(buttonBox(primaryStage));
	}
	
	public static VBox buttonBox(Stage primaryStage){
		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(30, 0, 0, 30));
		buttonBox.setSpacing(30);
		
		final Button button1 = new Button("打开浏览器");	//无标题按钮
		button1.setOnAction(new EventHandler<ActionEvent>() {	//按钮按下
			
			@Override
			public void handle(ActionEvent arg0) {
				JwWindow window = new JwWindow();
				   window.open();
			}
		});
		
		
		
		final Button button2 = new Button("Accept");  	//有标题的按钮
		final DropShadow shadow = new DropShadow();	//投影特效
		button2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {	//鼠标移入

			@Override
			public void handle(MouseEvent arg0) {
				button2.setEffect(shadow);
				
			}
		});
		button2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {	//鼠标移出

			@Override
			public void handle(MouseEvent arg0) {
				button2.setEffect(null);
				
			}
		});
		
		
		
		
		Button button4 = new Button("我是一个按钮");
		button4.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");		//为按钮添加样式
		
		
		buttonBox.getChildren().addAll(button1,button2,button4);
		return buttonBox;
		
	}
	
	

}
