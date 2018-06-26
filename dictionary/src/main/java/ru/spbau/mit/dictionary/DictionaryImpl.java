package ru.spbau.mit.dictionary;

public class DictionaryImpl implements Dictionary {

    private final int INITIAL_SIZE = 10;
    private double MAX_LOAD_FACTOR = 0.75;
    private double MIN_LOAD_FACTOR = 0.5;
    private int numOfKeys;
    private ListNode[] buckets;

    DictionaryImpl() {
        clear();
    }

    public void setMaxLoadFactor(double newLoadFactor) {
        MAX_LOAD_FACTOR = newLoadFactor;
    }

    private int getBucketIndex(String key) {
        return Math.abs(key.hashCode()) % this.buckets.length;
    }

    private int currentLoadFactor() {
        return numOfKeys / buckets.length;
    }
    @Override
    public int size() {
        return numOfKeys;
    }

    @Override
    public boolean contains(String key) {
        int index = getBucketIndex(key);
        ListNode current = buckets[index];
        ListNode withKey = ListNode.findListNodeByKey(current, key);
        return (withKey != null);
    }

    @Override
    public String get(String key) {
        int index = getBucketIndex(key);
        ListNode current = buckets[index];
        ListNode withKey = ListNode.findListNodeByKey(current, key);
        if (withKey != null) {
            return withKey.value;
        }
        return null;
    }

    @Override
    public String put(String key, String newValue) {
        int index = getBucketIndex(key);
        ListNode current = buckets[index];
        ListNode withKey = ListNode.findListNodeByKey(current, key);
        if (withKey != null) {
            if (!withKey.value.equals(newValue)) {
                String previousValue = withKey.value;
                withKey.value = newValue;
                return previousValue;
            } else {
                return null;
            }
        }
        ListNode newNode = new ListNode(key, newValue);
        if (!ListNode.setListNodeInTheEnd(current, newNode)) {
            buckets[index] = newNode;
        }
        this.numOfKeys += 1;
        while (this.currentLoadFactor() >= MAX_LOAD_FACTOR) {
            this.rehash(this.buckets.length * 2);

        }
        return null;
    }

    private void rehash(int newBucketsNum) {
        this.numOfKeys = 0;
        ListNode[] oldBuckets = this.buckets;
        this.buckets = new ListNode[newBucketsNum];
        for (ListNode current : oldBuckets) {
            while (current != null) {
                this.put(current.key, current.value);
                current = current.next;
            }
        }
    }

    @Override
    public String remove(String key) {
        int index = getBucketIndex(key);
        ListNode current = buckets[index];
        String deleted = null;
        if (current!= null && current.key.equals(key)) {
            deleted = current.value;
            buckets[index] = current.next;
        }
        if (deleted == null) {
            deleted = ListNode.removeByKey(current, key);
        }
        if (deleted != null) {
            this.numOfKeys -= 1;
            if (this.currentLoadFactor() <= MIN_LOAD_FACTOR) {
                this.rehash(this.buckets.length / 2);

            }
        }
        return deleted;
    }

    @Override
    public void clear() {
        numOfKeys = 0;
        buckets = new ListNode[INITIAL_SIZE];
    }

    private static class ListNode {

        private String key;
        private String value;
        private ListNode next;

        ListNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        static private String removeByKey(ListNode current, String key) {
            if (current != null) {
                ListNode next = current.next;
                while (next != null) {
                    if (next.key.equals(key)) {
                        current.next = next.next;
                        return next.value;
                    }
                    current = current.next;
                    next = current.next;
                }
            }
            return null;
        }

        static private boolean setListNodeInTheEnd(ListNode current, ListNode newListNode) {
            if (current != null) {
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newListNode;
                return true;
            } else {
                return false;
            }
        }

        static private ListNode findListNodeByKey(ListNode current, String key) {
            if (current != null) {
                while (current != null) {
                    if (current.key.equals(key)) {
                        return current;
                    }
                    current = current.next;
                }
            }
            return null;
        }
    }

}
