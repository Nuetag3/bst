package org.tmeehan;

import org.junit.Test;
import org.tmeehan.bst.BinaryNode;
import org.tmeehan.bst.IntegerBinaryNode;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.tmeehan.TestUtils.*;

/**
 * Test Driven Development. Stub out tests to outline logic to be tested.
 */
public class TestIntegerBinaryNode {


    /**
     * set random value for node on construction and assert getValue is correct
     * and depth = 0
     */
    @Test
    public void testValue() {
        int value = RANDOM.nextInt(MAX_NODES);
        IntegerBinaryNode node = new IntegerBinaryNode(value);
        assertEquals(node.getValue().intValue(), value);
        assertEquals(node.getDepth(), ZERO);
        assertNull(node.getLeftChild());
        assertNull(node.getRightChild());
    }

    /**
     * set random value for node on construction and assert getValue is correct
     * and depth = 0
     */
    @Test
    public void testDepth() {
        int value = RANDOM.nextInt(MAX_NODES);
        BinaryNode<Integer> binaryNode = new IntegerBinaryNode(value)
                .withDepth(value);
        assertEquals(binaryNode.getValue().intValue(), value);
        assertEquals(binaryNode.getDepth(), value);
    }

    /**
     * add children to node and values on construction are equal to getValue and
     * the depth of the children is equal to depth of parent node + 1 and each child node's
     * depth is equal to each other.
     */
    @Test
    public void testChildren() {
        int value = RANDOM.nextInt(MAX_NODES);
        BinaryNode<Integer> parent = new IntegerBinaryNode(value)
                .withDepth(value);
        parent.setLeftChild(new IntegerBinaryNode(value - ONE));
        parent.setRightChild(new IntegerBinaryNode(value + ONE));
        assertEquals(parent.getValue().intValue(), value);
        assertEquals(parent.getDepth(), value);
        assertEquals(parent.getLeftChild().getValue().intValue(), value - ONE);
        assertEquals(parent.getRightChild().getValue().intValue(), value + ONE);
        assertEquals(parent.getLeftChild().getDepth(), parent.getDepth() + ONE);
        assertEquals(parent.getLeftChild().getDepth(), parent.getRightChild().getDepth());
    }
}
