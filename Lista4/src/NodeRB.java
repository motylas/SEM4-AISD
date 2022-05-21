public class NodeRB extends Node{
    boolean red;

    public NodeRB(int value, Node parentNode, boolean color) {
        super(value, parentNode);
        red = color;
    }
}