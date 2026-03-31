package A3;

/**
 * A standard Binary Search Tree class. Retrieved from lecture notes.
 * 
 * @author
 * Alan Fedoruk
 */
public class BST<T extends Comparable<T>>
{
    class BSTNode implements Comparable<BSTNode>
    {
        private T data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(T d)
        {
            left = null;
            right = null;
            data = d;
        }

        public T getData() {return data;}
        public BSTNode getLeft() {return left;}
        public BSTNode getRight() {return right;}

        public void setData(T data) {this.data = data;}
        public void setLeft(BSTNode l) {left = l;}
        public void setRight(BSTNode r) {right = r;}

        public boolean isLeaf()
        {
            return ((getLeft() == null) && (getRight() == null));
        }

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

    public void add(T data)
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
