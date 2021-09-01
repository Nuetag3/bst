package org.tmeehan.bst;

import java.util.*;

/**
 * Implemented binary search tree for BinaryNode<Integer> that tracks the depth of each node
 * with a map to look up nodes at a given depth as Big(O(1)) rather than in order traversal  and tracking
 * deepest level which performs as Big(O(n)), where n is the size of the tree.
 */
public class IntegerBinarySearchTree implements BinarySearchTree<Integer> {

    private BinaryNode<Integer> root;
    private final Map<Integer, List<BinaryNode<Integer>>> depthOfNodes = new TreeMap<>(); //sorted map mapping each depth to nodes

    public IntegerBinarySearchTree() {

    }

    public IntegerBinarySearchTree(int v) {
        this.root = new IntegerBinaryNode(v);
        depthOfNodes.put(0, Collections.singletonList(this.root));
    }

    public BinaryNode<Integer> getRoot() {
        return this.root;
    }

    @Override
    public void insert(Integer value) {
        this.root = insert(root, value, -1);
    }

    //Recurse through tree to find a match
    @Override
    public BinaryNode<Integer> search(BinaryNode<Integer> node, Integer value) {

        return (!Optional.ofNullable(node).isPresent() || node.getValue().equals(value)) ? node
                : node.getValue() < value ? search(node.getRightChild(), value)
                : search(node.getLeftChild(), value);
    }

    //Recurse through tree to insert a node
    private BinaryNode<Integer> insert(BinaryNode<Integer> node, Integer value, int parentDepth) {
        int depth = parentDepth + 1;

        if (!Optional.ofNullable(node).isPresent()) {
            node = new IntegerBinaryNode(value).withDepth(depth);
            this.depthOfNodes.putIfAbsent(depth, new ArrayList<>());
            this.depthOfNodes.get(depth).add(node);
            return node;
        }

        return (value <= node.getValue())
                ? node.setLeftChild(insert(node.getLeftChild(), value, depth))
                : node.setRightChild(insert(node.getRightChild(), value, depth));
    }

    //Return nodes at a given depth
    @Override
    public List<BinaryNode<Integer>> getNodesAtDepth(int depth) {
        return depthOfNodes.containsKey(depth) ? depthOfNodes.get(depth) : new ArrayList<>();
    }

    //Return deepest nodes
    @Override
    public List<BinaryNode<Integer>> getDeepestNodes() {
        return getNodesAtDepth(this.depthOfNodes.size() - 1);
    }
}