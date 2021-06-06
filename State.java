package com.company;

public class State {

    private boolean initial; //if it is an initial state
    private boolean finality; //if it is a final state

    public void setInitial() {
        this.initial = true;
    }

    public void setFinality() {
        this.finality = true;
    }

    public boolean isInitial() {
        return this.initial;
    }

    public boolean isFinal() {
        return this.finality;
    }
}
