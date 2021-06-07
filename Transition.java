package com.company;

public class Transition {

    private State from; //the origin state
    private State to; //the destination state
    private char read; //the symbol to be read
    private char write; //the symbol to be written
    private char movement; //R for moving to the right and L for moving to the left

    public Transition(State origin, char read, State destination, char write, char movement) {
        this.from = origin;
        this.read = read;
        this.to = destination;
        this.write = write;
        this.movement = movement;
    }

    public char getRead() {
        return this.read;
    }

    public char getWrite() {
        return this.write;
    }

    public State getFrom() {
        return this.from;
    }

    public State getTo() {
        return this.to;
    }

    public char getMovement() {
        return this.movement;
    }

    public String toString() {
        return "δ(" + this.from + "," + this.read + ") = (" + this.to + "," + this.write + "," + this.movement + ")";
    }

}
