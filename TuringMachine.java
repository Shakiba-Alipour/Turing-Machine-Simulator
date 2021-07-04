package com.company;

import java.util.ArrayList;

public class TuringMachine {

    private States states;
    private Tape tape;
    private ArrayList<Transition> transitions;
    private String output;

    public TuringMachine(States states, String tapeAlphabet, ArrayList<Transition> transitions) {
        this.states = states;
        this.tape = new Tape(tapeAlphabet);
        this.transitions = transitions;
        this.output = "";
    }

    //put input into the tape
    public void setInput(String input) {
        this.tape.putInput(input);
    }

    //process on the input string
    public boolean acceptor() {
        int sw;
        while (true) {
            sw = 0; //if a transition if found based on this situation, sw will be valued 1, else s1 will be valued 0
            if (this.tape.isBlank()) {
                break;
            }
            //print the situation
            this.output += printOutput();
            //check the transitions
            char curr = tape.read();
            State state = states.getCurrent();
            for (int i = 0; i < transitions.size(); i++) {
                //the wanted transition is found
                if (transitions.get(i).getFrom().equals(state) && transitions.get(i).getRead() == curr) {
                    //change current state
                    int destIndx = transitions.get(i).getTo().getIndex();
                    states.setCurrent(destIndx);
                    //change tape's current symbol
                    char destSymbol = transitions.get(i).getWrite();
                    tape.write(destSymbol);
                    sw = 1;
                    //move read-write head
                    if (transitions.get(i).getMovement() == 'r' && !tape.isRightEmpty()) {
                        tape.moveRight();
                    } else if (transitions.get(i).getMovement() == 'l' && !tape.isLeftEmpty()) {
                        tape.moveLeft();
                    } else if (transitions.get(i).getMovement() == 's' && !tape.isLeftEmpty()) {
                        break;
                    }
                    break;
                }
            }
            if (sw == 0) { //no transition is found
                return false;
            } else if (sw == 1 && states.getCurrent().isFinal()) { //the input is accepted
                return true;
            }
        }
        return false;
    }

    //print current situation
    public String printOutput() {
        String str = "(" + this.tape.toStringLeft() + this.states.getCurrent().toString() +
                this.tape.getHead() + this.tape.toStringRight() + ")";
        if (!this.tape.isBlank()) {
            str += " ⊢";
        } else {
            System.out.println("\n");
        }
        str += " ";
        return str;
    }

    public String getOutput() {
        return this.output;
    }
}
