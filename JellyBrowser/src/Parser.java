
public class Parser {
	int pos;
	String input;
	
	public Parser() {
		pos = 0;
	}
	
	public char nextChar() {
		return input.charAt(pos);
	}
	
	public boolean startsWith(String str) {
		return input.startsWith(str, pos);
	}
	
	public boolean isEof() {
		return pos >= input.length();
	}
	
	public char consumeChar() {
		return input.charAt(pos++);
	}
	
	public String consumeWhile(CharComp cc) {
		StringBuilder result = new StringBuilder();
		while(!isEof() && cc.compare(nextChar())) {
			result.append(consumeChar());
		}
		return result.toString();
	}
	
	// TODO: should be improved to cover all whitespace character
	public void consumeWhitespace() {
		consumeWhile(new CharComp(' '));
	}
	
	// TODO: NOT YET IMPLEMENTED
	public String parseTagName() {
		return consumeWhile(new CharComp(' '));
	}
}
