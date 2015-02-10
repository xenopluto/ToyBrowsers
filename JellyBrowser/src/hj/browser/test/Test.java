package hj.browser.test;

import hj.browser.core.Parser;
import hj.browser.core.dom.Node;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser p = new Parser("<html><body><h1>Title</h1><div id=\"main\" class=\"test\"><p>Hello <em>world</em>!</p></div></body></html>");
		Node root = p.parse();
		System.out.println("Printing Tree Structure");
		root.printTree(0);
		System.out.println("TestComplete");
	}

}
