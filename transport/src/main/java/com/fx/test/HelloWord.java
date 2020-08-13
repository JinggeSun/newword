package com.fx.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWord extends Application {

    /**
     * 窗口          Stage
     *   -场景       Scene
     *     -布局     stackPane
     *       -控件   Button
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button("say hello world!");

        button.setOnAction(event->{
            System.out.println("hello world");
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);
        Scene scene = new Scene(stackPane,200,200);

        primaryStage.setTitle("xxxx");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
