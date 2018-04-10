package ru.spbau.mit.trie;

public class TrieImpl implements Trie {

    private Node root = new Node();

    @Override
    public boolean add(String element) {
        if (!element.isEmpty()) {
            Node lastLetterNode = goToNode(element, true);
            if (lastLetterNode.isTerminal()) {
                return false;
            }
            goAndChangeTerminal(element, true);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String element) {
        Node lastLetterNode = goToNode(element, false);
        return (lastLetterNode != null && lastLetterNode.isTerminal());
    }

    @Override
    public boolean remove(String element) {
        Node lastLetterNode = goToNode(element, false);
        if (lastLetterNode == null || !lastLetterNode.isTerminal()) {
            return false;
        }
        goAndChangeTerminal(element, false);
        checkAndDelete(element);
        return true;
    }

    @Override
    public int size() {
        return root.strings();
    }

    @Override
    public int howManyStartsWithPrefix(String prefix) {
        Node lastLetterNode = goToNode(prefix, false);
        if (lastLetterNode == null) {
            return 0;
        }
        return lastLetterNode.strings();
    }

    private Node goToNode(String element, boolean adding) {
        Node currentNode = root;
        for (int i = 0; i < element.length(); i++) {
            char letter = element.charAt(i);
            if (currentNode.getNextNode(letter) == null) {
                if (!adding) {
                    return null;
                }
                currentNode.addState(letter);
            }
            currentNode = currentNode.getNextNode(letter);
        }
        return  currentNode;
    }

    private void goAndChangeTerminal(String element, boolean adding) {
        final int num = adding ? 1 : -1;
        Node currentNode = root;
        for (int i = 0; i < element.length(); i++) {
            char letter = element.charAt(i);
            currentNode.changeStringsCount(num);
            currentNode = currentNode.getNextNode(letter);
        }
        currentNode.setTerminalState(adding);
        currentNode.changeStringsCount(1);
    }

    private void checkAndDelete(String element) {
        Node currentNode = root;
        for (int i = 0; i < element.length(); i++) {
            char letter = element.charAt(i);
            if (currentNode.checkAndDeleteNextNode(letter)) {
                return;
            }
            currentNode = currentNode.getNextNode(letter);
        }
    }

    private static class Node {

        private boolean terminalState = false;
        private int stringsCount = 0;
        final private Node[] next;

        Node() {
            next = new Node[26];
        }

        private static int getIndex(char letter) {
            if (letter >= 'a') {
                return (letter - 'a');
            }
            return letter - 'A';
        }

        boolean isTerminal() {
            return terminalState;
        }

        void setTerminalState(boolean state) {
            terminalState = state;
        }

        void addState(char letter) {
            int ix = getIndex(letter);
            if (next[ix] == null) {
                next[ix] = new Node();
            }
        }

        Node getNextNode(char letter) {
            return next[getIndex(letter)];
        }

        void changeStringsCount(int number) {
            stringsCount += number;
            if (stringsCount < 0) {
                stringsCount = 0;
            }
        }

        int strings() {
            return stringsCount;
        }

        boolean checkAndDeleteNextNode(char letter){
            int ix = getIndex(letter);
            Node nextNode = next[ix];
            if (nextNode != null && nextNode.stringsCount == 0 && !nextNode.isTerminal()) {
                next[ix] = null;
                return true;
            }
            return false;
        }
    }
}
