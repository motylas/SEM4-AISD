public class BST {
    protected NodeBST root;

    public BST() {
        root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new NodeBST(value, null);
            return;
        }
        insertRec(value, root);
    }

    protected void insertRec(int value, NodeBST currentNode) {
        int currentNodeValue = currentNode.getValue();
        if (value == currentNodeValue) {
            System.out.println("Value already in tree!");
        } else if (value < currentNodeValue) {
            if (currentNode.hasLeft()) {
                insertRec(value, currentNode.getLeftNode());
            } else currentNode.setLeftNode(new NodeBST(value, currentNode));
        } else {
            if (currentNode.hasRight()) {
                insertRec(value, currentNode.getRightNode());
            } else currentNode.setRightNode(new NodeBST(value, currentNode));
        }
    }

    public void delete(int value) {
        if (root == null) {
            System.out.println("No value found in the tree!");
            return;
        }
        deleteRec(value, root);
    }

    private void deleteRec(int value, NodeBST currentNode) {
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
        BST bst = new BST();
        bst.insert(12);
        bst.insert(5);
        bst.insert(3);
        bst.insert(1);
        bst.showTree();
    }
}
