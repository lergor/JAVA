package ru.spbau.mit.trie;

public class Node {

    private boolean terminalState = false;
    private int stringsCount = 0;
    private Node[] next;

    Node() {
        next = new Node[26];
    }

    static private int getIndex(char letter) {
        if (letter >= 'a') {
            return (letter - 'a');
        }
        return letter - 'A';
    }

    public boolean isTerminal() {
        return terminalState;
    }

    public void setTerminalState(boolean state) {
        terminalState = state;
    }

    public void setState(char letter, boolean adding) {
        if(adding) {
            if(next[getIndex(letter)] == null) {
                next[getIndex(letter)] = new Node();
                return;
            }
        }
        next[getIndex(letter)] = null;
    }

    public Node getNextNode(char letter) {
        return next[getIndex(letter)];
    }

    public void changeStringsCount(int number) {
        stringsCount += number;
        if(stringsCount < 0) {
            stringsCount = 0;
        }
    }

    public int strings() {
        return stringsCount;
    }

    public boolean checkAndDeleteNextNode(char letter){
        Node nextNode = next[getIndex(letter)];
        if( nextNode != null && nextNode.stringsCount == 0) {
            next[getIndex(letter)] = null;
            return true;
        }
        return false;
    }
}

