package org.tmeehan.bst;

/**
 * Template class to establish a tree node of a given type T and a depth within a tree.
 * Using builder pattern to set depth and children.
 *
 * @param <T>
 */
public interface BinaryNode<T> {

    T getValue();

    int getDepth();

    BinaryNode<T> withDepth(int depth);

    BinaryNode<T> getLeftChild();

    BinaryNode<T> getRightChild();

    BinaryNode<T> setLeftChild(BinaryNode<T> child);

    BinaryNode<T> setRightChild(BinaryNode<T> child);
}
