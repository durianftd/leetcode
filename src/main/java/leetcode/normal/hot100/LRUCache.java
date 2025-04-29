package leetcode.normal.hot100;

import leetcode.normal.hot100.struct.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {

    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int k = 0;
    public LRUCache(int capacity) {
        k = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addNodeToTail(node);
        return node.val;
    }

    private void removeNode(Node node) {
        map.remove(node.key);
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addNodeToTail(Node node) {
        map.put(node.key, node);
        node.next = tail;
        node.pre = tail.pre;
        node.pre.next = node;
        tail.pre = node;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            removeNode(node);
            node.val = value;
            addNodeToTail(node);
        } else {
            if (map.size() == k) {
                Node h = head.next;
                removeNode(h);
            }
            Node newNode = new Node(key, value);
            addNodeToTail(newNode);
        }
    }
}
