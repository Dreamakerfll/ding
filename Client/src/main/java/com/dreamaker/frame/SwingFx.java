package com.dreamaker.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class SwingFx extends Application {
	JPanel content = new JPanel();
    @Override
    public void start(Stage stage) {
    	NativeInterface.open();
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);

        System.out.println(7);
        StackPane pane = new StackPane();
        System.out.println(8);
        pane.setPrefSize(1000, 700);
        pane.setPadding(new Insets(5, 0, 0, 20));
        pane.getChildren().add(swingNode);
        System.out.println(9);

        stage.setScene(new Scene(pane, 1000, 700));
        System.out.println(10);

        stage.show();
        System.out.println(11);
        //NativeInterface.runEventPump();

    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
    	
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//            	
//            }
//        });
//        
//        Platform.runLater(new Runnable() {
//	        @Override
//	        public  void run() {
//	        	
//	        }
//	   });
    	swingNode.setContent(content);
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				final JWebBrowser webBrowser = new JWebBrowser();   
		        webBrowser.setSize(1000, 700);
		        webBrowser.navigate("http://www.google.com");   
				content.setSize(10, 500);
				content.setBackground(new Color(123,123,234));
				content.add(webBrowser);
				
				System.out.println(2);
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println(4);
						System.out.println(NativeInterface.isEventPumpRunning());
						System.out.println(4);
					}
				});
				
				System.out.println(3);
				
			}
		});
    	System.out.println(1);

    }
    
    public static void main(String[] args) {

        launch(args);

    }

}