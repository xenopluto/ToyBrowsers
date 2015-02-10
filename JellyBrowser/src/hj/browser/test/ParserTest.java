package hj.browser.test;

import static org.junit.Assert.*;

import hj.browser.core.*;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	private Parser p;
	
	@Test
	public void testParser() {
		Parser pp = new Parser();
		assertEquals(0, p.getPos());
	}

	@Test
	public void testParserWithString() {
		Parser pp = new Parser("<html><body><h1>Title</h1><div id=\"main\" class=\"test\"><p>Hello <em>world</em>!</p></div></body></html>");
		assertEquals(0, p.getPos());
	}
	
	@Before
	public void setup() {
		p = new Parser("<html><body><h1>Title</h1><div id=\"main\" class=\"test\"><p>Hello <em>world</em>!</p></div></body></html>");
	}
	
	@Test
	public void testGetPos() {
		assertEquals(0, p.getPos());
	}

	@Test
	public void testNextChar() {
		assertEquals('<', p.nextChar());
		assertEquals(0, p.getPos());
	}

	@Test
	public void testStartsWith() {
		p.pos = 21;
		assertEquals(true, p.startsWith("</"));
		assertEquals(21, p.getPos());
	}

	@Test
	public void testIsEOF() {
		assertEquals(0, p.getPos());
		assertEquals(false, p.isEOF());
	}

	@Test
	public void testConsumeChar() {
		assertEquals('<', p.consumeChar());
		assertEquals(1, p.getPos());
		assertEquals('h', p.consumeChar());
		assertEquals(2, p.getPos());
		assertEquals('t', p.consumeChar());
		assertEquals(3, p.getPos());
	}

/*	@Test
	public void testConsumeWhile() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testConsumeWhitespace() {
		Parser pp = new Parser("       test");
		pp.consumeWhitespace();
		assertEquals(7, pp.getPos());
	}

	@Test
	public void testParseTagName() {
		p.pos = 1;
		assertEquals("html", p.parseTagName());
	}
/*
	@Test
	public void testParseNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseText() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseAttrValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseAttributes() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseNodes() {
		fail("Not yet implemented");
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}
*/
}
