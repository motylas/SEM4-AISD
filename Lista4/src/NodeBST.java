public class NodeBST{
    private int value;
    private NodeBST leftNode;
    private NodeBST rightNode;
    private NodeBST parentNode;

    public NodeBST(int value, NodeBST parentNode) {
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

    public NodeBST getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeBST leftNode) {
        this.leftNode = leftNode;
        if (leftNode != null) leftNode.setParentNode(this);
    }

    public NodeBST getRightNode() {
        return rightNode;
    }


    public void setRightNode(NodeBST rightNode) {
        this.rightNode = rightNode;
        if (rightNode != null) rightNode.setParentNode(this);
    }

    public NodeBST getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeBST parentNode) {
        this.parentNode = parentNode;
    }

    public boolean hasLeft() {
        return leftNode != null;
    }

    public boolean hasRight() {
        return rightNode != null;
    }
}