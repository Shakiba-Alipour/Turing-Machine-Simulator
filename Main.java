package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static Stage mainStage;
    public static int q, Σ, Γ, t;
    public static int[] f; //if the state is final,f[state] is 1 else it is 0
    public static States states;
    public static ArrayList<Transition> transitions;
    public static TuringMachine machine;
    public static String input = null;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        mainStage = stage;
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
