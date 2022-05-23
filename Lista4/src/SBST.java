import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SBST {
    private NodeBST root;
    public SBST(){
        root = null;
    }
    public SBST(NodeBST root){
        this.root = root;
    }
    public void insert(int value){
        if (root == null){
            root = new NodeBST(value, null);
            return;
        }
        splay(value, root);
        int rootValue = root.getValue();
        if (value == rootValue) {
            System.out.println("Value already in Tree!");
            return;
        }
        NodeBST newNode = new NodeBST(value, null);
        if (value < rootValue){
            newNode.setLeftNode(root.getLeftNode());
            root.setLeftNode(null);
            newNode.setRightNode(root);
        }
        else{
            newNode.setRightNode(root.getRightNode());
            root.setRightNode(null);
            newNode.setLeftNode(root);
        }
        root = newNode;
    }

    private void splay(int value, NodeBST currentNode){
        int currentNodeValue = currentNode.getValue();
        if (value < currentNodeValue && currentNode.hasLeft())  splay(value, currentNode.getLeftNode());
        else if (value > currentNodeValue && currentNode.hasRight())     splay(value, currentNode.getRightNode());
        else{
            NodeBST parent, grandParent;
            while (currentNode != root) {
                parent = currentNode.getParentNode();
                if (parent == root) {
                    if (currentNode == parent.getLeftNode()) rightRotation(parent);
                    else leftRotation(parent);
                } else {
                    grandParent = parent.getParentNode();
                    // Left X Case
                    if (parent == grandParent.getLeftNode()){
                        // Left Left Case
                        if (currentNode == parent.getLeftNode()){
                            rightRotation(grandParent);
                            rightRotation(parent);
                        }
                        // Left Right Case
                        else{
                            leftRotation(parent);
                            rightRotation(grandParent);
                        }
                    }
                    // Right X case
                    else{
                        // Right Left Case
                        if (currentNode == parent.getLeftNode()){
                            rightRotation(parent);
                            leftRotation(grandParent);
                        }
                        // Right Right Case
                        else{
                            leftRotation(grandParent);
                            leftRotation(parent);
                        }
                    }
                }
            }
        }
    }

    private void leftRotation(NodeBST currentNode) {
        NodeBST parent = currentNode.getParentNode();
        NodeBST rightCurrent = currentNode.getRightNode();
        NodeBST leftOfRightCurrent = rightCurrent.getLeftNode();
        int value = currentNode.getValue();
        // Change parent
        // Current node not root
        if (parent != null) {
            if (parent.hasLeft() && value == parent.getLeftNode().getValue()) {
                parent.setLeftNode(rightCurrent);
            } else if (parent.hasRight()) {
                parent.setRightNode(rightCurrent);
            }
        }
        // Current node root
        else root = rightCurrent;
        rightCurrent.setParentNode(parent);
        // Change current node and right child relations
        currentNode.setParentNode(rightCurrent);
        rightCurrent.setLeftNode(currentNode);
        // Change left child of rightNode to child of currentNode
        currentNode.setRightNode(leftOfRightCurrent);
    }

    private void rightRotation(NodeBST currentNode) {
        NodeBST parent = currentNode.getParentNode();
        NodeBST leftCurrent = currentNode.getLeftNode();
        NodeBST rightOfLeftCurrent = leftCurrent.getRightNode();
        int value = currentNode.getValue();
        // Change parent
        if (parent != null) {
            if (parent.hasLeft() && value == parent.getLeftNode().getValue()) {
                parent.setLeftNode(leftCurrent);
            } else if (parent.hasRight()) {
                parent.setRightNode(leftCurrent);
            }
        } else root = leftCurrent;
        leftCurrent.setParentNode(parent);
        // Change current node and left child relations
        currentNode.setParentNode(leftCurrent);
        leftCurrent.setRightNode(currentNode);
        // Change right child of leftNode to child of currentNode
        currentNode.setLeftNode(rightOfLeftCurrent);
    }

    public void delete(int value){
        if (root == null){
            System.out.println("No value found in the tree!");
            return;
        }
        splay(value, root);
        int rootValue = root.getValue();
        if (value != rootValue){
            System.out.println("No value found in the tree!");
            return;
        }
        if (root.getLeftNode() == null){
            root = root.getRightNode();
            return;
        }
        SBST tree1 = new SBST(root.getLeftNode());
        SBST tree2 = new SBST(root.getRightNode());
        tree1.root.setParentNode(null);
        if (tree2.root != null) tree2.root.setParentNode(null);
        root = null;
        tree1.splay(value, tree1.root);
        tree1.root.setRightNode(tree2.root);
        root = tree1.root;
    }

    public int getHeight() {
        if (root == null) return 0;
        return calculateHeight(root, 1);
    }

    private int calculateHeight(NodeBST currentNode, int height) {
        if (!(currentNode.hasLeft() || currentNode.hasRight())) return height;

        int leftHeight = 0, rightHeight = 0;
        if (currentNode.hasLeft()) leftHeight = calculateHeight(currentNode.getLeftNode(), ++height);
        if (currentNode.hasRight()) rightHeight = calculateHeight(currentNode.getRightNode(), ++height);

        return Math.max(leftHeight, rightHeight);
    }
    public void showTree() {
        printTree(root, "");
    }

    private void printTree(NodeBST node, String prefix) {
        if (node == null) return;

        System.out.println(prefix + " + " + node.getValue());
        printTree(node.getLeftNode(), prefix + " ");
        printTree(node.getRightNode(), prefix + " ");
    }

    public static void main(String[] args) {
        if (args.length < 1) return;
        int amountOfNumbers;
        try {
            amountOfNumbers = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return;
        }

        SBST case1 = new SBST();
        SBST case2 = new SBST();

        // Sorted array, case1
        int[] sortedNumbers = new int[amountOfNumbers];
        Random random = new Random();
        int bound = amountOfNumbers * 2;
        for (int i = 0; i < amountOfNumbers; i++) sortedNumbers[i] = random.nextInt(bound);
        Arrays.sort(sortedNumbers);
        for (int i = 0; i < amountOfNumbers; i++) {
            case1.insert(sortedNumbers[i]);
            //System.out.println("Inserted:  " + sortedNumbers[i]);
            //case1.showTree();
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case1.delete(value);
            //System.out.println("Deleted:  " + value);
            //case1.showTree();
        }

        System.out.println("XXXXXXXXXXXXXXXX");

        // Case 2
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case2.insert(value);
            //System.out.println("Inserted:  " + value);
            //case2.showTree();
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case2.delete(value);
            //System.out.println("Deleted:  " + value);
            //case2.showTree();
        }
    }
}