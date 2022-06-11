import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SBST {
    private NodeBST root;
    private long comparisonBetweenValues = 0;
    private long MAXcomparisonBetweenValues = 0;
    private long readsAndSwapsOnNodes = 0;
    private long MAXreadsAndSwapsOnNodes = 0;

    public SBST() {
        root = null;
    }

    public SBST(NodeBST root) {
        this.root = root;
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

    public NodeBST insert(int value) {
        clearValues();
        readsAndSwapsOnNodes++; // ==
        if (root == null) {
            readsAndSwapsOnNodes++; // =
            root = new NodeBST(value, null);
            returnOperationCalculations();
            return root;
        }
        splay(value, root);
        int rootValue = root.getValue();
        comparisonBetweenValues++; // ==
        if (value == rootValue) {
            returnOperationCalculations();
            return null;
        }
        readsAndSwapsOnNodes++; // =
        NodeBST newNode = new NodeBST(value, null);
        comparisonBetweenValues++; // <
        if (value < rootValue) {
            readsAndSwapsOnNodes += 3; // set and get
            newNode.setLeftNode(root.getLeftNode());
            readsAndSwapsOnNodes += 2; //set
            root.setLeftNode(null);
            readsAndSwapsOnNodes += 2; //set
            newNode.setRightNode(root);
        } else {
            readsAndSwapsOnNodes += 3; //set and get
            newNode.setRightNode(root.getRightNode());
            readsAndSwapsOnNodes += 2; //set and get
            root.setRightNode(null);
            readsAndSwapsOnNodes += 2; //set and get
            newNode.setLeftNode(root);
        }
        readsAndSwapsOnNodes++; // =
        root = newNode;
        returnOperationCalculations();
        return root;
    }


    private void splay(int value, NodeBST currentNode) {
        int currentNodeValue = currentNode.getValue();
        comparisonBetweenValues++; // <
        while (true) {
            currentNodeValue = currentNode.getValue();
            comparisonBetweenValues++; // <
            readsAndSwapsOnNodes++; // hasLeft
            if (value < currentNodeValue && currentNode.hasLeft()) {
                readsAndSwapsOnNodes += 2; // = and getLeft
                currentNode = currentNode.getLeftNode();
            } else if (value > currentNodeValue && currentNode.hasRight()) {
                comparisonBetweenValues++; // >
                readsAndSwapsOnNodes++; // hasRight
                readsAndSwapsOnNodes += 2; // = and get
                currentNode = currentNode.getRightNode();
            } else {
                // else if
                comparisonBetweenValues++; // >
                readsAndSwapsOnNodes++; // hasRight
                break;
            }
        }
        NodeBST parent, grandParent;
        while (currentNode != root) {
            readsAndSwapsOnNodes += 2; // = and get
            parent = currentNode.getParentNode();
            readsAndSwapsOnNodes++; // ==
            if (parent == root) {
                readsAndSwapsOnNodes += 2; // == and get
                if (currentNode == parent.getLeftNode()) rightRotation(parent);
                else leftRotation(parent);
            } else {
                readsAndSwapsOnNodes += 2; // = and get
                grandParent = parent.getParentNode();
                // Left X Case
                readsAndSwapsOnNodes += 2; // == and get
                if (parent == grandParent.getLeftNode()) {
                    // Left Left Case
                    readsAndSwapsOnNodes += 2; // == and get
                    if (currentNode == parent.getLeftNode()) {
                        rightRotation(grandParent);
                        rightRotation(parent);
                    }
                    // Left Right Case
                    else {
                        leftRotation(parent);
                        rightRotation(grandParent);
                    }
                }
                // Right X case
                else {
                    // Right Left Case
                    readsAndSwapsOnNodes += 2; // == and get
                    if (currentNode == parent.getLeftNode()) {
                        rightRotation(parent);
                        leftRotation(grandParent);
                    }
                    // Right Right Case
                    else {
                        leftRotation(grandParent);
                        leftRotation(parent);
                    }
                }
            }
        }
    }

    private void leftRotation(NodeBST currentNode) {
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST parent = currentNode.getParentNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST rightCurrent = currentNode.getRightNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST leftOfRightCurrent = rightCurrent.getLeftNode();
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

    private void rightRotation(NodeBST currentNode) {
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST parent = currentNode.getParentNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST leftCurrent = currentNode.getLeftNode();
        readsAndSwapsOnNodes += 2; // = and get
        NodeBST rightOfLeftCurrent = leftCurrent.getRightNode();
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

    public NodeBST delete(int value) {
        clearValues();
        readsAndSwapsOnNodes++; // ==
        if (root == null) {
            returnOperationCalculations();
            return null;
        }
        splay(value, root);
        int rootValue = root.getValue();
        comparisonBetweenValues++; //!=
        if (value != rootValue) {
            returnOperationCalculations();
            return null;
        }
        readsAndSwapsOnNodes += 2; // get and ==
        if (root.getLeftNode() == null) {
            readsAndSwapsOnNodes += 2; // = and get
            root = root.getRightNode();
            readsAndSwapsOnNodes += 2; // set
            root.setParentNode(null);
            returnOperationCalculations();
            return root;
        }
        readsAndSwapsOnNodes += 2; // = and get
        SBST tree1 = new SBST(root.getLeftNode());
        readsAndSwapsOnNodes += 2; // = and get
        SBST tree2 = new SBST(root.getRightNode());
        readsAndSwapsOnNodes += 2; // set
        tree1.root.setParentNode(null);
        readsAndSwapsOnNodes++; //!=
        if (tree2.root != null) {
            readsAndSwapsOnNodes += 2; //set
            tree2.root.setParentNode(null);
        }
        readsAndSwapsOnNodes++; // =
        root = null;
        tree1.splay(value, tree1.root);
        readsAndSwapsOnNodes += tree1.getReadsAndSwapsOnNodes();
        comparisonBetweenValues += tree1.getComparisonBetweenValues();
        tree1.readsAndSwapsOnNodes = 0;
        tree1.comparisonBetweenValues = 0;
        readsAndSwapsOnNodes += 2; // set
        tree1.root.setRightNode(tree2.root);
        readsAndSwapsOnNodes++; // =
        root = tree1.root;
        returnOperationCalculations();
        return root;
    }

    int treeHeight() {
        NodeBST node = root;
        // Base Case
        if (node == null)
            return 0;

        // Create an empty queue for level order traversal
        Queue<NodeBST> q = new LinkedList();

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
                NodeBST newnode = q.peek();
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
            NodeBST temp = case1.insert(sortedNumbers[i]);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("Value already exist in tree");
            else {
                System.out.println("Inserted:  " + sortedNumbers[i]);
                case1.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeBST temp = case1.delete(value);
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
            NodeBST temp = case2.insert(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("Value already exist in tree");
            else {
                System.out.println("Inserted:  " + value);
                case2.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeBST temp = case2.delete(value);
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