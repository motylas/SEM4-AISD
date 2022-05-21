public class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        insertRec(value, root);
    }

    private void insertRec(int value, Node currentNode) {
        int currentNodeValue = currentNode.getValue();
        if (value == currentNodeValue) {
            System.out.println("Value already in tree!");
        } else if (value < currentNodeValue) {
            if (currentNode.hasLeft()) {
                insertRec(value, currentNode.getLeftNode());
            } else {
                Node newNode = new Node(value);
                currentNode.setLeftNode(newNode);
            }
        } else {
            if (currentNode.hasRight()) {
                insertRec(value, currentNode.getRightNode());
            } else {
                Node newNode = new Node(value);
                currentNode.setRightNode(newNode);
            }
        }
    }

    public void delete(int value) {
        if (root == null) {
            System.out.println("No value found in the tree!");
            return;
        }
        if (value == root.getValue()) {
            root = null;
            return;
        }
        deleteRec(value, root, root);
    }

    private void deleteRec(int value, Node currentNode, Node parentNode) {
        int currentNodeValue = currentNode.getValue();
        if (value == currentNodeValue) {
            if (currentNode.hasLeft() && currentNode.hasRight()) delete2(currentNode);
            else if (currentNode.hasLeft() || currentNode.hasRight()) delete1(value, currentNode, parentNode);
            else delete0(value, parentNode);
        } else if (value < currentNodeValue) {
            if (!currentNode.hasLeft()) System.out.println("No value found in the tree!");
            else deleteRec(value, currentNode.getLeftNode(), currentNode);
        } else {
            if (!currentNode.hasRight()) System.out.println("No value found in the tree!");
            else deleteRec(value, currentNode.getRightNode(), currentNode);
        }
    }

    private void delete0(int value, Node parentNode) {
        if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()) parentNode.setLeftNode(null);
        else parentNode.setRightNode(null);
    }

    private void delete1(int value, Node currentNode, Node parentNode) {
        if (parentNode.hasLeft() && value == parentNode.getLeftNode().getValue()) {
            if (currentNode.hasLeft()) parentNode.setLeftNode(currentNode.getLeftNode());
            else parentNode.setLeftNode(currentNode.getRightNode());
        } else {
            if (currentNode.hasLeft()) parentNode.setRightNode(currentNode.getLeftNode());
            else parentNode.setRightNode(currentNode.getRightNode());
        }
    }

    private void delete2(Node currentNode) {
        Node parentOfMax = findParentMax(currentNode.getLeftNode(), currentNode);
        Node maxNode;
        if (currentNode == parentOfMax) maxNode = currentNode.getLeftNode();
        else maxNode = parentOfMax.getRightNode();
        int maxNodeValue = maxNode.getValue();
        currentNode.setValue(maxNodeValue);
        if (maxNode.hasLeft()) delete1(maxNodeValue, maxNode, parentOfMax);
        else delete0(maxNodeValue, parentOfMax);
    }

    private Node findParentMax(Node currentNode, Node parentNode) {
        if (currentNode.hasRight()) return findParentMax(currentNode.getRightNode(), currentNode);
        else return parentNode;
    }

    public int getHeight() {
        if (root == null) return 0;
        return calculateHeight(root, 1);
    }

    private int calculateHeight(Node currentNode, int height) {
        if (!(currentNode.hasLeft() || currentNode.hasRight())) return height;

        int leftHeight = 0, rightHeight = 0;
        if (currentNode.hasLeft()) leftHeight = calculateHeight(currentNode.getLeftNode(), ++height);
        if (currentNode.hasRight()) rightHeight = calculateHeight(currentNode.getRightNode(), ++height);

        return Math.max(leftHeight, rightHeight);
    }

    public void showTree() {
        printTree(root, "");
    }

    private void printTree(Node node, String prefix) {
        if (node == null) return;

        System.out.println(prefix + " + " + node.getValue());
        printTree(node.getLeftNode(), prefix + " ");
        printTree(node.getRightNode(), prefix + " ");
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(12);
        bst.insert(5);
        bst.insert(3);
        bst.insert(1);
        bst.insert(7);
        bst.insert(9);
        bst.insert(8);
        bst.insert(11);
        bst.insert(15);
        bst.insert(13);
        bst.insert(14);
        bst.insert(17);
        bst.insert(20);
        bst.insert(18);
        bst.insert(21);
        bst.showTree();
        bst.delete(20);
        bst.showTree();
    }
}
