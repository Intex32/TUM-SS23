package gad.avl;

import java.util.*;

public class AVLTree {

    public static void main(String[] args) {
        var tree = new AVLTree();
        var random = new Random(45);
        for (int i = 0; i < 10; i++) {
            tree.insert(random.nextInt(100));
        }
        System.out.println(tree.dot());
    }

    private AVLTreeNode root = null;

    public AVLTree() {
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    public int height() {
        return root == null ? 0 : root.height();
    }

    public boolean validAVL() {
        return checkCycle(root, new HashSet<>()) && validAVL(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkCycle(AVLTreeNode node, Set<AVLTreeNode> traversedNodes) {
        if (node == null)
            return true;
        if (traversedNodes.contains(node))
            return false;
        traversedNodes.add(node);
        return checkCycle(node.getLeft(), traversedNodes) && checkCycle(node.getRight(), traversedNodes);
    }

    private boolean validAVL(AVLTreeNode node, int min, int max) {
        if (node == null)
            return true;
        var left = node.getLeft();
        var right = node.getRight();
        if (
                (right == null ? 0 : right.height()) - (left == null ? 0 : left.height()) != node.getBalance()
                || Math.abs(node.getBalance()) > 1
                || left != null && left.getKey() > node.getKey()
                || right != null && right.getKey() < node.getKey()
                || left != null && left.getKey() < min
                || right != null && right.getKey() > max
        ) return false;
        return validAVL(left, min, node.getKey()) && validAVL(right, node.getKey(), max);
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLTreeNode insert(AVLTreeNode node, int key) {
        if (node == null)
            return new AVLTreeNode(key);
        else if (node.getKey() > key)
            node.left = insert(node.left, key);
        else if (node.getKey() <= key)
            node.right = insert(node.right, key);
        return rebalance(node);
    }

    private int height(AVLTreeNode n) {
        return n == null ? -1 : n.height();
    }

    private void setHeight(AVLTreeNode n) {
        n.setBalance((n.getRight() == null ? 0 : n.getRight().height()) - (n.getLeft() == null ? 0 : n.getLeft().height()));
    }

    private AVLTreeNode rotateRight(AVLTreeNode y) {
        var x = y.left;
        var z = x.right;
        x.right = y;
        y.left = z;
        setHeight(y);
        setHeight(x);
        return x;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode y) {
        var x = y.right;
        var z = x.left;
        x.left = y;
        y.right = z;
        setHeight(y);
        setHeight(x);
        return x;
    }

    private AVLTreeNode rebalance(AVLTreeNode node) {
        setHeight(node);
        int b = node.getBalance();
        if (b > 1) {
            if (height(node.right.right) > height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        } else if (b < -1) {
            if (height(node.left.left) > height(node.left.right))
                node = rotateRight(node);
            else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        return node;
    }

    public boolean find(int key) {
        return find(key, root);
    }

    private boolean find(int key, AVLTreeNode node) {
        if (node == null)
            return false;
        return key == node.getKey()
                || key > node.getKey() && find(key, node.getRight())
                || key < node.getKey() && find(key, node.getLeft());
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @return der Baum im Graphiz-Format
     */
    private String dot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {" + System.lineSeparator());
        if (root != null) {
            root.dot(sb);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return dot();
    }
    
}