package lang;

import lang.parser.node.Variable;

public class Parser {

	LexemaToken[] tokens;

	public Parser(LexemaToken... tokens) {
		this.tokens = tokens;
	}

	public void parse() throws Exception {

		System.out.println(validGrammar(0, new Variable()));

	}

	// return 0 if not valid or the end of the tokens list
	public int validGrammar(int pc, IParserNode node) throws Exception {

		// Token
		if (node instanceof Token token) {
			return (token == tokens[pc].token()) ? 1 : 0;
		}

		return validGrammar(pc, (ParserNode) node);
	}

	public int validGrammar(int pc, ParserNode node) throws Exception {

		int pc_local = pc;
		Fragment[] grammar = node.getGrammar();

		for (Fragment fragment : grammar) {

			for (IParserNode _node : fragment.getNodes()) {

				int end = validGrammar(pc_local, _node);
				if (end == 0) {
					pc_local = pc;
					break; // next fragment
				}

				pc_local += end;

			}

			// no unvalid grammar
			int dif_pc = pc_local - pc;
			if (dif_pc != 0) {
				return dif_pc;
			}

		}

		return 0;
	}
}
