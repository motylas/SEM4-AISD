public class Node {
    private int value;
    private Node leftNode;
    private Node rightNode;
    private Node parentNode;

    public Node(int value, Node parentNode) {
        this.value = value;
        leftNode = null;
        rightNode = null;
        this.parentNode = parentNode;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
        if (leftNode != null) leftNode.setParentNode(this);
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
        if (rightNode != null) rightNode.setParentNode(this);
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public boolean hasLeft() {
        return leftNode != null;
    }

    public boolean hasRight() {
        return rightNode != null;
    }
}