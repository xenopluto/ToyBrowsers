package hj.browser.core;

import hj.browser.core.dom.Node;
import hj.browser.core.dom.Element;
import hj.browser.core.dom.Text;

import java.util.HashMap;
import java.util.Vector;


public class Parser {
	
	int pos;
	String input;
	
	private Parser() {
		pos = 0;
	}
	
	public Parser(String data) {
		this();
		this.input = data;
	}
	
	private char nextChar() {
		return input.charAt(pos);
	}
	
	private boolean startsWith(String str) {
		return input.startsWith(str, pos);
	}
	
	private boolean isEOF() {
		return pos >= input.length();
	}
	
	private char consumeChar() {
		return input.charAt(pos++);
	}
	
	private String consumeWhile(Comparator<Character> c) {
		StringBuilder result = new StringBuilder();
		while(!isEOF() && c.matches(nextChar())) {
			result.append(consumeChar());
		}
		return result.toString();
	}
	
	private void consumeWhitespace() {
		class WhitespaceComparator implements Comparator<Character> {
			
			char[] whitespaceCharacters = {' ', '\n', '\t'};
			
			@Override
			public boolean matches(Character target) {
				int i = 0;
				while(i < whitespaceCharacters.length) {
					if (whitespaceCharacters[i++] == target) return true;
				}
				return false;
			}
			
		}
		
		consumeWhile(new WhitespaceComparator());
	}
	
	private String parseTagName() {
		class TagNameComparator implements Comparator<Character> {

			@Override
			public boolean matches(Character target) {
				return Character.isAlphabetic(target) || Character.isDigit(target);
			}
			
		}
		
		return consumeWhile(new TagNameComparator());
	}
	
	private Node parseNode() {
		Node result;
		
		if (nextChar() == '<') result = parseElement();
		else result = parseText();
		
		return result;
	}

	private Node parseText() {
		class TagComparator implements Comparator<Character> {

			@Override
			public boolean matches(Character target) {
				return target.compareTo('<') != 0;
			}
			
		}
		return new Text(consumeWhile(new TagComparator()));
	}

	private Node parseElement() {
		// Opening Tag
		assert(consumeChar() == '<');
		String tagName = parseTagName();
		HashMap<String, String> attrs = parseAttributes();
		assert!(consumeChar() == '>');
		
		// Contents
		Vector<Node> children = parseNodes();
		
		// Closing Tag
		assert!(consumeChar() == '<');
		assert!(consumeChar() == '/');
		assert!(parseTagName() == tagName);
		assert!(consumeChar() == '>');
		
		return new Element(tagName, attrs, children);
	}

	private String parseAttrValue() {
		class CharacterNotComparator implements Comparator<Character> {

			private Character source;
			
			private CharacterNotComparator() {}
			
			public CharacterNotComparator(char c) {
				this.source = c;
			}
			
			@Override
			public boolean matches(Character target) {
				return source.compareTo(target) != 0;
			}
			
		}

		char openQuote = consumeChar();
		assert!(openQuote == '"' || openQuote == '\'');
		String value = consumeWhile(new CharacterNotComparator(openQuote));
		assert!(consumeChar() == openQuote);
		return value;
	}
	
	private HashMap<String, String> parseAttributes() {
		HashMap<String, String> attributes = new HashMap<String, String>();
		while(true) {
			consumeWhitespace();
			if (nextChar() == '>') {
				break;
			}
			String tagName = parseTagName();
			assert!(consumeChar() == '=');
			String value = parseAttrValue();
			attributes.putIfAbsent(tagName, value);
		}
		return attributes;
	}
	
	private Vector<Node> parseNodes() {
		Vector<Node> nodes = new Vector<Node>();
		while(true) {
			consumeWhitespace();
			if (isEOF() || startsWith("</")) {
				break;
			}
			nodes.add(parseNode());
		}
		return nodes;
	}
	
	public Node parse() {
		Vector<Node> nodes = parseNodes();
		
		if (nodes.size() == 1) {
			return nodes.firstElement();
		} else {
			return new Element(new String("html"), new HashMap<String, String>(), nodes);
		}
	}
}
