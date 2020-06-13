package application;

public class BoardEasy extends Board{
	int CHECKS=0;
	int MOVES=0;
	int count=0;
	public BoardEasy(){
		setChecks(7);
		setMoves(countEmpty()+15);
		count = 0;
	}

}
