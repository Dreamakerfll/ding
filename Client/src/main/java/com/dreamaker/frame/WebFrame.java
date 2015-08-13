package com.dreamaker.frame;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WebFrame  extends Application {
	private void init(Stage primaryStage) {
		
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        final WindowResizeButton windowResizeButton = new WindowResizeButton(primaryStage, 1020,700);

        BorderPane root1 = new BorderPane() {
            @Override protected void layoutChildren() {
                super.layoutChildren();
                windowResizeButton.autosize();
                windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
                windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
            }
        };
        
        root1.getStyleClass().add("application");
        
        StackPane layerPane = new StackPane();
        
        layerPane.setDepthTest(DepthTest.DISABLE);
        layerPane.getChildren().add(root1);
        
        // create scene
        boolean is3dSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        Scene scene = new Scene(layerPane, 1020, 700, is3dSupported);
        
        if (is3dSupported) {
            //RT-13234
            scene.setCamera(new PerspectiveCamera());
        }

        primaryStage.setScene(scene);
        

    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        init(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }
}
