package lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token implements IParserNode {

	// comment
	COMMENT_INLINE("//.*\\n"),
	COMMENT_MULTILINE("/\\*.*\\*/"),

	// arithmetic operator
	MINUS("-"),
	PLUS("\\+"),
	MUL("\\*"),
	DIV("/"),
	REMAINDER("%"),

	// bitwise operator
	BITWISE_NOT("~"),
	BITWISE_AND("&"),
	BITWISE_OR("\\|"),
	BITWISE_XOR("\\^"),
	SHIFT_LEFT("<<"),
	SHIFT_RIGHT(">>"),

	// logical operator
	NOT("!"),
	AND("&&"),
	OR("\\|\\|"),

	// relational operator
	LESS("<"),
	LEQ("<="),
	GT(">"),
	GEQ(">="),
	EQ("=="),

	// parenthesis
	OPEN("\\("),
	CLOSE("\\)"),

	// bracket
	OPEN_BRACKET("\\{"),
	CLOSE_BRACKET("\\}"),

	// square bracket
	OPEN_SQUARE_BRACKET("\\["),
	CLOSE_SQUARE_BRACKET("\\]"),

	// symbole
	ASSIGN("="),
	SEMI(";"),
	COMMA(","),
	COLON(":"),

	// keys
	KEY_DEFINE("def"),
	KEY_IF("if"),
	KEY_ELSE("else"),
	KEY_FOR("for"),
	KEY_FOREACH("foreach"),
	KEY_SWITCH("switch"),
	KEY_CASE("case"),
	KEY_DEFAULT("default"),
	KEY_RETURN("return"),
	KEY_CONTINUE("continue"),
	KEY_BREAK("break"),
	KEY_WHILE("while"),
	KEY_DO("do"),

	// primary
	KEY_TRUE("true"),
	KEY_FALSE("false"),
	CHAR("'.'"),
	STRING("\"[^\"]*\""),
	FLOAT("(\\d*)\\.\\d+"),
	INTEGER("\\d+"),
	IDENTIFIER("[a-zA-Z]\\w*");

	private final Pattern pattern;

	Token(String regex) {
		pattern = Pattern.compile("^" + regex);
	}

	int endOfMatch(String s) {
		Matcher m = pattern.matcher(s);

		if (m.find()) {
			return m.end();
		}
		return -1;
	}
}
