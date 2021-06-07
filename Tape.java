package com.company;

import java.util.Stack;

public class Tape {

    private char head;
    private Stack<Character> leftSide; //the left symbols of the current symbol
    private Stack<Character> rightSide;  //the right symbols of the current symbol
    private char[] tapeAlphabet;
    protected final static char blank = '⊡';

    public Tape(String alphabet) {
        this.leftSide = new Stack<Character>();
        this.rightSide = new Stack<Character>();
        this.tapeAlphabet = new char[alphabet.length()];
        //determine the tape alphabet
        for (int i = 0; i > tapeAlphabet.length; i++) {
            this.tapeAlphabet[i] = alphabet.charAt(i);
        }
        this.leftSide.push(blank);
        this.rightSide.push(blank);
    }

    //put input string in the tape
    public void putInput(String input) {
        for (int i = input.length() - 1; i >= 0; i--) {
            this.rightSide.push(input.charAt(i));
        }
        this.head = input.charAt(0);
    }

    //change current symbol
    public void write(char symbol) {
        this.head = symbol;
    }

    //read current symbol
    public char read() {
        return this.head;
    }

    //move head to the left
    public void moveLeft() {
        this.rightSide.push(this.head);
        this.head = this.leftSide.pop();
    }

    //move head to the right
    public void moveRight() {
        this.leftSide.push(this.head);
        this.head = this.rightSide.pop();
    }

    //convert the tape's content to a string
    public String toString() {
        String output = null;
        //move the head symbol and the left side symbols to the right side symbol
        this.rightSide.push(this.head);
        while (true) {
            char top = this.leftSide.pop();
            if (top == blank) {
                this.leftSide.push(blank);
                break;
            }
            this.rightSide.push(top);
        }
        //pop all of the right side symbols and make a string of the tape's content
        while (true) {
            char top = this.rightSide.pop();
            if (top == blank) {
                this.rightSide.push(blank);
                break;
            }
            output += top;
        }
        return output;
    }
}
