package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*JFrame frame = new JFrame("Turing Machine");
        frame.setSize(600, 400);
        JLabel title = new JLabel("Turing Machine");
        title.setBounds(300, 50, 100, 100);

        frame.add(title);
        frame.setVisible(true);
        frame.show();*/
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
