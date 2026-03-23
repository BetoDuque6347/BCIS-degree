package A3;

public class BST<T extends Comparable<T>>
{
    class BSTNode implements Comparable<BSTNode>
    {
        private T data;
        private BSTNode left;
        private BSTNode right;

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

        public BSTNode(T d)
        {
            left = null;
            right = null;
            data = d;
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
}
