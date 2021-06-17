package com.company;

import java.util.ArrayList;

public class States {

    private ArrayList<State> states;
    private int current; //determine the current state

    public States(int number) {
        this.states = new ArrayList<State>();
        //build states
        for (int i = 0; i < number; i++) {
            this.states.add(new State(i));
        }
        this.current = 0;
    }

    //change current state
    public void setCurrent(int index) {
        this.current = index;
    }

    //get current state
    public State getCurrent() {
        return this.states.get(current);
    }

    //get a special state
    public State getState(int index) {
        return this.states.get(index);
    }
}
