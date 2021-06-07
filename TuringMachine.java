package com.company;

public class TuringMachine {

    private States states;
    private Tape tape;
    private Transition[] transitions;

    public TuringMachine(States states, String tapeAlphabet, Transition[] transitions) {
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
            for (int i = 0; i < transitions.length; i++) {
                //the wanted transition is found
                if (transitions[i].getFrom().equals(state) && transitions[i].getRead() == curr) {
                    //change current state
                    int destIndx = transitions[i].getTo().getIndex();
                    states.setCurrent(destIndx);
                    //change tape's current symbol
                    char destSymbol = transitions[i].getWrite();
                    tape.write(destSymbol);
                    //move read-write head
                    if (transitions[i].getMovement() == 'R') {
                        tape.moveRight();
                    } else if (transitions[i].getMovement() == 'L') {
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
}
