package application;

public class BoardMedium extends Board{
	int CHECKS=0;
	int MOVES=0;
	int count=0;
	public BoardMedium() {
		setChecks(5);
		setMoves(countEmpty()+10);
		count = 0;
	}

}
