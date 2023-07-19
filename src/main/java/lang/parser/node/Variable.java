package lang.parser.node;

import lang.Fragment;
import lang.ParserNode;
import lang.Token;

public class Variable extends ParserNode {

	public static final Fragment[] grammar = { new Fragment(Token.IDENTIFIER, Token.ASSIGN, Token.INTEGER),
			new Fragment(Token.IDENTIFIER, Token.ASSIGN, Token.IDENTIFIER) };

	public Fragment[] getGrammar() {
		return grammar;
	}

}
