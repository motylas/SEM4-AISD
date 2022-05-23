public class NodeRB {
    private int value;
    private NodeRB leftNode;
    private NodeRB rightNode;
    private NodeRB parentNode;

    // false = black
    // true = red
    private boolean red;

    public NodeRB(int value, NodeRB parentNode, boolean color) {
        this.value = value;
        leftNode = null;
        rightNode = null;
        this.parentNode = parentNode;
        red = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeRB getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeRB leftNode) {
        this.leftNode = leftNode;
        if (leftNode != null) leftNode.setParentNode(this);
    }

    public NodeRB getRightNode() {
        return rightNode;
    }


    public void setRightNode(NodeRB rightNode) {
        this.rightNode = rightNode;
        if (rightNode != null) rightNode.setParentNode(this);
    }

    public NodeRB getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeRB parentNode) {
        this.parentNode = parentNode;
    }

    public NodeRB getSibling(){
        if (parentNode != null){
            if (parentNode.leftNode != this) return parentNode.leftNode;
            else return parentNode.rightNode;
        }
        return null;
    }

    public boolean hasLeft() {
        return leftNode != null;
    }

    public boolean hasRight() {
        return rightNode != null;
    }

    public void setColor(boolean color) {
        red = color;
    }

    public boolean isRed() {
        return red;
    }
}