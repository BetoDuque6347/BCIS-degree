package A3;

import java.util.ArrayList;

/**
 * A specialized Binary Search Tree class specifically for ArrayList<Row>. Retrieved most if it from lecture notes.
 * 
 * <p>COMP 2503</p>
 * @author
 * Beto Duque
 */
public class BST
{
    class BSTNode implements Comparable<BSTNode>
    {
        private String key;
        private ArrayList<Row> data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(String key, Row row)
        {
            this.key = key;
            this.data = new ArrayList<>();
            this.data.add(row);
            left = null;
            right = null;
        }

        public ArrayList<Row> getData() {return data;}
        public String getKey() {return key;}
        public BSTNode getLeft() {return left;}
        public BSTNode getRight() {return right;}

        public void setData(ArrayList<Row> data) {this.data = data;}
        public void setKey(String key) {this.key = key;}
        public void setLeft(BSTNode l) {left = l;}
        public void setRight(BSTNode r) {right = r;}

        public boolean isLeaf()
        {
            return ((getLeft() == null) && (getRight() == null));
        }

        @Override
        public int compareTo(BSTNode o )
        {
            return this.getKey().compareTo(o.getKey());
        }
    }

    private BSTNode root;
    private int size;

    public BST()
    {
        root = null;
        size = 0;
    }

    public BSTNode getRoot() {return root;}
    public int getSize() {return size;}

    public void add(String key, Row row)
    {
        root = add(root, key, row);
    }

    public void print()
    {
        traverse(root);
    }

    private BSTNode add(BSTNode node, String key, Row row)
    {
        if (node == null)
            return new BSTNode(key, row);

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            node.left = add(node.left, key, row);
        else if (cmp > 0)
            node.right = add(node.right, key, row);
        else
            node.data.add(row);

        return node;
    }

    private void visit(BSTNode root)
    {
        if(root != null)
            System.out.println(root.getKey());
    }

    private void traverse(BSTNode root)
    {
        if (root != null)
        {
            visit( root);
            traverse(root.getRight());
            traverse(root.getLeft());
        }
    }

    public ArrayList<Row> find(String key)
    {
        BSTNode node = findNode(root, key);
        if (node == null)
            return new ArrayList<>();
        return node.data;
    }

    private BSTNode findNode(BSTNode node, String key)
    {
        //Node cannot be found
        if(node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0) //Node is found
            return node;
        else if(cmp < 0) //Node is to the left
            return findNode(node.left, key);
        else //Node is to the right
            return findNode(node.right, key);
    }
}