package ubc.cosc322;

import java.util.ArrayList;

public class Node{
	private Board board;
	private boolean enemy;
	private ArrayList<Node> children;
	private Node parent;
	private int bf = 10;
	private int gameOver;
	
	public Node(Board board, boolean enemy) {
		this.board = new Board(board,enemy);
		this.enemy = enemy;
		this.gameOver = this.getBoard().gameOverCheck(enemy);
		this.children = new ArrayList<>();
		this.parent = null;
	}
	public Board getBoard() {
		return this.board;
	}
	public boolean getEnemy() {
		return this.enemy;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return this.parent;
	}
	public void setGameOver(int gameOver) {
		this.gameOver = gameOver;
	}
	public int getGameOver() {
		return this.gameOver;
	}
	public void addChild(Node child) {
		this.children.add(child);
	}
	public Node getChild(int child) {
		return this.children.get(child);
	}
	public ArrayList<Node> getChildren() {
		return this.children;
	}
	public int getBF() {
		return this.bf;
	}
}