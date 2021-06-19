package com.company;

public class State {

    private final int index;
    private boolean initial; //if it is an initial state
    private boolean finality; //if it is a final state

    public State(int index) {
        this.index = index;
        if (index == 0) {
            this.setInitial();
        }
    }

    public int getIndex() {
        return this.index;
    }

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

    public String toString() {
        return "q" + this.index;
    }

    public boolean equals(State object) {
        if (this.getIndex() == object.getIndex() &&
                this.initial == object.isInitial() &&
                this.finality == object.isFinal()) {
            return true;
        }
        return false;
    }

}
