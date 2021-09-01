package org.tmeehan.bst;

/**
 * Implemented BinaryNode<T> as Integer for T
 */
public class IntegerBinaryNode implements BinaryNode<Integer> {

    private Integer value;
    private int depth;
    private BinaryNode<Integer> leftChild;
    private BinaryNode<Integer> rightChild;

    public IntegerBinaryNode(int v) {
        this.value = v;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public BinaryNode<Integer> withDepth(int depth) {
        this.depth = depth;
        return this;
    }


    @Override
    public BinaryNode<Integer> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public BinaryNode<Integer> getRightChild() {
        return this.rightChild;
    }

    @Override
    public BinaryNode<Integer> setLeftChild(BinaryNode<Integer> child) {
        this.leftChild = child.withDepth(this.depth + 1);
        return this;
    }

    @Override
    public BinaryNode<Integer> setRightChild(BinaryNode<Integer> child) {
        this.rightChild = child.withDepth(this.depth + 1);
        return this;
    }
}