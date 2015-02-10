package hj.browser.core.dom;


import java.util.Vector;


public class Text extends Node {
	
	private String data;

	public Text(String data) {
		super.children = new Vector<Node>();
		this.data = data;
	}

	@Override
	protected void printMe() {
		System.out.println("Text: " + this.data);
	}
}
