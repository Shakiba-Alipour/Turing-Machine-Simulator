package com.company;

import javafx.fxml.FXML;

import java.awt.*;

import javafx.event.ActionEvent;

public class Controller {
    @FXML

    String[] splitted;
    int q, Σ, Γ;
    int[] f = new int[q]; //if the state is final,f[state] is 1 else it is 0
    TuringMachine turingMachine;

    public void setQ(ActionEvent e) {
        this.q = Integer.valueOf(new TextField().getText());
        System.out.println(q);
    }

    public void setΣ(ActionEvent e) {
        this.Σ = Integer.valueOf(new TextField().getText());
    }

    public void setΓ(ActionEvent e) {
        this.Γ = Integer.valueOf(new TextField().getText());
    }

    public void setF(ActionEvent e) {
        for (int i = 0; i < f.length; i++) {
            f[i] = 0;
        }
        String str = new TextField().getText();
        String[] split = str.split(",");
        for (int i = 0, j = 0; i < str.length(); i += 2, j++) {
            split[j] = String.valueOf(str.charAt(i));
        }
        for (int i = 0; i < split.length; i++) {
            int idx = Integer.valueOf(split[i]);
            f[idx] = 1;
        }
    }

    public void makeMachine(ActionEvent e) {

    }

}
