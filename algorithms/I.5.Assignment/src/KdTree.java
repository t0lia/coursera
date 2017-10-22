public class KdTree {
    private Node root;         //     root of BST

    private class Node {
        private Point2D key;       //     sorted by key
        private Node left, right;  // left and right subtrees
        private int N;         //     number of nodes in subtree

        public Node(Point2D key, int N) {
            this.key = key;
            this.N = N;
        }
    }


//     is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

//     return number of key-value pairs in BST
    public int size() {
        return size(root);
    }

//     return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) 
            return 0;
        else 
            return x.N;
    }




//    ////////////////////////////////////////////////////

    private Point2D get(Point2D key) {
        return get(root, key, true);
    }

    private Point2D get(Node x, Point2D key, boolean levelParity) {
        if (x == null) 
            return null;
        if (x.key.x() == key.x() && x.key.y() == key.y()) {
            return x.key;
        }
        if (levelParity) {
            if (key.x() <= x.key.x()) 
                return get(x.left, key, !levelParity);
            return get(x.right, key, !levelParity);
        }
        else {
            if (key.y() <= x.key.y()) 
                return get(x.left, key, !levelParity);
            return get(x.right, key, !levelParity);                
                
        }
    }

//     draw all points to standard draw 
    public void draw() {
        for (Point2D p : levelOrder()) {
            p.draw();
        }
    }
    
    public void insert(Point2D key) {
        root = insert(root, key, true);
    }

    private Node insert(Node x, Point2D key, boolean levelParity) {
        if (x == null) {
            return new Node(key, 1);
        }
        if (key.x() == x.key.x() && key.y() == x.key.y())
            return x;

        if (levelParity) {
            if      (key.x() <= x.key.x()) x.left  = insert(x.left,  key, !levelParity);
            else if (key.x() > x.key.x()) x.right = insert(x.right, key, !levelParity);
            else              x.key   = key;
        }
        else {
            if      (key.y() <= x.key.y()) x.left  = insert(x.left,  key, !levelParity);
            else if (key.y() > x.key.y()) x.right = insert(x.right, key, !levelParity);
            else              x.key   = key;
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Iterable<Point2D> levelOrder() {
        Queue<Point2D> keys = new Queue<Point2D>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) 
                continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }
/*
    public Iterable<Point2D> inOrder() {
        Queue<Point2D> keys = new Queue<Point2D>();
        inOrder(root, keys);
        return keys;
    }

    private void inOrder(Node x, Queue<Point2D> keys) {
        if (x == null) 
            return;
        inOrder(x.left, keys);
        keys.enqueue(x.key);
        inOrder(x.right, keys);
    }
*/
//    ////////////////////////////////////////////////////

//     does the set contain point p? 
    public boolean contains(Point2D key) {
        return get(key) != null;
    }

//     all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> points = new Queue<Point2D>();
        range(root, points, rect, true);
        return points;   
    }

    private void range(Node n, Queue<Point2D> points, RectHV rect, boolean levelParity) {
        if (n == null) 
            return;
        if (rect.contains(n.key)) {
            points.enqueue(n.key);
            range(n.left, points, rect, !levelParity);
            range(n.right, points, rect, !levelParity);
        }
        if (levelParity) {
            if (n.key.x() > rect.xmax()) {
                range(n.left, points, rect, !levelParity);
            }
            else if (n.key.x() < rect.xmin()) {
                range(n.right, points, rect, !levelParity);
            }
            else if (!rect.contains(n.key)) {
                range(n.left, points, rect, !levelParity);
                range(n.right, points, rect, !levelParity);
            }
        }
        if (!levelParity) {
            if (n.key.y() > rect.ymax()) {
                range(n.left, points, rect, !levelParity);
            }
            else if (n.key.y() < rect.ymin()) {
                range(n.right, points, rect, !levelParity);
            }
            else if (!rect.contains(n.key)) {
                range(n.left, points, rect, !levelParity);
                range(n.right, points, rect, !levelParity);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) 
            return null;
        Point2D[] candidate = new Point2D[1];
        candidate[0] = root.key;
        nearest(root, p, candidate, true);
        return candidate[0];
    }

    private void nearest(Node n, Point2D p, Point2D[] candidate, boolean levelParity) {
        if (n == null)
            return;
        double minDistance = candidate[0].distanceTo(p);
        double curDistance = n.key.distanceTo(p);

        if (curDistance < minDistance) {
            candidate[0] = n.key;
        }
        double distaceInOneCoord;
        //odd levels
        if (levelParity) {
            distaceInOneCoord = Math.abs(n.key.x() - p.x());
            if (n.key.x() >= p.x()) {
                nearest(n.left, p, candidate, !levelParity);
                //if not found less distance go other child
                if (candidate[0].distanceTo(p) >  distaceInOneCoord) {
                    nearest(n.right, p, candidate, !levelParity);

                }
            }

            if (n.key.x() < p.x()) {
                nearest(n.right, p, candidate, !levelParity);
                //if not found less distance go other child
                if (candidate[0].distanceTo(p) >  distaceInOneCoord) {
                    nearest(n.left, p, candidate, !levelParity);
                }
            
            }

        
        }
        //even levels
        if (!levelParity) {
            distaceInOneCoord = Math.abs(n.key.y() - p.y());
            if (n.key.y() >= p.y()) {
                nearest(n.left, p, candidate, !levelParity);
                //if not found less distance go other child
                if (candidate[0].distanceTo(p) >  distaceInOneCoord) {
                    nearest(n.right, p, candidate, !levelParity);
                }   
            }
            if (n.key.y() < p.y()) {
                nearest(n.right, p, candidate, !levelParity);
                //if not found less distance go other child
                if (candidate[0].distanceTo(p) >  distaceInOneCoord) {
                    nearest(n.left, p, candidate, !levelParity);
                }
            }

        //if not found less distance go other child
        }
        return;
    }

}