package com.company;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Main.*;

public class Controller {
    @FXML
    private javafx.scene.control.TextField numberOfStates, alphabets, tapeAlphabets, finalStates, numberOfTransitions,
            currentState, currentSymbol, nextState, newSymbol, movement, inputField;
    @FXML
    private javafx.scene.layout.AnchorPane anchorpane;
    @FXML
    private javafx.scene.layout.Pane pane, finalPane;

    private int check = 0; //check is used for receiving transitions
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    //set input
    public void setInputField() {
        input = inputField.getText();
    }

    //set number of states
    public void setQ() {
        q = Integer.valueOf(numberOfStates.getText());
    }

    //set number of alphabets
    public void setΣ() {
        Σ = Integer.valueOf(alphabets.getText());
    }

    //set number of tape's alphabets
    public void setΓ() {
        Γ = Integer.valueOf(tapeAlphabets.getText());
    }

    //set number of transitions
    public void setT() {
        t = Integer.valueOf(numberOfTransitions.getText());
    }

    //set final states
    public void setF() {
        f = new int[q];
        for (int i = 0; i < f.length; i++) {
            f[i] = 0;
        }
        String str = finalStates.getText();
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            int idx = Integer.valueOf(split[i].charAt(0)) - 48;
            f[idx] = 1;
        }
    }

    public void pressOK(ActionEvent e) {
        setInputField();
        setQ();
        setΣ();
        setΓ();
        setF();
        setT();
        //clear anchor pane
        anchorpane.getScene().getWindow().hide();
        //open new pane to get transitions
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Transition.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void takeTransitions(ActionEvent e) {
        //making the states
        states = new States(q);
        //create transition list
        transitions = new ArrayList<Transition>();
        //create new transition
        int currState = Integer.valueOf(currentState.getText());
        char read = currentSymbol.getText().charAt(0);
        int newState = Integer.valueOf(nextState.getText());
        char write = newSymbol.getText().charAt(0);
        char move = movement.getText().charAt(0);
        Transition transition = new Transition(states.getState(currState), read, states.getState(newState), write, move);
        //add the transition to the array list
        transitions.add(transition);
        check++;
        //clear text fields' content
        currentState.clear();
        currentSymbol.clear();
        nextState.clear();
        newSymbol.clear();
        movement.clear();
        if (check == t) {
            pane.getScene().getWindow().hide();
            makeMachine(e);
        }
    }

    public void makeMachine(ActionEvent e) {
        //set final states
        for (int i = 0; i < f.length; i++) {
            if (f[i] == 1) {
                states.getState(i).setFinality();
            }
        }
        //making the tape
        String tapeAlphabet = "";
        for (int i = 0; i < Γ; i++) {
            tapeAlphabet += alphabet.charAt(i);
        }
        Tape tape = new Tape(tapeAlphabet);
        //making the machine
        machine = new TuringMachine(states, tapeAlphabet, transitions);
        machine.setInput(input);
        //display the machine
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DisplayMachine.fxml"));
            if (machine.acceptor()) {

            } else {
                Label result = new Label("The string is not accepted!");
                result.setMinSize(100, (int) finalPane.getHeight() / 2);
                //add label to the final pane
                finalPane.getChildren().add(new javafx.scene.control.Label());
            }
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
