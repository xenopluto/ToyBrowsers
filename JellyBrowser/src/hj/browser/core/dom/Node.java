package hj.browser.core.dom;
import java.util.Vector;

public class Node {
	
	protected Vector<Node> children;
	
	public void printTree(int depth) {
		int childrenCnt = children.size();

		for (int i=0; i<depth; i++) {
			System.out.print("\t");
		}
		
		printMe();
		
		for (int i=0; i<childrenCnt; i++) {
			children.get(i).printTree(depth+1);
		}
	}
	
	protected void printMe() {
		System.out.println("Node");
	}
}
