package A3;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * A specialized Binary Search Tree class specifically for ArrayList<Row>. Retrieved from lecture notes.
 * 
 * <p>COMP 2503</p>
 * @author
 * Alan Fedoruk
 */
public class BST
{
    class BSTNode implements Comparable<BSTNode>
    {
        private ArrayList<Row> data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(ArrayList<Row> d)
        {
            left = null;
            right = null;
            data = d;
        }

        public ArrayList<Row> getData() {return data;}
        public BSTNode getLeft() {return left;}
        public BSTNode getRight() {return right;}

        public void setData(ArrayList<Row> data) {this.data = data;}
        public void setLeft(BSTNode l) {left = l;}
        public void setRight(BSTNode r) {right = r;}

        public boolean isLeaf()
        {
            return ((getLeft() == null) && (getRight() == null));
        }

        //TODO: fix compareTo so it compares two rows
        public int compareTo(BSTNode o )
        {
            return this.getData().compareTo(o.getData());
        }
    }

    private BSTNode root;
    private int size;

    public BST()
    {
        root = null;
        size = 0;
    }

    public int getSize() {return size;}

    public void add(ArrayList<Row> data)
    {
        BSTNode node = new BSTNode(data);

        if(root == null)
            root = node;
        else
            add(root, node);
        
        size++;
    }

    public void print()
    {
        traverse(root);
    }

    private void add(BSTNode root, BSTNode node)
    {
        int comparison = node.compareTo(root);

        if(comparison < 0)
        {
            if(root.getLeft() == null)
                root.setLeft(node);
            else
                add(root.getLeft(), node);
        }
        else if (comparison > 0)
        {
            if (root.getRight() == null)
                root.setRight(node);
            else
                add(root.getRight(), node);
        }
    }

    private void visit(BSTNode root)
    {
        if(root != null)
            System.out.println(root.getData());
    }

    private void traverse(BSTNode root)
    {
        if (root == null)
            return;
        else
        {
            visit( root);
            traverse(root.getRight());
            traverse(root.getLeft());
        }
    }
}