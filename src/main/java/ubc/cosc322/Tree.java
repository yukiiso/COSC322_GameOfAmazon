package ubc.cosc322;

import java.util.Stack;

public class Tree {
	private Node root;
	private int depth;
	
	public Tree(Node root) {
		this.root = root;
		this.depth = 0;
	}

	public void growTree(Node curr, int toDepth) {
		if(toDepth == 0) return;
		Node child;
		for(int i=0; i<curr.getBF(); ++i) {
			if(toDepth % 2 == 1) {
				child = new Node(new Board(curr.getBoard(),true),true);
				child.getBoard().randomMove(true);
			}
			else {
				child = new Node(new Board(curr.getBoard(),false),false);
				child.getBoard().randomMove(false);
			}
			if(curr.getChildren().contains(child)) {
				continue;
			}
			else {
				addChild(curr,child);
				growTree(child,toDepth-1);
			}
		}
		this.depth++;
	}

	public void addChild(Node parent, Node child) {
		parent.addChild(child);
		child.setParent(parent);
	}

	public Stack<Node> findPath(Node curr) {
		Node temp = curr;
		Stack<Node> path = new Stack<>();
		while(temp.getChildren().size() != 0) {
			temp = treeSearch(temp);
			if(temp.getGameOver() == 1) {
				break;
			}
		}
		while(!temp.equals(curr)) {
			path.add(temp);
			temp = temp.getParent();
		}
		return path;
	}

	public Node treeSearch(Node curr) {
		Node best = curr.getChildren().get(0);
		int c = 0;
		for(int i=0;i<curr.getChildren().size();++i) {
			int k = 0;
			if(curr.getChildren().get(i).getBoard().inDanger()) {
				k =- 1000;
			}
			if(curr.getChildren().get(i).getGameOver()==1) {
				best = curr.getChildren().get(i);
				break;
			}					
			else if(best.getBoard().evaluateBoard()+c <curr.getChildren().get(i).getBoard().evaluateBoard()+k) {
				best = curr.getChildren().get(i);
				c = k;
			}
		}
		return best;
	}
}