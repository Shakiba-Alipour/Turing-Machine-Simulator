package com.company;

import javafx.fxml.FXML;

import java.awt.*;

import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private javafx.scene.control.TextField states, alphabets, tapeAlphabets, finalStates;
    @FXML
    private javafx.scene.layout.AnchorPane TuringMachine;

    int q, Σ, Γ;
    int[] f; //if the state is final,f[state] is 1 else it is 0

    //define number of states
    public void setQ() {
        this.q = Integer.valueOf(states.getText());
        System.out.println(q);
    }

    //define number of alphabets
    public void setΣ() {
        this.Σ = Integer.valueOf(alphabets.getText());
        System.out.println(this.Σ);
    }

    //define number of tape's alphabets
    public void setΓ() {
        this.Γ = Integer.valueOf(tapeAlphabets.getText());
        System.out.println(this.Γ);
    }

    //define final states
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
        //clear anchor pane
        TuringMachine.getChildren().clear();
    }

    public void makeMachine(ActionEvent e) {

    }

}
