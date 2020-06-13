package application;

public class BoardHard extends Board{
	int CHECKS=0 ;
	int moves=0 ;
	int count=0;
	public BoardHard() {	
		
		setChecks(3);
		setMoves(countEmpty());
		count = 0;
	}
	
} 