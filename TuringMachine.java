package com.company;

import java.util.ArrayList;

public class TuringMachine {

    private States states;
    private Tape tape;
    private ArrayList<Transition> transitions;

    public TuringMachine(States states, String tapeAlphabet, ArrayList<Transition> transitions) {
        this.states = states;
        this.tape = new Tape(tapeAlphabet);
        this.transitions = transitions;
    }

    //put input into the tape
    public void setInput(String input) {
        this.tape.putInput(input);
    }

    //process on the input string
    public boolean acceptor() {
        while (true) {
            char curr = tape.read();
            if (curr == tape.blank) {
                break;
            }
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
                    //move read-write head
                    if (transitions.get(i).getMovement() == 'r') {
                        tape.moveRight();
                    } else if (transitions.get(i).getMovement() == 'l') {
                        tape.moveLeft();
                    }
                    break;
                }
            }

        }
        if (states.getCurrent().isFinal()) {
            return true;
        }
        return false;
    }

    //print current situation
    public String printOutput() {
        String output = "(" + this.tape.toStringLeft() + this.states.getCurrent().toString() +
                this.tape.getHead() + this.tape.toStringRight() + ") ";
        if (!this.tape.isBlank()) {
            output += "⊢";
        }
        output += " ";
        return output;
    }
}
