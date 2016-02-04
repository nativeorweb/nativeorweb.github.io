package com.example.woj.happynumbers.TCLBG.Experiments;

/**
 * Created by welli on 16/10/2015.
 */


/**
 *
 * #6
 *
 * The Computer Language Benchmarks Game
 * http://benchmarksgame.alioth.debian.org/
 http://benchmarksgame.alioth.debian.org/u64/program.php?test=binarytrees&lang=java&id=6
 *
 * Loosely based on Jarkko Miettinen's implementation. Requires Java 8.
 *
 * contributed by Heikki Salokanto.
 * modified by Chandra Sekar
 * modified by Mike Krüger
 */

public class binarytrees {

    public static final int DEFAULT_NUMBER = 19;


    public static void execute(int number) throws Exception {
        int n = number;
        int minDepth = 4;
        int maxDepth = Math.max(minDepth + 2, n);
        int stretchDepth = maxDepth + 1;
        int check = (TreeNode.create(0, stretchDepth)).check();

        System.out.println("stretch tree of depth " + (maxDepth + 1) + "\t check: " + check);

        TreeNode longLivedTree = TreeNode.create(0, maxDepth);
        for (int depth = minDepth; depth <= maxDepth; depth += 2)
        {
            int iterations = 1 << (maxDepth - depth + minDepth);
            check = 0;

            for (int i = 1; i <= iterations; i++)
            {
                check += (TreeNode.create(i, depth)).check();
                check += (TreeNode.create(-i, depth)).check();
            }
            System.out.println((iterations << 1) + "\t trees of depth " + depth + "\t check: " + check);
        }

        System.out.println("long lived tree of depth " + maxDepth + "\t check: " + longLivedTree.check());
    }

    static class TreeNode {
        int item;
        TreeNode left, right;

        static TreeNode create(int item, int depth)
        {
            return ChildTreeNodes(item, depth - 1);
        }

        static TreeNode ChildTreeNodes(int item, int depth)
        {
            TreeNode node = new TreeNode(item);
            if (depth > 0)
            {
                node.left = ChildTreeNodes(2 * item - 1, depth - 1);
                node.right = ChildTreeNodes(2 * item, depth - 1);
            }
            return node;
        }

        TreeNode(int item) {
            this.item = item;
        }

        int check() {
            return left == null ? item : left.check() - right.check() + item;
        }
    }
}