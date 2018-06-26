package ru.spbau.mit.trie;

public class Node {

    private boolean terminalState = false;
    private int stringsCount = 0;
    final private Node[] next;

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

    public void addState(char letter) {
        int ix = getIndex(letter);
            if (next[ix] == null) {
                next[ix] = new Node();
            }
    }

    public Node getNextNode(char letter) {
        return next[getIndex(letter)];
    }

    public void changeStringsCount(int number) {
        stringsCount += number;
        if (stringsCount < 0) {
            stringsCount = 0;
        }
    }

    public int strings() {
        return stringsCount;
    }

    public boolean checkAndDeleteNextNode(char letter){
        int ix = getIndex(letter);
        Node nextNode = next[ix];
        if (nextNode != null && nextNode.stringsCount == 0 && !nextNode.isTerminal()) {
            next[ix] = null;
            return true;
        }
        return false;
    }
}

