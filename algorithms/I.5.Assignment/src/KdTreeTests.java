import java.util.Iterator;
public class KdTreeTests{
	public static void main(String[] args) { 
		KdTreeTests test = new KdTreeTests();
		StdOut.println("Tests running...");

		test.nearest_sampleExample();
		test.range_sampleExample();
		test.size_samePoints();
		test.contains_sampleExample();
		StdOut.println("Well done!");

	}

	public void nearest_sampleExample() {
		KdTree set = new KdTree();
		set.insert(new Point2D(.5, .1));
		set.insert(new Point2D(.4, .3));
		set.insert(new Point2D(.6, .5));
		set.insert(new Point2D(.63, .5));
		set.insert(new Point2D(.62, .5));
		set.insert(new Point2D(.64, .5));
		set.insert(new Point2D(.42, .5));
		set.insert(new Point2D(.23, .5));
		set.insert(new Point2D(.500001, .5));
		Point2D nearest = set.nearest(new Point2D(.499999, .5));

		assert nearest.x() == .500001;
		assert nearest.y() == .5;
	}
	public void contains_sampleExample() {
		KdTree set = new KdTree();
		set.insert(new Point2D(.74, .24));
		set.insert(new Point2D(.66, .46));
		set.insert(new Point2D(.37, .17));
		set.insert(new Point2D(.58, .98));
		set.insert(new Point2D(.15, .95));

		assert set.contains(new Point2D(.15, .95)) == true;
	}



	public void range_sampleExample(){
		KdTree set = new KdTree();
		set.insert(new Point2D(.15, .95));
		set.insert(new Point2D(.74, .24));
		set.insert(new Point2D(.66, .46));
		set.insert(new Point2D(.37, .17));
		set.insert(new Point2D(.58, .98));
		Iterable<Point2D> pointsInRange = set.range(new RectHV(.36, .16, .75, .25));
		Iterator<Point2D> i = pointsInRange.iterator();
		assert set.size() == 5;
		assert i.hasNext() == true;
		Point2D p = i.next();
		assert p.x() == .74;
		assert p.y() == .24;
		assert i.hasNext() == true;
		p = i.next();
		assert p.x() == .37;
		assert p.y() == .17;
		assert i.hasNext() == false;
	}
	public void size_samePoints() {

		KdTree set = new KdTree();
		set.insert(new Point2D(.15, .95));
		set.insert(new Point2D(.15, .95));
		assert set.size() == 1;
	}
}