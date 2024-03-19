package uvg.ed.gt;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<E> {
    private class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(E data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, E data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data.hashCode() < root.data.hashCode())
            root.left = insertRec(root.left, data);
        else if (data.hashCode() > root.data.hashCode())
            root.right = insertRec(root.right, data);

        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderTraversalRec(root, result);
        return result;
    }

    private void inOrderTraversalRec(Node root, List<E> result) {
        if (root != null) {
            inOrderTraversalRec(root.left, result);
            result.add(root.data);
            inOrderTraversalRec(root.right, result);
        }
    }

    public boolean search(E data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node root, E data) {
        if (root == null)
            return false;
        if (data.equals(root.data))
            return true;

        if (data.hashCode() < root.data.hashCode())
            return searchRec(root.left, data);
        else
            return searchRec(root.right, data);
    }
}





