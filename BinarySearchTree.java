package gfg;

import java.util.Scanner;

public class BinarySearchTree {
    public static void main(String[] args) {
        System.out.println("Enter number of elements to create BST");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Enter elements in one line separated by spaces and hit enter to insert");
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
        System.out.println(" Now provide number of elements to be deleted");
        t = sc.nextInt();
        while (t-- > 0) {
            System.out.println(" Enter element to be deleted");
            int key = sc.nextInt();
            root= delete(root, key);
            System.out.println(key + " deleted " );
            inOrderTraverse(root);
        }
    }

    private static BSTNode delete(BSTNode root, int key){
        if(root==null)
            return null;
        if(root.data==key) {
            if(root.left==null&& root.right==null)
                return null;
            else if(root.right==null ){
                return root.left;
            }else if(root.left==null ){
                return root.right;
            }else{
                BSTNode parentToSuccessor=findSmallestLeftChild(root.right);
                if(parentToSuccessor==null) {
                    root.data=(root.right).data;
                    root.right=(root.right).right;
                    return root;
                }
                root.data = (parentToSuccessor.left).data;
                parentToSuccessor.left=(parentToSuccessor.left).right;
                return root;
            }
        }
        else if(key> root.data){
            root.right= delete(root.right,key);
            return root;
        }else{
            root.left= delete(root.left,key);
            return root;
        }
    }

    private static BSTNode findSmallestLeftChild(BSTNode root){
        BSTNode parent;
        if(root.left==null)
            return  null;
        if((root.left).left==null)
            return root;
        else{
            parent=findSmallestLeftChild(root.left);
            return parent;
        }
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
