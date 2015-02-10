package hj.browser.core.dom;


import java.util.HashMap;
import java.util.Vector;


public class Element extends Node {
	
	String tag_name;
	HashMap<String, String> attributes;
	
	public Element(String name, HashMap<String, String> attrs, Vector<Node> children) {
		this.tag_name = name;
		this.attributes = attrs;
		super.children = children; 
	}

	@Override
	protected void printMe() {
		System.out.println(this.tag_name);
	}
}
