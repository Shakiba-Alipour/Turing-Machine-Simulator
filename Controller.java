package com.company;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class Controller {
    @FXML
    private javafx.scene.control.TextField numberOfStates, alphabets, tapeAlphabets, finalStates, numberOfTransitions,
            currentState, currentSymbole, nextState, newSymbole, movement;
    @FXML
    private javafx.scene.layout.AnchorPane TuringMachine;
    @FXML
    private javafx.scene.layout.Pane pane;

    int q, Σ, Γ, t, check = 0; //check is used for receiving transitions
    int[] f; //if the state is final,f[state] is 1 else it is 0
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    States states;
    ArrayList<Transition> transitions = new ArrayList<Transition>();
    TuringMachine machine;

    //set number of states
    public void setQ() {
        this.q = Integer.valueOf(numberOfStates.getText());
    }

    //set number of alphabets
    public void setΣ() {
        this.Σ = Integer.valueOf(alphabets.getText());
    }

    //set number of tape's alphabets
    public void setΓ() {
        this.Γ = Integer.valueOf(tapeAlphabets.getText());
    }

    //set number of transitions
    public void setT() {
        this.t = Integer.valueOf(numberOfTransitions.getText());
    }

    //set final states
    public void setF() {
        this.f = new int[10];
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
        setQ();
        setΣ();
        setΓ();
        setF();
        setT();
        //clear anchor pane
        TuringMachine.getScene().getWindow().hide();

    }

    public void takeTransitions(ActionEvent e) {
        if (check < t) {
            //create new transition
            int currState = Integer.valueOf(currentState.getText());
            char read = currentSymbole.getText().charAt(0);
            int newState = Integer.valueOf(nextState.getText());
            char write = newSymbole.getText().charAt(0);
            char move = movement.getText().charAt(0);
            Transition transition = new Transition(states.getState(currState), read, states.getState(newState), write, move);
            //add the transition to the array list
            transitions.add(transition);
            check++;
        } else {
            pane.getScene().getWindow().hide();
            makeMachine(e);
        }
    }

    public void makeMachine(ActionEvent e) {
        //making the states
        states = new States(q);
        //making the tape
        String tapeAlphabet = "";
        for (int i = 0; i < this.Γ; i++) {
            tapeAlphabet += alphabet.charAt(i);
        }
        Tape tape = new Tape(tapeAlphabet);
        //making the machine
        machine = new TuringMachine(states, tapeAlphabet, transitions);
    }

}
