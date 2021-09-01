package org.tmeehan.bst;

import java.util.List;

/**
 * Template class to establish a binary search tree for any type and return deepestNodes
 * @param <T>
 */
public interface BinarySearchTree<T> {

    void insert(T value);

    BinaryNode<T> getRoot();

    BinaryNode<T> search(BinaryNode<T> node, T value);

    List<BinaryNode<T>> getNodesAtDepth(int depth);

    List<BinaryNode<T>> getDeepestNodes();
}
