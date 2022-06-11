import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RBBST {
    private NodeRB root;
    private long comparisonBetweenValues = 0;
    private long MAXcomparisonBetweenValues = 0;
    private long readsAndSwapsOnNodes = 0;
    private long MAXreadsAndSwapsOnNodes = 0;
    private long allHeight = 0;

    public NodeRB insert(int value) {
        clearValues();
        readsAndSwapsOnNodes++; // root ==
        if (root == null) {
            readsAndSwapsOnNodes++; // root =
            root = new NodeRB(value, null, false);
            returnOperationCalculations();
            return root;
        }
        readsAndSwapsOnNodes++; // currentNode =
        NodeRB currentNode = insertRec(value, root);
        readsAndSwapsOnNodes++; // current ==
        if (currentNode == null) {
            returnOperationCalculations();
            return null;
        }
        checkForViolations(currentNode);
        returnOperationCalculations();
        return currentNode;
    }

    private void clearValues() {
        comparisonBetweenValues = 0;
        readsAndSwapsOnNodes = 0;
    }

    private void returnOperationCalculations() {
        if (readsAndSwapsOnNodes > MAXreadsAndSwapsOnNodes) {
            MAXreadsAndSwapsOnNodes = readsAndSwapsOnNodes;
        }
        if (comparisonBetweenValues > MAXcomparisonBetweenValues) {
            MAXcomparisonBetweenValues = comparisonBetweenValues;
        }
    }

    protected NodeRB insertRec(int value, NodeRB currentNode) {
        while (true) {
            int currentNodeValue = currentNode.getValue();
            comparisonBetweenValues++; // value ==
            if (value == currentNodeValue) {
                return null;
            } else if (value < currentNodeValue) {
                comparisonBetweenValues++; // value <
                readsAndSwapsOnNodes++; //if node.left
                if (currentNode.hasLeft()) {
                    readsAndSwapsOnNodes += 2;
                    currentNode = currentNode.getLeftNode();
                } else {
                    readsAndSwapsOnNodes += 2; // setLeft
                    currentNode.setLeftNode(new NodeRB(value, currentNode, true));
                    readsAndSwapsOnNodes++; //getLeft
                    return currentNode.getLeftNode();
                }
            } else {
                comparisonBetweenValues++; // else if before this
                readsAndSwapsOnNodes++; // node.right
                if (currentNode.hasRight()) {
                    readsAndSwapsOnNodes += 2;
                    currentNode = currentNode.getRightNode();
                } else {
                    readsAndSwapsOnNodes += 2; // setRight
                    currentNode.setRightNode(new NodeRB(value, currentNode, true));
                    readsAndSwapsOnNodes++; // getRight
                    return currentNode.getRightNode();
                }
            }
        }
    }

    private void checkForViolations(NodeRB currentNode) {
        while (currentNode.getParentNode() != null && currentNode.getParentNode().isRed()) {
            readsAndSwapsOnNodes += 2; /// get Parent twice
            readsAndSwapsOnNodes += 4; //getParent x3 get left x1
            if (currentNode.getParentNode() == currentNode.getParentNode().getParentNode().getLeftNode()) {
                readsAndSwapsOnNodes += 4; // uncle = and getParent x2 getRight x1
                NodeRB uncle = currentNode.getParentNode().getParentNode().getRightNode();
                readsAndSwapsOnNodes += 2; //uncle twice
                if (uncle != null && uncle.isRed()) {
                    readsAndSwapsOnNodes++; //getParent
                    currentNode.getParentNode().setColor(false);
                    uncle.setColor(false);
                    readsAndSwapsOnNodes += 2; // getParent twice
                    currentNode.getParentNode().getParentNode().setColor(true);
                    readsAndSwapsOnNodes += 3; // currentNode = and getParent x2
                    currentNode = currentNode.getParentNode().getParentNode();
                } else {
                    readsAndSwapsOnNodes += 3; // == and get x2
                    if (currentNode == currentNode.getParentNode().getRightNode()) {
                        readsAndSwapsOnNodes += 2; // = and get
                        currentNode = currentNode.getParentNode();
                        leftRotation(currentNode);
                    }
                    readsAndSwapsOnNodes++; // get
                    currentNode.getParentNode().setColor(false);
                    readsAndSwapsOnNodes += 2; // get x2
                    currentNode.getParentNode().getParentNode().setColor(true);
                    readsAndSwapsOnNodes += 2; // get x2
                    rightRotation(currentNode.getParentNode().getParentNode());
                }
            } else {
                readsAndSwapsOnNodes += 4; // = and get x3
                NodeRB uncle = currentNode.getParentNode().getParentNode().getLeftNode();
                readsAndSwapsOnNodes++; // != null
                if (uncle != null && uncle.isRed()) {
                    readsAndSwapsOnNodes++; // getParent
                    currentNode.getParentNode().setColor(false);
                    uncle.setColor(false);
                    readsAndSwapsOnNodes += 2; //get x2
                    currentNode.getParentNode().getParentNode().setColor(true);
                    readsAndSwapsOnNodes += 3; //= and getx2
                    currentNode = currentNode.getParentNode().getParentNode();
                } else {
                    readsAndSwapsOnNodes += 3; //== and get x2
                    if (currentNode == currentNode.getParentNode().getLeftNode()) {
                        readsAndSwapsOnNodes += 2; // = and get
                        currentNode = currentNode.getParentNode();
                        rightRotation(currentNode);
                    }
                    readsAndSwapsOnNodes++; // get
                    currentNode.getParentNode().setColor(false);
                    readsAndSwapsOnNodes += 2; //get x2
                    currentNode.getParentNode().getParentNode().setColor(true);
                    readsAndSwapsOnNodes += 2; // get x2
                    leftRotation(currentNode.getParentNode().getParentNode());
                }
            }
        }
        root.setColor(false);
    }

    private void leftRotation(NodeRB currentNode) {
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB parent = currentNode.getParentNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB rightCurrent = currentNode.getRightNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB leftOfRightCurrent = rightCurrent.getLeftNode();
        int value = currentNode.getValue();
        // Change parent
        // Current node not root
        readsAndSwapsOnNodes++; // parent !=
        if (parent != null) {
            readsAndSwapsOnNodes += 2; // hasLeft and get
            comparisonBetweenValues++; // value ==
            if (parent.hasLeft() && value == parent.getLeftNode().getValue()) {
                readsAndSwapsOnNodes += 2; // set
                parent.setLeftNode(rightCurrent);
            } else if (parent.hasRight()) {
                readsAndSwapsOnNodes++; // hasRight
                readsAndSwapsOnNodes += 2; //set
                parent.setRightNode(rightCurrent);
            }
        }
        // Current node root
        else {
            readsAndSwapsOnNodes++; // root =
            root = rightCurrent;
        }
        readsAndSwapsOnNodes += 2; //set
        rightCurrent.setParentNode(parent);
        // Change current node and right child relations
        readsAndSwapsOnNodes += 2; //set
        currentNode.setParentNode(rightCurrent);
        readsAndSwapsOnNodes += 2; //set
        rightCurrent.setLeftNode(currentNode);
        // Change left child of rightNode to child of currentNode
        readsAndSwapsOnNodes += 2; //set
        currentNode.setRightNode(leftOfRightCurrent);
    }

    private void rightRotation(NodeRB currentNode) {
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB parent = currentNode.getParentNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB leftCurrent = currentNode.getLeftNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB rightOfLeftCurrent = leftCurrent.getRightNode();
        int value = currentNode.getValue();
        // Change parent
        readsAndSwapsOnNodes++; // parent !=
        if (parent != null) {
            readsAndSwapsOnNodes += 2; // hasLeft and get
            comparisonBetweenValues++; // value ==
            if (parent.hasLeft() && value == parent.getLeftNode().getValue()) {
                readsAndSwapsOnNodes += 2; // set
                parent.setLeftNode(leftCurrent);
            } else if (parent.hasRight()) {
                readsAndSwapsOnNodes++; // hasRight
                readsAndSwapsOnNodes += 2; //set
                parent.setRightNode(leftCurrent);
            }
        } else {
            readsAndSwapsOnNodes++; // root =
            root = leftCurrent;
        }
        readsAndSwapsOnNodes += 2; //set
        leftCurrent.setParentNode(parent);
        // Change current node and left child relations
        readsAndSwapsOnNodes += 2; //set
        currentNode.setParentNode(leftCurrent);
        readsAndSwapsOnNodes += 2; //set
        leftCurrent.setRightNode(currentNode);
        // Change right child of leftNode to child of currentNode
        readsAndSwapsOnNodes += 2; //set
        currentNode.setLeftNode(rightOfLeftCurrent);
    }

    public NodeRB delete(int value) {
        clearValues();
        if (root == null) {

            return null;
        }
        return deleteRec(value, root);
    }

    private NodeRB deleteRec(int value, NodeRB currentNode) {
        while (true) {
            int currentNodeValue = currentNode.getValue();
            comparisonBetweenValues++; // value ==
            if (value == currentNodeValue) {
                readsAndSwapsOnNodes += 2; // hasLeft, hasRight
                if (currentNode.hasLeft() && currentNode.hasRight()) {
                    delete2(currentNode);
                } else if (currentNode.hasLeft() || currentNode.hasRight()) {
                    readsAndSwapsOnNodes += 2; //hasLeft hasRight
                    delete1(currentNode);
                } else {
                    readsAndSwapsOnNodes += 2; //else if above
                    delete0(currentNode);
                }
                returnOperationCalculations();
                return currentNode;
            } else if (value < currentNodeValue) {
                comparisonBetweenValues++; // value <
                readsAndSwapsOnNodes++; // hasLeft
                if (!currentNode.hasLeft()) {
                    returnOperationCalculations();
                    return null;
                } else {
                    readsAndSwapsOnNodes += 2; // getLeftNode and current =
                    currentNode = currentNode.getLeftNode();
                }
            } else {
                comparisonBetweenValues++; // else if above
                readsAndSwapsOnNodes++; // hasRight
                if (!currentNode.hasRight()) {
                    returnOperationCalculations();
                    return null;
                } else {
                    readsAndSwapsOnNodes += 2; // getRight and current =
                    currentNode = currentNode.getRightNode();
                }
            }
        }
    }

    private void delete0(NodeRB currentNode) {
        readsAndSwapsOnNodes += 2; // = and get
        NodeRB parent = currentNode.getParentNode();
        readsAndSwapsOnNodes++; // ==
        if (parent == null) {
            readsAndSwapsOnNodes++; // root =
            root = null;
        } else {
            readsAndSwapsOnNodes += 2; // get and ==
            if (parent.getLeftNode() == currentNode) {
                readsAndSwapsOnNodes += 2; //set
                parent.setLeftNode(null);
            } else {
                readsAndSwapsOnNodes += 2; //set
                parent.setRightNode(null);
            }
            if (!currentNode.isRed()) {
                doubleBlack(null, parent);
            }
        }
    }

    private void delete1(NodeRB currentNode) {
        readsAndSwapsOnNodes += 2; //parent and get
        NodeRB parent = currentNode.getParentNode();
        readsAndSwapsOnNodes += 3; //hasLeft child and get
        NodeRB child = currentNode.hasLeft() ? currentNode.getLeftNode() : currentNode.getRightNode();
        readsAndSwapsOnNodes++; //==
        if (parent == null) {
            readsAndSwapsOnNodes++; // root =
            root = child;
            readsAndSwapsOnNodes += 2; //set
            child.setParentNode(null);
        } else {
            readsAndSwapsOnNodes += 2; //get and ==
            if (parent.getLeftNode() == currentNode) {
                readsAndSwapsOnNodes += 2; //set
                parent.setLeftNode(child);
            } else {
                readsAndSwapsOnNodes += 2; //set
                parent.setRightNode(child);
            }
            readsAndSwapsOnNodes += 2; //set
            child.setParentNode(parent);
            child.setColor(false);
            if (!currentNode.isRed() && !child.isRed()) {
                doubleBlack(child, parent);
            }
        }
    }

    private void delete2(NodeRB currentNode) {
        readsAndSwapsOnNodes += 2; // parent = and getLeft
        NodeRB parentOfMax = findParentMax(currentNode.getLeftNode(), currentNode);
        NodeRB maxNode;
        readsAndSwapsOnNodes++; // currentNode ==
        if (currentNode == parentOfMax) {
            readsAndSwapsOnNodes += 2; //maxNode = and getLeft
            maxNode = currentNode.getLeftNode();
        } else {
            readsAndSwapsOnNodes += 2; //maxNode = and getRight
            maxNode = parentOfMax.getRightNode();
        }
        int maxNodeValue = maxNode.getValue();
        readsAndSwapsOnNodes += 2; //set
        currentNode.setValue(maxNodeValue);
        readsAndSwapsOnNodes++; // hasLeft
        if (maxNode.hasLeft()) {
            delete1(maxNode);
        } else {
            delete0(maxNode);
        }
    }

    private NodeRB findParentMax(NodeRB currentNode, NodeRB parentNode) {
        readsAndSwapsOnNodes++; // hasRight
        if (currentNode.hasRight()) {
            readsAndSwapsOnNodes++; //getRight
            return findParentMax(currentNode.getRightNode(), currentNode);
        } else {
            return parentNode;
        }
    }

    private void doubleBlack(NodeRB doubleBlackNode, NodeRB parent) {
        while (doubleBlackNode != root) {
            readsAndSwapsOnNodes++; //while
            readsAndSwapsOnNodes++; //!=
            if (doubleBlackNode != null) {
                readsAndSwapsOnNodes += 2; //= and get
                parent = doubleBlackNode.getParentNode();
            }
            readsAndSwapsOnNodes += 4; //= and get and == and get
            NodeRB sibling = parent.getRightNode() == doubleBlackNode ? parent.getLeftNode() : parent.getRightNode();
            // a or b sibling black
            readsAndSwapsOnNodes++; // ==
            if (sibling == null || !sibling.isRed()) {
                // a at least one child red
                readsAndSwapsOnNodes += 5; // 2x has 2x get and != null
                if (sibling != null && ((sibling.hasLeft() && sibling.getLeftNode().isRed()) || (sibling.hasRight() && sibling.getRightNode().isRed()))) {
                    // Left X case
                    readsAndSwapsOnNodes += 2; // == and get
                    if (sibling == parent.getLeftNode()) {
                        // Left Left Case
                        readsAndSwapsOnNodes += 2; // hasLeft and getLeft
                        if (sibling.hasLeft() && sibling.getLeftNode().isRed()) {
                            rightRotation(parent);
                            readsAndSwapsOnNodes++; // getLeft
                            sibling.getLeftNode().setColor(false);
                        }
                        // Left Right Case
                        else {
                            readsAndSwapsOnNodes += 2; // = and get
                            NodeRB rightChild = sibling.getRightNode();
                            rightChild.setColor(false);
                            leftRotation(sibling);
                            rightRotation(rightChild);
                        }
                    }
                    // Right X case
                    else {
                        // Right Right Case
                        readsAndSwapsOnNodes += 2; // has and get
                        if (sibling.hasRight() && sibling.getRightNode().isRed()) {
                            leftRotation(parent);
                            readsAndSwapsOnNodes++; //get
                            sibling.getRightNode().setColor(false);
                        }
                        // Right Left Case
                        else {
                            readsAndSwapsOnNodes += 2; // = and get
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
                    readsAndSwapsOnNodes++; // ==
                    if (sibling == null) return;
                    sibling.setColor(true);
                    if (parent.isRed()) {
                        parent.setColor(false);
                        return;
                    }
                    readsAndSwapsOnNodes++; // =
                    doubleBlackNode = parent;
                }
            }
            // c sibling red
            else {
                parent.setColor(true);
                sibling.setColor(false);
                // Left case
                readsAndSwapsOnNodes += 2; // == and get
                if (sibling == parent.getLeftNode()) {
                    rightRotation(parent);
                    readsAndSwapsOnNodes += 2; // = and get
                    doubleBlackNode = parent.getRightNode();
                }
                // Right case
                else {
                    leftRotation(parent);
                    readsAndSwapsOnNodes += 2; // = and get
                    doubleBlackNode = parent.getLeftNode();
                }
            }
        }
    }

    int treeHeight() {
        NodeRB node = root;
        // Base Case
        if (node == null)
            return 0;

        // Create an empty queue for level order traversal
        Queue<NodeRB> q = new LinkedList();

        // Enqueue Root and initialize height
        q.add(node);
        int height = 0;

        while (true) {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                NodeRB newnode = q.peek();
                q.remove();
                if (newnode.getLeftNode() != null)
                    q.add(newnode.getLeftNode());
                if (newnode.getRightNode() != null)
                    q.add(newnode.getRightNode());
                nodeCount--;
            }
        }
    }

    public void showTree() {
        printTree(root, "");
    }

    private void printTree(NodeRB node, String prefix) {
        if (node == null) {
            return;
        }
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

        RBBST case1 = new RBBST();
        RBBST case2 = new RBBST();

        // Sorted array, case1
        int[] sortedNumbers = new int[amountOfNumbers];
        Random random = new Random();
        int bound = amountOfNumbers * 2;
        for (int i = 0; i < amountOfNumbers; i++) sortedNumbers[i] = random.nextInt(bound);
        Arrays.sort(sortedNumbers);
        for (int i = 0; i < amountOfNumbers; i++) {
            NodeRB temp = case1.insert(sortedNumbers[i]);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("Value already exist in tree");
            else {
                System.out.println("Inserted:  " + sortedNumbers[i]);
                case1.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeRB temp = case1.delete(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("No value found in tree");
            else {
                System.out.println("Deleted:  " + value);
                case1.showTree();
            }
        }

        System.out.println("XXXXXXXXXXXXXXXX");

        // Case 2
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeRB temp = case2.insert(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("Value already exist in tree");
            else {
                System.out.println("Inserted:  " + value);
                case2.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeRB temp = case2.delete(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("No value found in tree");
            else {
                System.out.println("Deleted:  " + value);
                case2.showTree();
            }
        }
    }

    public long getComparisonBetweenValues() {
        return comparisonBetweenValues;
    }

    public long getReadsAndSwapsOnNodes() {
        return readsAndSwapsOnNodes;
    }

    public long getMAXcomparisonBetweenValues() {
        return MAXcomparisonBetweenValues;
    }

    public long getMAXreadsAndSwapsOnNodes() {
        return MAXreadsAndSwapsOnNodes;
    }
}