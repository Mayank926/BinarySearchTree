package gfg;

import java.util.Scanner;

public class BinarySearchTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BSTNode root = new BSTNode(sc.nextInt());
        while (--n > 0) {
            insert(root, sc.nextInt());
        }
        System.out.println(" BST created and root is having data " + root.data);
        inOrderTraverse(root);
        System.out.println("Provide number of search cases ");
        int t = sc.nextInt();
        while (t-- > 0) {
            System.out.println(" Enter element to search");
            int key = sc.nextInt();
            System.out.println(key + " present in BST : " + (searchTree(root, key) != null));
        }
        System.out.println(" Now provide number of elements to be deleted, should be less than " + n);
        t = sc.nextInt();
        while (t-- > 0) {
            System.out.println(" Enter element to be deleted");
            int key = sc.nextInt();
            System.out.println(key + " deleted " + delete(root, key));
            inOrderTraverse(root);
        }
    }

    private static boolean delete(BSTNode root, int key) {
        BSTNode nodeToBeDeleted = searchTree(root, key);
        if (nodeToBeDeleted == null)
            return false;

        if (nodeToBeDeleted.left == null && nodeToBeDeleted.right == null) {
            BSTNode parent = getParent(root, key);
            ChildDirection direction;
            if (parent.left!=null && (parent.left).data == key) {
                nodeToBeDeleted = parent.left;
                direction = ChildDirection.LEFT;
            } else {
                nodeToBeDeleted = parent.right;
                direction = ChildDirection.RIGHT;
            }
            if ((direction == ChildDirection.LEFT)) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        } else if (nodeToBeDeleted.left == null || nodeToBeDeleted.right == null) {
            if (nodeToBeDeleted.left == null) {
                nodeToBeDeleted.data = (nodeToBeDeleted.right).data;
                nodeToBeDeleted.right = null;
            } else {
                nodeToBeDeleted.data = (nodeToBeDeleted.left).data;
                nodeToBeDeleted.left = null;
            }
            return true;
        } else {
            BSTNode parentToInorderSuccessor = findParentToInOrderSuccessor(nodeToBeDeleted);
            BSTNode inorderSuccessor;
            if (parentToInorderSuccessor == nodeToBeDeleted) {
                inorderSuccessor = parentToInorderSuccessor.right;
                nodeToBeDeleted.data = inorderSuccessor.data;
                nodeToBeDeleted.right = inorderSuccessor.right;
            } else {
                inorderSuccessor = parentToInorderSuccessor.left;
                nodeToBeDeleted.data = inorderSuccessor.data;
                parentToInorderSuccessor.left = inorderSuccessor.right;
            }
            return true;
        }
    }

    private static BSTNode findParentToInOrderSuccessor(BSTNode nodeToBeDeleted) {
        BSTNode parentNode = nodeToBeDeleted.right;
        if (parentNode.left == null)
            return nodeToBeDeleted;
        else {
            while ((parentNode.left).left != null)
                parentNode = parentNode.left;
            return parentNode;
        }
    }

    private static BSTNode getParent(BSTNode root, int key) {
        if((root.right!=null && (root.right).data == key) || (root.left!=null && (root.left).data == key))
            return root;
        else if (key > root.data)
            return getParent(root.right, key);
        else
            return getParent(root.left, key);
    }


    private static BSTNode searchTree(BSTNode root, int key) {
        BSTNode result = null;
        if (root != null) {
            if (root.data == key)
                result = root;
            else if (key > root.data)
                result = searchTree(root.right, key);
            else
                result = searchTree(root.left, key);
        }
        return result;
    }

    private static void inOrderTraverse(BSTNode root) {
        if (root == null)
            return;
        else {
            inOrderTraverse(root.left);
            System.out.println(" " + root.data);
            inOrderTraverse(root.right);
        }
    }

    private static BSTNode insert(BSTNode root, int data) {
        if (root == null) {
            return new BSTNode(data);
        } else if (data > root.data) {
            root.right=insert(root.right, data);
        } else {
            root.left=insert(root.left, data);
        }
        return root;
    }
}

enum ChildDirection {
    LEFT,
    RIGHT
}

class BSTNode {
    BSTNode left;
    BSTNode right;
    int data;

    public BSTNode(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public BSTNode() {
    }
}
