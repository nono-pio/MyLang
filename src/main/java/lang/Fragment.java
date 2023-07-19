package lang;

public class Fragment {

	private final IParserNode[] nodes;

	public Fragment(IParserNode... nodes) {
		this.nodes = nodes;
	}

	public IParserNode[] getNodes() {
		return nodes;
	}

	public IParserNode getNode(int index) {
		return nodes[index];
	}

}
