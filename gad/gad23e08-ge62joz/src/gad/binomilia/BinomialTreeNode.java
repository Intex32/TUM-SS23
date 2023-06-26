package gad.binomilia;

import java.util.ArrayList;
import java.util.List;

public class BinomialTreeNode {
	private int element;

	private List<BinomialTreeNode> children = new ArrayList<>();
	private int rank = 0;

	public BinomialTreeNode(int element) {
		this.element = element;
	}

	public int min() {
		return element;
	}

    public List<BinomialTreeNode> getChildren() {
        return children;
    }

    public int rank() {
		/*BinomialTreeNode parent = this;
		var i = 0;
		while(!parent.children.isEmpty()) {
			i++;
			parent = parent.children.get(0);
		}
		return i;*/
		return rank;
	}

	public BinomialTreeNode getChildWithRank(int rank) {
		return children.get(children.size() - rank - 1);
	}

	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		if(a.element <= b.element) {
			a.children.add(0, b);
			a.rank++;
			return a;
		} else {
			b.children.add(0, a);
			b.rank++;
			return b;
		}
	}

	public int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t\t%d [label=\"%d\"];%n", idx, element));
		int rank = rank();
		int next = idx + 1;
		for (int i = 0; i < rank; i++) {
			next = getChildWithRank(i).dotLink(sb, idx, next);
		}
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next) {
		sb.append(String.format("\t\t%d -> %d;%n", idx, next));
		return dotNode(sb, next);
	}
}