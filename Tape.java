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
        for (int i = 0; i < tapeAlphabet.length; i++) {
            this.tapeAlphabet[i] = alphabet.charAt(i);
        }
        this.leftSide.push(blank);
        this.rightSide.push(blank);
    }

    //put input string in the tape
    public void putInput(String input) {
        this.head = input.charAt(0);
        for (int i = input.length() - 1; i > 0; i--) {
            this.rightSide.push(input.charAt(i));
        }
    }

    //get the head symbol
    public char getHead() {
        return this.head;
    }

    //check if the left side is empty
    public boolean isLeftEmpty() {
        if (this.leftSide.empty()) {
            return true;
        }
        char temp = this.leftSide.pop();
        if (temp != blank) {
            this.leftSide.push(temp);
            return false;
        }
        this.leftSide.push(temp);
        return true;
    }

    //check if the right side is empty
    public boolean isRightEmpty() {
        if (this.rightSide.empty()) {
            return true;
        }
        char temp = this.rightSide.pop();
        if (temp != blank) {
            this.rightSide.push(temp);
            return false;
        }
        this.rightSide.push(temp);
        return true;
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
        if (this.head != blank) {
            this.rightSide.push(this.head);
            this.head = this.leftSide.pop();
        }
    }

    //move head to the right
    public void moveRight() {
        if (this.head != blank) {
            this.leftSide.push(this.head);
            this.head = this.rightSide.pop();
        }
    }

    //check if the head is blank
    public boolean isBlank() {
        if (this.head == blank) {
            return true;
        }
        return false;
    }

    //print left side symbols
    public String toStringLeft() {
        String output = "";
        Stack<Character> temp = new Stack<>();
        //move the head symbol and the left side symbols to the temp
        while (true) {
            char top = this.leftSide.pop();
            if (top == blank) {
                this.leftSide.push(blank);
                break;
            }
            temp.push(top);
        }
        while (!temp.empty()) {
            char top = temp.pop();
            this.leftSide.push(top);
            output += top;
        }
        if (output == null) {
            return "";
        }
        return output;
    }

    //print right side symbols
    public String toStringRight() {
        String output = "";
        Stack<Character> temp = new Stack<>();
        //pop all of the right side symbols and make a string of them
        while (true) {
            char top = this.rightSide.pop();
            if (top == blank) {
                this.rightSide.push(blank);
                break;
            }
            temp.push(top);
            output += top;
        }
        while (!temp.empty()) {
            this.rightSide.push(temp.pop());
        }
        if (output == null) {
            return "";
        }
        return output;
    }

    //convert the tape's content to a string
    public String toString() {
        return this.toStringLeft() + this.getHead() + this.toStringRight();
    }
}
