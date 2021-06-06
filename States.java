package com.company;

public class States {

    private State[] states;
    private int current; //determine the current state

    public States(int number, int initial) {
        this.states = new State[number];
        this.current = 0;
        this.states[initial].setInitial(); //determine the initial state
    }

    //change current state
    public void setCurrent(int index) {
        this.current = index;
    }

}
