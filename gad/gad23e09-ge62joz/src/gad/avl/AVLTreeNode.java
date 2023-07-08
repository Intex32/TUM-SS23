package gad.avl;

public class AVLTreeNode {
    private int key;
    private int balance = 0;
    AVLTreeNode left = null;
    AVLTreeNode right = null;

    public AVLTreeNode(int key) {
        this.key = key;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public int getBalance() {
        return balance;
    }

    public int getKey() {
        return key;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
        //setBalance((getRight() == null ? 0 : getRight().height()) - (getLeft() == null ? 0 : getLeft().height()));
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
        //setBalance((getRight() == null ? 0 : getRight().height()) - (getLeft() == null ? 0 : getLeft().height()));
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int height() {
        return height(this);
    }

    public int height(AVLTreeNode node) {
        if(node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @param sb der StringBuilder für die Ausgabe
     */
    public void dot(StringBuilder sb) {
        dotNode(sb, 0);
    }

    private int dotNode(StringBuilder sb, int idx) {
        sb.append(String.format("\t%d [label=\"%d, b=%d\"];%n", idx, key, balance));
        int next = idx + 1;
        if (left != null) {
            next = left.dotLink(sb, idx, next, "l");
        }
        if (right != null) {
            next = right.dotLink(sb, idx, next, "r");
        }
        return next;
    }

    private int dotLink(StringBuilder sb, int idx, int next, String label) {
        sb.append(String.format("\t%d -> %d [label=\"%s\"];%n", idx, next, label));
        return dotNode(sb, next);
    }
}