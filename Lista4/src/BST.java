import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BST {
    protected NodeBST root;

    public BST() {
        root = null;
    }

    public NodeBST insert(int value) {
        if (root == null) {
            root = new NodeBST(value, null);
            return root;
        }
        return insertRec(value, root);
    }

    protected NodeBST insertRec(int value, NodeBST currentNode) {
        while (true) {
            int currentNodeValue = currentNode.getValue();
            if (value == currentNodeValue) return null;
            else if (value < currentNodeValue) {
                if (currentNode.hasLeft()) currentNode = currentNode.getLeftNode();
                else {
                    currentNode.setLeftNode(new NodeBST(value, currentNode));
                    return currentNode.getLeftNode();
                }
            } else {
                if (currentNode.hasRight()) currentNode = currentNode.getRightNode();
                else {
                    currentNode.setRightNode(new NodeBST(value, currentNode));
                    return currentNode.getRightNode();
                }
            }
        }
    }

    public NodeBST delete(int value) {
        if (root == null) return null;

        return deleteRec(value, root);
    }

    private NodeBST deleteRec(int value, NodeBST currentNode) {
        while (true) {
            int currentNodeValue = currentNode.getValue();
            if (value == currentNodeValue) {
                if (currentNode.hasLeft() && currentNode.hasRight()) delete2(currentNode);
                else if (currentNode.hasLeft() || currentNode.hasRight()) delete1(value, currentNode);
                else delete0(value, currentNode);
                return currentNode;
            } else if (value < currentNodeValue) {
                if (!currentNode.hasLeft()) return null;
                else currentNode = currentNode.getLeftNode();

            } else {
                if (!currentNode.hasRight()) return null;
                else currentNode = currentNode.getRightNode();
            }
        }
    }

    private void delete0(int value, NodeBST currentNode) {
        NodeBST parentNode = currentNode.getParentNode();
        if (parentNode == null) root = null;
        else if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()) parentNode.setLeftNode(null);
        else parentNode.setRightNode(null);
    }

    private void delete1(int value, NodeBST currentNode) {
        NodeBST parentNode = currentNode.getParentNode();
        if (parentNode == null) root = currentNode;
        else if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()) {
            if (currentNode.hasLeft()) parentNode.setLeftNode(currentNode.getLeftNode());
            else parentNode.setLeftNode(currentNode.getRightNode());
        } else {
            if (currentNode.hasLeft()) parentNode.setRightNode(currentNode.getLeftNode());
            else parentNode.setRightNode(currentNode.getRightNode());
        }
    }

    private void delete2(NodeBST currentNode) {
        NodeBST parentOfMax = findParentMax(currentNode.getLeftNode(), currentNode);
        NodeBST maxNode;
        if (currentNode == parentOfMax) maxNode = currentNode.getLeftNode();
        else maxNode = parentOfMax.getRightNode();
        int maxNodeValue = maxNode.getValue();
        currentNode.setValue(maxNodeValue);
        if (maxNode.hasLeft()) delete1(maxNodeValue, maxNode);
        else delete0(maxNodeValue, maxNode);
    }

    private NodeBST findParentMax(NodeBST currentNode, NodeBST parentNode) {
        if (currentNode.hasRight()) return findParentMax(currentNode.getRightNode(), currentNode);
        else return parentNode;
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

        BST case1 = new BST();
        BST case2 = new BST();

        // Sorted array, case1
        int[] sortedNumbers = new int[amountOfNumbers];
        Random random = new Random();
        int bound = amountOfNumbers * 2;
        for (int i = 0; i < amountOfNumbers; i++) sortedNumbers[i] = random.nextInt(bound);
        Arrays.sort(sortedNumbers);
        for (int i = 0; i < amountOfNumbers; i++) {
            if (case1.insert(sortedNumbers[i]) == null) System.out.println("Value already exist in tree");
            else{
                System.out.println("Inserted:  " + sortedNumbers[i]);
                case1.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            if (case1.delete(value) == null) System.out.println("No value found in tree");
            else{
                System.out.println("Deleted:  " + value);
                case1.showTree();
            }
        }

        System.out.println("XXXXXXXXXXXXXXXX");

        // Case 2
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            if (case2.insert(value) == null) System.out.println("Value already exist in tree");
            else{
                System.out.println("Inserted:  " + value);
                case2.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            if (case2.delete(value) == null) System.out.println("No value found in tree");
            else{
                System.out.println("Deleted:  " + value);
                case2.showTree();
            }
        }
    }
}
