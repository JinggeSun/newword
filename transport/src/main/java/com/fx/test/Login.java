package com.fx.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("login");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        Text scenceText = new Text("hello");
        scenceText.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        gridPane.add(scenceText,0,0,2,1);


    }
}
