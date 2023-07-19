package lang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Lexer {

	private StringBuilder input = new StringBuilder();
	private Token token;
	private String lexema;
	private boolean exausthed = false;
	private static final Set<Character> blankChars = new HashSet<Character>();

	static {
		blankChars.add('\r');
		blankChars.add('\n');
		blankChars.add((char) 8);
		blankChars.add((char) 9);
		blankChars.add((char) 11);
		blankChars.add((char) 12);
		blankChars.add((char) 32);
	}

	public Lexer(String filePath) throws Exception {

		try {
			Files.lines(Paths.get(filePath)).forEach(input::append);
			System.out.println("Get file correctly \nLenght of " + input.length());

		} catch (IOException ex) {
			exausthed = true;
			throw new Exception("Could not read file: " + filePath);
		}

		nextToken();
	}

	public void nextToken() throws Exception {

		if (exausthed) {
			return;
		}

		if (input.length() == 0) {
			exausthed = true;
			return;
		}

		ignoreWhiteSpaces();

		if (findNextToken()) {
			return;
		}

		exausthed = true;

		if (input.length() > 0) {
			throw new Exception("Unexpected symbol: '" + input.charAt(0) + "'");
		}
	}

	private void ignoreWhiteSpaces() {
		int charsToDelete = 0;

		while (blankChars.contains(input.charAt(charsToDelete))) {
			charsToDelete++;
		}

		if (charsToDelete > 0) {
			input.delete(0, charsToDelete);
		}
	}

	private boolean findNextToken() {
		for (Token t : Token.values()) {
			int end = t.endOfMatch(input.toString());

			if (end != -1) {
				token = t;
				lexema = input.substring(0, end);
				input.delete(0, end);
				return true;
			}
		}

		return false;
	}

	public Token currentToken() {
		return token;
	}

	public String currentLexema() {
		return lexema;
	}

	public boolean isExausthed() {
		return exausthed;
	}

	public List<LexemaToken> getTokens() throws Exception {

		List<LexemaToken> tokens = new LinkedList<LexemaToken>();

		// TODO: sup error --debug
		int error = 100; // max lenght of tokens for .txt file
		while (!exausthed && error > 0) {
			// System.out.println((100 - error) + ". ");
			tokens.add(new LexemaToken(lexema, token));
			nextToken();

			error--;
		}

		return tokens;
	}
}