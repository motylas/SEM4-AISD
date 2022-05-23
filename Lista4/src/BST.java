import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BST {
    private NodeBST root;
    private long comparisonBetweenValues = 0;
    private long readsAndSwapsOnNodes = 0;
    private long allHeight = 0;
    private long maxOperations = 0;
    private long allOperations = 0;
    private long currentOperations = 0;

    public BST() {
        root = null;
    }

    public NodeBST insert(int value) {
        clearValues();
        readsAndSwapsOnNodes++; // if below
        if (root == null) {
            readsAndSwapsOnNodes++; // root =
            root = new NodeBST(value, null);
            returnOperationCalculations();
            return root;
        }
        currentOperations++;
        return insertRec(value, root);
    }

    private void clearValues() {
        comparisonBetweenValues = 0;
        readsAndSwapsOnNodes = 0;
        currentOperations = 0;
    }

    private void returnOperationCalculations() {
        currentOperations += readsAndSwapsOnNodes + comparisonBetweenValues +1;
        if (maxOperations < currentOperations) maxOperations = currentOperations;
        allOperations += currentOperations;
    }

    private NodeBST insertRec(int value, NodeBST currentNode) {
        while (true) {
            currentOperations++; //while check
            currentOperations++; //not a comparison
            int currentNodeValue = currentNode.getValue();
            comparisonBetweenValues++; // value ==
            if (value == currentNodeValue){
                returnOperationCalculations();
                return null;
            }
            else if (value < currentNodeValue) {
                comparisonBetweenValues++; // value <
                readsAndSwapsOnNodes++; //if node.left
                if (currentNode.hasLeft()){
                    readsAndSwapsOnNodes+=2;
                    currentNode = currentNode.getLeftNode();
                }
                else {
                    readsAndSwapsOnNodes+=2; // setLeft
                    currentNode.setLeftNode(new NodeBST(value, currentNode));
                    returnOperationCalculations();
                    return currentNode.getLeftNode();
                }
            } else {
                comparisonBetweenValues++; // else if before this
                readsAndSwapsOnNodes++; // node.right
                if (currentNode.hasRight()){
                    readsAndSwapsOnNodes+=2;
                    currentNode = currentNode.getRightNode();
                }
                else {
                    readsAndSwapsOnNodes+=2; // setRight
                    currentNode.setRightNode(new NodeBST(value, currentNode));
                    returnOperationCalculations();
                    return currentNode.getRightNode();
                }
            }
        }
    }

    public NodeBST delete(int value) {
        clearValues();
        readsAndSwapsOnNodes++; // root ==
        if (root == null){
            returnOperationCalculations();
            return null;
        }
        currentOperations++;
        return deleteRec(value, root);
    }

    private NodeBST deleteRec(int value, NodeBST currentNode) {
        while (true) {
            currentOperations++; // while true
            currentOperations++; // not a comparison
            int currentNodeValue = currentNode.getValue();
            comparisonBetweenValues++; // value ==
            if (value == currentNodeValue) {
                readsAndSwapsOnNodes+=2; // hasLeft, hasRight
                if (currentNode.hasLeft() && currentNode.hasRight()){
                    currentOperations++; //delete
                    delete2(currentNode);
                }
                else if (currentNode.hasLeft() || currentNode.hasRight()){
                    readsAndSwapsOnNodes+=2; //hasLeft hasRight
                    currentOperations++; //delete
                    delete1(value, currentNode);
                }
                else{
                    readsAndSwapsOnNodes+=2; //else if above
                    currentOperations++; // delete;
                    delete0(value, currentNode);
                }
                returnOperationCalculations();
                return currentNode;
            } else if (value < currentNodeValue) {
                comparisonBetweenValues++; // value <
                readsAndSwapsOnNodes++; // hasLeft
                if (!currentNode.hasLeft()){
                    returnOperationCalculations();
                    return null;
                }
                else{
                    readsAndSwapsOnNodes+=2; // getLeftNode and current =
                    currentNode = currentNode.getLeftNode();
                }
            } else {
                comparisonBetweenValues++; // else if above
                readsAndSwapsOnNodes++; // hasRight
                if (!currentNode.hasRight()){
                    returnOperationCalculations();
                    return null;
                }
                else{
                    readsAndSwapsOnNodes+=2; // getRight and current =
                    currentNode = currentNode.getRightNode();
                }
            }
        }
    }

    private void delete0(int value, NodeBST currentNode) {
        readsAndSwapsOnNodes+=2; // newNode and getParent
        NodeBST parentNode = currentNode.getParentNode();
        readsAndSwapsOnNodes++; //parent ==
        if (parentNode == null){
            readsAndSwapsOnNodes++; //root =
            root = null;
        }
        else if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()){
            readsAndSwapsOnNodes+=2; //parent.hasLeft and parent.getLeft
            comparisonBetweenValues++; // value ==
            readsAndSwapsOnNodes+=2; //setLeft
            parentNode.setLeftNode(null);
        }
        else{
            readsAndSwapsOnNodes+=2; //parent.hasLeft and parent.getLeft
            comparisonBetweenValues++; // value ==
            readsAndSwapsOnNodes+=2; //setRight
            parentNode.setRightNode(null);
        }
    }

    private void delete1(int value, NodeBST currentNode) {
        readsAndSwapsOnNodes+=2; // parentNode = and getParent
        NodeBST parentNode = currentNode.getParentNode();
        readsAndSwapsOnNodes++; // parent ==
        if (parentNode == null){
            readsAndSwapsOnNodes++; // root =
            root = currentNode;
        }
        else if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()) {
            readsAndSwapsOnNodes+=2; // hasLeft and getLeft
            comparisonBetweenValues++; // value ==
            readsAndSwapsOnNodes++; //hasLeft
            if (currentNode.hasLeft()){
                readsAndSwapsOnNodes+=3; //set counts as 2 and get
                parentNode.setLeftNode(currentNode.getLeftNode());
            }
            else{
                readsAndSwapsOnNodes+=3; //set counts as 2 and get
                parentNode.setLeftNode(currentNode.getRightNode());
            }
        } else {
            readsAndSwapsOnNodes+=2; // last else if
            comparisonBetweenValues++; // last else if
            readsAndSwapsOnNodes++; // hasLeft
            if (currentNode.hasLeft()){
                readsAndSwapsOnNodes+=3; //set counts as 2 and get
                parentNode.setRightNode(currentNode.getLeftNode());
            }
            else {
                readsAndSwapsOnNodes+=3; //set counts as 2 and get
                parentNode.setRightNode(currentNode.getRightNode());
            }
        }
    }

    private void delete2(NodeBST currentNode) {
        readsAndSwapsOnNodes+=2; // parent = and getLeft
        NodeBST parentOfMax = findParentMax(currentNode.getLeftNode(), currentNode);
        currentOperations++;
        NodeBST maxNode;
        readsAndSwapsOnNodes++; // currentNode ==
        if (currentNode == parentOfMax){
            readsAndSwapsOnNodes+=2; //maxNode = and getLeft
            maxNode = currentNode.getLeftNode();
        }
        else {
            readsAndSwapsOnNodes+=2; //maxNode = and getRight
            maxNode = parentOfMax.getRightNode();
        }
        currentOperations++; // not comparison
        int maxNodeValue = maxNode.getValue();
        readsAndSwapsOnNodes+=2; //set
        currentNode.setValue(maxNodeValue);
        readsAndSwapsOnNodes++; // hasLeft
        if (maxNode.hasLeft()){
            currentOperations++;
            delete1(maxNodeValue, maxNode);
        }
        else {
            currentOperations++;
            delete0(maxNodeValue, maxNode);
        }
    }

    private NodeBST findParentMax(NodeBST currentNode, NodeBST parentNode) {
        readsAndSwapsOnNodes++; // hasRight
        if (currentNode.hasRight()){
            readsAndSwapsOnNodes++; //getRight
            return findParentMax(currentNode.getRightNode(), currentNode);
        }
        else{
            currentOperations++;
            return parentNode;
        }
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

        while (true)
        {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0)
            {
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

        BST case1 = new BST();
        BST case2 = new BST();

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
            else{
                System.out.println("Inserted:  " + sortedNumbers[i]);
                case1.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeBST temp = case1.delete(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("No value found in tree");
            else{
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
            else{
                System.out.println("Inserted:  " + value);
                case2.showTree();
            }
        }
        for (int i = 0; i < amountOfNumbers; i++) {
            int value = random.nextInt(bound);
            NodeBST temp = case2.delete(value);
            if (amountOfNumbers > 50) continue;
            if (temp == null) System.out.println("No value found in tree");
            else{
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

    public long getAllOperations() {
        return allOperations;
    }

    public long getMaxOperations() {
        return maxOperations;
    }
}