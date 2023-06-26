package gad.binomilia;

import java.util.*;

public class BinomialHeap {

	private List<BinomialTreeNode> roots = new ArrayList<>();

	public BinomialHeap() {
	}

	public static void main(String[] args) {
		var heap = new BinomialHeap();
		var random = new Random(32);
		for(int i = 0; i < 12; i++) {
			heap.insert(random.nextInt(100), new StudentResult());
		}
		System.out.println(dot(heap.roots));
		for(int i = 0; i < 3; i++) {
			System.out.println("delete min: " + heap.deleteMin(new StudentResult()));
		}
		System.out.println(dot(heap.roots));
	}

	public int min() {
		Integer min = null;
		for(BinomialTreeNode root : roots) {
			if(min == null || root.min() < min)
				min = root.min();
		}
		if(min == null)
			throw new NoSuchElementException("no root");
		return min;
	}

	public void insert(int key, Result result) {
		result.startInsert(key, roots);

		var modifiedNode = new BinomialTreeNode(key);
		roots.add(modifiedNode);
		result.logIntermediateStep(roots);

		mergeSameRank(roots.size() - 1, result);
	}

	private void mergeSameRank(int modifiedNodeIndex, Result result) {
		while(roots.size() > 1) {
			var modifiedNode = roots.get(modifiedNodeIndex);
			boolean modified = false;
			for(int i = 0; i < roots.size(); i++) {
				if(i == modifiedNodeIndex)
					continue;

				var root = roots.get(i);

				if(root.rank() == modifiedNode.rank()) {
					var mergedNode = BinomialTreeNode.merge(root, modifiedNode);
					if(mergedNode == modifiedNode) {
						roots.remove(i);
						if(i < modifiedNodeIndex)
							modifiedNodeIndex--;
					} else {
						roots.remove(modifiedNodeIndex);
						if(modifiedNodeIndex < i)
							modifiedNodeIndex = i - 1;
						else modifiedNodeIndex = i;
					}
					modified = true;
					result.logIntermediateStep(roots);
					break;
				}
			}
			if(!modified)
				break;
		}
	}

	private void mergeSameRank(Result result) {
		for (int j = 0; j < roots.size() - 1; j++) {
			int i = j + 1;
			var rootI = roots.get(i);
			var rootJ = roots.get(j);

			if(rootI.rank() != rootJ.rank())
				continue;

			var mergedNode = BinomialTreeNode.merge(rootI, rootJ);
			if (mergedNode == rootJ) {
				roots.remove(i);
			} else {
				roots.remove(j);
			}
			result.logIntermediateStep(roots);
			j--;
			if(j >= 0)
				j--;
        }
    }

	public int deleteMin(Result result) {
		result.startDeleteMin(roots);

		BinomialTreeNode minRoot = null;
		Integer minIndex = null;
		for(int i = 0; i < roots.size(); i++) {
			var root = roots.get(i);
			if(minRoot == null || root.min() < minRoot.min()) {
				minIndex = i;
				minRoot = root;
			}
		}
		if(minRoot == null)
			throw new NoSuchElementException("no root");

		roots.remove((int) minIndex);
		outer: for(var child : minRoot.getChildren()) {
			for(int i = 0; i < roots.size(); i++) {
				if(child.rank() >= roots.get(i).rank()) {
					roots.add(i, child);
					continue outer;
				}
			}
			roots.add(child);
		}
		result.logIntermediateStep(roots);
		mergeSameRank(result);

		return minRoot.min();
	}

	public static String dot(BinomialTreeNode[] trees) {
		return dot(Arrays.stream(trees).toList());
	}

	public static String dot(Collection<BinomialTreeNode> trees) {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {").append(System.lineSeparator());
		int id = 0;
		List<Integer> roots = new ArrayList<>();
		for (BinomialTreeNode tree : trees) {
			sb.append(String.format("\tsubgraph cluster_%d {%n", id));
			roots.add(id);
			id = tree.dotNode(sb, id);
			sb.append(String.format("\t}%n"));
		}
		for (int i = 0; i < roots.size() - 1; i++) {
			sb.append(String.format("\t%d -> %d [constraint=false,style=dashed];%n", roots.get(i), roots.get(i + 1)));
		}
		sb.append("}");
		return sb.toString();
	}
}