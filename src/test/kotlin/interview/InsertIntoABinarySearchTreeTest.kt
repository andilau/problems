package interview

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

// 701. Insert into a Binary Search Tree
// https://leetcode.com/problems/insert-into-a-binary-search-tree/

class InsertIntoABinarySearchTreeTest {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun insertIntoBST(root: TreeNode?, value: Int): TreeNode? {
        if (root == null) return null

        if (value < root.`val`) {
            if (root.left != null) insertIntoBST(root.left, value)
            else root.left = TreeNode(value)
        }
        else {
            if (root.right != null) insertIntoBST(root.right, value)
            else root.right = TreeNode(value)
        }
        return root
    }

    fun insertIntoBSTIterative(root: TreeNode?, value: Int): TreeNode? {
        if (root == null) return null

        var next: TreeNode = root
        while (true) {
            if (value < next.`val`) {
                if (next.left != null) {
                    next = next.left!!
                    continue
                }
                else {
                    next.left = TreeNode(value)
                    break
                }
            }
            else {
                if (next.right != null) {
                    next = next.right!!
                    continue
                }
                else {
                    next.right = TreeNode(value)
                    break
                }
            }
        }
        return root
    }

    @Test
    fun testTree() {
        val root = TreeNode(4)
        root.left = TreeNode(2)
        root.right = TreeNode(7)
        root.left!!.left = TreeNode(1)
        root.left!!.right = TreeNode(3)

        insertIntoBSTIterative(root, 5)

        assertNotNull(root.right!!.left)
        assertEquals(5, root.right!!.left!!.`val`)
    }
}