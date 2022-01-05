package hw4.puzzle;

import java.util.Comparator;
import java.util.LinkedList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver{
	private int moves;
	private LinkedList<WorldState> solutions = new LinkedList<>();

	public Solver(WorldState initial) {
		solution();

		MinPQ<SearchNode> pq = new MinPQ<>(new NodeComparator());
		SearchNode currentNode = new SearchNode(initial, 0, null);
		pq.insert(currentNode);
		while (!currentNode.state.isGoal()) {
			currentNode = pq.delMin();
			if (currentNode.state.isGoal()) {
				break;
			}
			for (WorldState middle: currentNode.state.neighbors()) {
				SearchNode newNode = new SearchNode(middle, currentNode.moves + 1, currentNode);
				if (currentNode.prev !=null && middle.equals(currentNode.prev.state)) {
					continue;
				}
				pq.insert(newNode);
			}
		}
		while (currentNode != null) {
			solutions.addFirst(currentNode.state);
			currentNode = currentNode.prev;
		}
	}

	private class SearchNode {
		private WorldState state;
		private int moves;
		private SearchNode prev;

		public SearchNode(WorldState state, int moves, SearchNode prev) {
			this.state = state;
			this.moves = moves;
			this.prev = prev;
		}
	}

	public int moves() {
		return solutions.size() - 1;
	}

	public Iterable<WorldState> solution() {
		return solutions;
	}

	private class NodeComparator implements Comparator<SearchNode> {
		@Override
		public int compare(SearchNode node1, SearchNode node2) {
			int m1 = node1.moves;
			int m2 = node2.moves;
			int e1 = node1.state.estimatedDistanceToGoal();
			int e2 = node2.state.estimatedDistanceToGoal();
			return m1 + e1 - m2 - e2;
		}
	}

}
