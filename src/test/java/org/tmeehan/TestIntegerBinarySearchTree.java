package org.tmeehan;

import org.junit.Test;
import org.tmeehan.bst.BinaryNode;
import org.tmeehan.bst.BinarySearchTree;
import org.tmeehan.bst.IntegerBinarySearchTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.tmeehan.TestUtils.*;

/**
 * Test Driven Development. Stub out tests to outline logic to be tested.
 */
public class TestIntegerBinarySearchTree {
    public static int[] BALANCED_TREE = {2, 1, 3};

    /**
     * No nodes added and assert empty list of nodes at any depth
     */
    @Test
    public void testEmptyTreeDepth() {
        BinarySearchTree<Integer> tree = new IntegerBinarySearchTree();
        assertEquals(tree.getDeepestNodes().size(), ZERO);
        assertEquals(tree.getNodesAtDepth(RANDOM.nextInt()).size(), ZERO);
    }

    /**
     * Assert empty tree returns null on search.
     */
    @Test
    public void testNullSearch() {
        BinarySearchTree<Integer> tree = new IntegerBinarySearchTree();
        assertNull(tree.search(null, ZERO));
    }


    /**
     * Add only root node. assert root is the value on construction of one node.
     */
    @Test
    public void testOnlyRootNodeTreeDepth() {
        BinarySearchTree<Integer> tree = new IntegerBinarySearchTree(ONE);
        assertEquals(tree.getRoot().getValue().intValue(), ONE);
        assertNull(tree.getRoot().getLeftChild());
        assertNull(tree.getRoot().getRightChild());
        assertEquals(tree.getDeepestNodes().size(), ONE);
        assertEquals(tree.getNodesAtDepth(ZERO).size(), ONE);
        assertEquals(tree.getNodesAtDepth(RANDOM.nextInt() + TWO).size(), ZERO);
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getDepth(), ZERO));
        tree.getDeepestNodes().forEach(n -> assertNull(n.getLeftChild()));
        tree.getDeepestNodes().forEach(n -> assertNull(n.getRightChild()));
    }

    /**
     * Test simple example with root=2, left=1, right=3.
     * Assert deepest level = 2
     * Assert (2*deepest.size()) - 1 = number of nodes
     */
    @Test
    public void testRootPlusChildrenFullBalanced() {
        IntegerBinarySearchTree tree = new IntegerBinarySearchTree();
        Arrays.stream(BALANCED_TREE).forEach(tree::insert);

        assertEquals((TWO * tree.getDeepestNodes().size()) - ONE, BALANCED_TREE.length);
        assertEquals(tree.getNodesAtDepth(ZERO).size(), ONE);
        assertEquals((TWO * tree.getNodesAtDepth(ONE).size()) - ONE, BALANCED_TREE.length);
        assertEquals(tree.getRoot().getValue().intValue(), BALANCED_TREE[ZERO]);
        assertEquals(tree.getRoot().getLeftChild().getValue().intValue(), BALANCED_TREE[ONE]);
        assertEquals(tree.getRoot().getRightChild().getValue().intValue(), BALANCED_TREE[TWO]);
        assertEquals(tree.search(tree.getRoot(), BALANCED_TREE[ZERO]), tree.getRoot());
        assertEquals(tree.getRoot().getLeftChild(), tree.search(tree.getRoot(), BALANCED_TREE[ONE]));
        assertEquals(tree.getRoot().getRightChild(), tree.search(tree.getRoot(), BALANCED_TREE[TWO]));
    }

    /**
     * Test example integer array, assert deepest node = 9 with depth = 3 and only one node.
     */
    @Test
    public void testExampleIntegerArray() {
        IntegerBinarySearchTree tree = new IntegerBinarySearchTree();
        int[] nodes = {12, 11, 90, 82, 7, 9};
        Arrays.stream(nodes).forEach(tree::insert);
        assertEquals(tree.getDeepestNodes().size(), ONE);
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getValue().intValue(), 9));
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getDepth(), 3));
        tree.getDeepestNodes().forEach(n -> assertEquals(tree.search(tree.getRoot(), 9), n));
    }

    /**
     * Test array with increasing values is right handed only tree and assert depth = size of array - 1
     */
    @Test
    public void testDepthEqualsSizeOfListIncreasing() {
        int lastNum = RANDOM.nextInt(MAX_NODES) + ONE;
        List<Integer> nodes = IntStream.range(ZERO, lastNum)
                .boxed()
                .collect(Collectors.toList());
        BinarySearchTree<Integer> tree = new IntegerBinarySearchTree();
        nodes.forEach(tree::insert);
        assertEquals(tree.getDeepestNodes().size(), ONE);
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getValue().intValue(), lastNum - ONE));
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getDepth(), lastNum - ONE));
    }

    /**
     * Test array with decreasing values is left handed only tree and assert depth = size of array - 1
     */
    @Test
    public void testDepthEqualsSizeOfListDecreasing() {
        int lastNum = RANDOM.nextInt(MAX_NODES) + ONE;
        List<Integer> nodes = IntStream.range(ZERO, lastNum)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        BinarySearchTree<Integer> tree = new IntegerBinarySearchTree();
        nodes.forEach(tree::insert);
        assertEquals(tree.getDeepestNodes().size(), ONE);
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getValue().intValue(), ZERO));
        tree.getDeepestNodes().forEach(n -> assertEquals(n.getDepth(), lastNum - ONE));
    }

    @Test
    public void testPreOrderTraverse() {
        IntegerBinarySearchTree tree = new IntegerBinarySearchTree();
        Arrays.stream(BALANCED_TREE).forEach(tree::insert);
        List<Integer> order = Arrays.stream(BALANCED_TREE).boxed().collect(Collectors.toList());
        List<BinaryNode<Integer>> traverse = tree.preOrderTraversal(tree.getRoot());
        assertEquals(traverse.stream()
                .map(n -> n.getValue()).collect(Collectors.toList()), order);
    }

    @Test
    public void testInOrderTraverse() {
        IntegerBinarySearchTree tree = new IntegerBinarySearchTree();
        Arrays.stream(BALANCED_TREE).forEach(tree::insert);
        List<Integer> order = Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList());
        List<BinaryNode<Integer>> traverse = tree.inOrderTraversal(tree.getRoot());
        assertEquals(traverse.stream()
                .map(n -> n.getValue()).collect(Collectors.toList()), order);
    }

    @Test
    public void testPostOrderTraverse() {
        IntegerBinarySearchTree tree = new IntegerBinarySearchTree();
        Arrays.stream(BALANCED_TREE).forEach(tree::insert);
        List<Integer> order = Arrays.stream(new int[]{1, 3, 2}).boxed().collect(Collectors.toList());
        List<BinaryNode<Integer>> traverse = tree.postOrderTraversal(tree.getRoot());
        assertEquals(traverse.stream()
                .map(n -> n.getValue()).collect(Collectors.toList()), order);
    }
}
