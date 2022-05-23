import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RBBST {
    private NodeRB root;

    public void insert(int value) {
        if (root == null) {
            root = new NodeRB(value, null, false);
            return;
        }
        NodeRB currentNode = insertRec(value, root);
        if (currentNode == null) return;
        checkForViolations(currentNode);
    }

    protected NodeRB insertRec(int value, NodeRB currentNode) {
        int currentNodeValue = currentNode.getValue();
        if (value == currentNodeValue) {
            System.out.println("Value already in tree!");
            return null;
        } else if (value < currentNodeValue) {
            if (currentNode.hasLeft()) {
                return insertRec(value, currentNode.getLeftNode());
            } else {
                NodeRB newNode = new NodeRB(value, currentNode, true);
                currentNode.setLeftNode(newNode);
                return newNode;
            }
        } else {
            if (currentNode.hasRight()) {
                return insertRec(value, currentNode.getRightNode());
            } else {
                NodeRB newNode = new NodeRB(value, currentNode, true);
                currentNode.setRightNode(newNode);
                return newNode;
            }
        }
    }

    private void checkForViolations(NodeRB currentNode) {
        while (currentNode.getParentNode() != null && currentNode.getParentNode().isRed()) {
            if (currentNode.getParentNode() == currentNode.getParentNode().getParentNode().getLeftNode()) {
                NodeRB uncle = currentNode.getParentNode().getParentNode().getRightNode();
                if (uncle != null && uncle.isRed()) {
                    currentNode.getParentNode().setColor(false);
                    uncle.setColor(false);
                    currentNode.getParentNode().getParentNode().setColor(true);
                    currentNode = currentNode.getParentNode().getParentNode();
                } else {
                    if (currentNode == currentNode.getParentNode().getRightNode()) {
                        currentNode = currentNode.getParentNode();
                        leftRotation(currentNode);
                    }
                    currentNode.getParentNode().setColor(false);
                    currentNode.getParentNode().getParentNode().setColor(true);
                    rightRotation(currentNode.getParentNode().getParentNode());
                }
            } else {
                NodeRB uncle = currentNode.getParentNode().getParentNode().getLeftNode();
                if (uncle != null && uncle.isRed()) {
                    currentNode.getParentNode().setColor(false);
                    uncle.setColor(false);
                    currentNode.getParentNode().getParentNode().setColor(true);
                    currentNode = currentNode.getParentNode().getParentNode();
                } else {
                    if (currentNode == currentNode.getParentNode().getLeftNode()) {
                        currentNode = currentNode.getParentNode();
                        rightRotation(currentNode);
                    }
                    currentNode.getParentNode().setColor(false);
                    currentNode.getParentNode().getParentNode().setColor(true);
                    leftRotation(currentNode.getParentNode().getParentNode());
                }
            }
        }
        root.setColor(false);
    }

    private void leftRotation(NodeRB currentNode) {
        NodeRB parent = currentNode.getParentNode();
        NodeRB rightCurrent = currentNode.getRightNode();
        NodeRB leftOfRightCurrent = rightCurrent.getLeftNode();
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

    private void rightRotation(NodeRB currentNode) {
        NodeRB parent = currentNode.getParentNode();
        NodeRB leftCurrent = currentNode.getLeftNode();
        NodeRB rightOfLeftCurrent = leftCurrent.getRightNode();
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

    public void delete(int value) {
        if (root == null) {
            System.out.println("No value found in the tree!");
            return;
        }
        deleteRec(value, root);
    }

    private void deleteRec(int value, NodeRB currentNode) {
        int currentNodeValue = currentNode.getValue();
        if (value == currentNodeValue) {
            if (currentNode.hasLeft() && currentNode.hasRight()) delete2(currentNode);
            else if (currentNode.hasLeft() || currentNode.hasRight()) delete1(value, currentNode);
            else delete0(value, currentNode);
        } else if (value < currentNodeValue) {
            if (!currentNode.hasLeft()) System.out.println("No value found in the tree!");
            else deleteRec(value, currentNode.getLeftNode());
        } else {
            if (!currentNode.hasRight()) System.out.println("No value found in the tree!");
            else deleteRec(value, currentNode.getRightNode());
        }
    }

    private void delete0(int value, NodeRB currentNode) {
        NodeRB parent = currentNode.getParentNode();
        if (parent == null) root = null;
        else {
            if (parent.getLeftNode() == currentNode) parent.setLeftNode(null);
            else parent.setRightNode(null);
            if (!currentNode.isRed()) doubleBlack(null, parent);
        }
    }

    private void delete1(int value, NodeRB currentNode) {
        NodeRB parent = currentNode.getParentNode();
        NodeRB child = currentNode.hasLeft() ? currentNode.getLeftNode() : currentNode.getRightNode();
        if (parent == null) {
            root = child;
            child.setParentNode(null);
        } else {
            if (parent.getLeftNode() == currentNode) parent.setLeftNode(child);
            else parent.setRightNode(child);
            child.setParentNode(parent);
            child.setColor(false);
            if (!currentNode.isRed() && !child.isRed()) doubleBlack(child, parent);
        }
    }

    private void delete2(NodeRB currentNode) {
        NodeRB parentOfMax = findParentMax(currentNode.getLeftNode(), currentNode);
        NodeRB maxNode;
        if (currentNode == parentOfMax) maxNode = currentNode.getLeftNode();
        else maxNode = parentOfMax.getRightNode();
        int maxNodeValue = maxNode.getValue();
        currentNode.setValue(maxNodeValue);
        if (maxNode.hasLeft()) delete1(maxNodeValue, maxNode);
        else delete0(maxNodeValue, maxNode);
    }

    private NodeRB findParentMax(NodeRB currentNode, NodeRB parentNode) {
        if (currentNode.hasRight()) return findParentMax(currentNode.getRightNode(), currentNode);
        else return parentNode;
    }

    private void doubleBlack(NodeRB doubleBlackNode, NodeRB parent) {
        while (doubleBlackNode != root) {
            if (doubleBlackNode != null) parent = doubleBlackNode.getParentNode();
            NodeRB sibling = parent.getRightNode() == doubleBlackNode ? parent.getLeftNode() : parent.getRightNode();
            // a or b sibling black
            if (sibling == null || !sibling.isRed()) {
                // a at least one child red
                if (sibling != null && ((sibling.hasLeft() && sibling.getLeftNode().isRed()) || (sibling.hasRight() && sibling.getRightNode().isRed()))) {
                    // Left X case
                    if (sibling == parent.getLeftNode()) {
                        // Left Left Case
                        if (sibling.hasLeft() && sibling.getLeftNode().isRed()) {
                            rightRotation(parent);
                            sibling.getLeftNode().setColor(false);
                        }
                        // Left Right Case
                        else {
                            NodeRB rightChild = sibling.getRightNode();
                            rightChild.setColor(false);
                            leftRotation(sibling);
                            rightRotation(rightChild);
                        }
                    }
                    // Right X case
                    else {
                        // Right Right Case
                        if (sibling.hasRight() && sibling.getRightNode().isRed()) {
                            leftRotation(parent);
                            sibling.getRightNode().setColor(false);
                        }
                        // Right Left Case
                        else {
                            NodeRB leftChild = sibling.getLeftNode();
                            leftChild.setColor(false);
                            rightRotation(sibling);
                            leftRotation(leftChild);
                        }
                    }
                    return;
                }
                // b If sibling is Black and its both children are black
                else {
                    if (sibling == null) return;
                    sibling.setColor(true);
                    if (parent.isRed()) {
                        parent.setColor(false);
                        return;
                    }
                    doubleBlackNode = parent;
                }
            }
            // c sibling red
            else {
                parent.setColor(true);
                sibling.setColor(false);
                // Left case
                if (sibling == parent.getLeftNode()) {
                    rightRotation(parent);
                    doubleBlackNode = parent.getRightNode();
                }
                // Right case
                else {
                    leftRotation(parent);
                    doubleBlackNode = parent.getLeftNode();
                }
            }
        }
    }

    public int getHeight() {
        if (root == null) return 0;
        return calculateHeight(root, 1);
    }

    private int calculateHeight(NodeRB currentNode, int height) {
        if (!(currentNode.hasLeft() || currentNode.hasRight())) return height;

        int leftHeight = 0, rightHeight = 0;
        if (currentNode.hasLeft()) leftHeight = calculateHeight(currentNode.getLeftNode(), ++height);
        if (currentNode.hasRight()) rightHeight = calculateHeight(currentNode.getRightNode(), ++height);

        return Math.max(leftHeight, rightHeight);
    }

    public void showTree() {
        printTree(root, "", 0);
    }

    private void printTree(NodeRB node, String prefix, int timesBlack) {
        if (node == null) {
//            System.out.println("TimesBlack: " + ++timesBlack);
            return;
        }
//        if (!node.isRed()) timesBlack++;
//        String color = node.isRed() ? "r" : "b";
//        System.out.println(prefix + " + " + node.getValue() + color);
        System.out.println(prefix + " + " + node.getValue());
        printTree(node.getLeftNode(), prefix + " ", timesBlack);
        printTree(node.getRightNode(), prefix + " ", timesBlack);
    }

    public static void main(String[] args) {
        if (args.length < 1) return;
        int amountOfNumbers;
        try {
            amountOfNumbers = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return;
        }

        RBBST case1 = new RBBST();
        RBBST case2 = new RBBST();

        // Sorted array, case1
        int[] sortedNumbers = new int[amountOfNumbers];
        Random random = new Random();
        int bound = amountOfNumbers * 2;
        for (int i = 0; i < amountOfNumbers; i++) sortedNumbers[i] = random.nextInt(bound);
        Arrays.sort(sortedNumbers);
        for (int i = 0; i < amountOfNumbers; i++) {
            case1.insert(sortedNumbers[i]);
            System.out.println("Inserted:  " + sortedNumbers[i]);
            case1.showTree();
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case1.delete(value);
            System.out.println("Deleted:  " + value);
            case1.showTree();
        }

        System.out.println("XXXXXXXXXXXXXXXX");

        // Case 2
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case2.insert(value);
            System.out.println("Inserted:  " + value);
            case2.showTree();
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            case2.delete(value);
            System.out.println("Deleted:  " + value);
            case2.showTree();
        }
    }
}