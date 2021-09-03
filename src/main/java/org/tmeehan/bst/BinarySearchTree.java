package org.tmeehan.bst;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Template class to establish a binary search tree for any type and return deepestNodes
 *
 * @param <T>
 */
public interface BinarySearchTree<T> {

    void insert(T value);

    BinaryNode<T> getRoot();

    BinaryNode<T> search(BinaryNode<T> node, T value);

    List<BinaryNode<T>> getNodesAtDepth(int depth);

    List<BinaryNode<T>> getDeepestNodes();

    default List<BinaryNode<T>> inOrderTraversal(BinaryNode<T> node) {
        List<BinaryNode<T>> nodes = new LinkedList<>();
        if (Optional.ofNullable(node).isPresent()) {
            nodes.addAll(inOrderTraversal(node.getLeftChild()));
            nodes.add(node);
            nodes.addAll(inOrderTraversal(node.getRightChild()));
        }
        return nodes;
    }

    default List<BinaryNode<T>> preOrderTraversal(BinaryNode<T> node) {
        List<BinaryNode<T>> nodes = new LinkedList<>();
        if (Optional.ofNullable(node).isPresent()) {
            nodes.add(node);
            nodes.addAll(inOrderTraversal(node.getLeftChild()));
            nodes.addAll(inOrderTraversal(node.getRightChild()));
        }
        return nodes;
    }

    default List<BinaryNode<T>> postOrderTraversal(BinaryNode<T> node) {
        List<BinaryNode<T>> nodes = new LinkedList<>();
        if (Optional.ofNullable(node).isPresent()) {
            nodes.addAll(inOrderTraversal(node.getLeftChild()));
            nodes.addAll(inOrderTraversal(node.getRightChild()));
            nodes.add(node);
        }
        return nodes;
    }
}
