package lang;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		Lexer lexer = new Lexer("src/main/resources/type.txt");
		List<LexemaToken> tokens = lexer.getTokens();

		System.out.println("\nPrinting...");
		printToken(tokens);

		System.out.println("\nParser ...");
		Parser parser = new Parser(tokens.toArray(new LexemaToken[tokens.size()]));
		parser.parse();
	}

	public static void printToken(List<LexemaToken> tokens) {

		for (LexemaToken token : tokens) {
			System.out.println(token.token() + " : " + token.lexema());
		}

	}

}
