package application;

import java.util.Random;

public class Board {
	
	 int grid[][]= new int[9][9];
	 int solGrid[][]= new int [9][9];
	 int refGrid[][] = new int [9][9];
	 int squareroot;
	 int number;
	 int count;
	 int CHECKS=0;
	 int moves=0;
	 int movesmade=0;
	 
	 Random random = new Random();
	    
	    public Board() {
	    	fillDiagonal();
	    	fillNonDiagonals();
	    	EmptyingBlocks();
	    }
	    
// Method to fill the diagonal 3 by 3 matrices. 
	    public void fillDiagonal(){ 
	        int i=0, j=0;
	        int k=1;
	        int l=1;
	        while(k<9){
	        k= l*3;
	        for(i=k-3; i<k;i++){  
	            for(j=k-3;j<k;j++){
	                number=random.nextInt(9)+1;
	                if( check(i,j, number)== true){
	                    if(checkMatrix(i,j, number)==true){
	                        grid[i][j]=number;
	                        solGrid[i][j]=number;
	                        refGrid[i][j]= number;
	                    }
	                    else {
	                         j--;
	                        continue;
	                    }
	                }
	                else if(check(i,j, number)== false){
	                    j--;
	                    continue;
	                }
	            }
	        }
	        l++;
	        }
	    }
// Method to fill non-diagonal matrices
	    public void fillNonDiagonals() {
	    	int i=0,j=0,k=0;
	       	for (i=0;i<3;i++) {
	    		for (j=3;j<9;j++) {
	    			for (k = 1;k<=9;k++) {
	    				if (check(i,j,k)== true) {
	    					if (checkMatrix(i,j,k) == true) {
	    						grid[i][j] = k;
	    						solGrid[i][j]=k;
	    						refGrid[i][j] = k;
	    					}
	    					else continue;
	    				}
	    				else continue;
	    			}
	    		}
	    	}
	    	for (i=3;i<6;i++) {
	    		for (j=0;j<3;j++) {
	    			for (k = 1;k<=9;k++) {
	    				if (check(i,j,k)== true) {
	    					if (checkMatrix(i,j,k) == true) {
	    						grid[i][j] = k;
	    						solGrid[i][j] = k;
	    						refGrid[i][j] = k;
	    					}
	    					else continue;
	    				}
	    				else continue;
	    			}
	    		}
	    	}
	    	for (i=3;i<6;i++) {
	    		for (j=6;j<9;j++) {
	    			for (k = 1;k<=9;k++) {
	    				if (check(i,j,k)== true) {
	    					if (checkMatrix(i,j,k) == true) {
	    						grid[i][j] = k;
	    						solGrid[i][j] = k;
	    						refGrid[i][j] = k;
	    					}
	    					else continue;
	    				}
	    				else continue;
	    			}
	    		}
	    	}
	    	for (i=6;i<9;i++) {
	    		for (j=0;j<6;j++) {
	    			for (k = 1;k<=9;k++) {
	    				if (check(i,j,k)== true) {
	    					if (checkMatrix(i,j,k) == true) {
	    						grid[i][j] = k;
	    						solGrid[i][j] = k;
	    						refGrid[i][j] = k;
	    					}
	    					else continue;
	    				}
	    				else continue;
	    			}
	    		}
	    	}
	    }
	    // Method to check for repetitions in rows and columns. 
	    public Boolean check(int row, int column, int x){
	        int repeatCount=0;
	        for(int i=0; i<9;i++){ // Checking for repetitions with in same row.
	            if(grid[row][i]== x){
	                repeatCount++;
	            }
	        }
	        for(int i=0;i<9;i++){
	            if(grid[i][column]== x){ // checking for repetitions with in same column.
	                repeatCount++;
	            }
	        }
	        if(repeatCount == 0)
	            return true;
	        else
	            return false;
	    }
	    // Method for checking repetitions inside a 3 by 3 matrix. 
	    public Boolean checkMatrix( int row, int column, int x){ 
	        int i=0;
	        int j=0;
	        int repeatCount=0;
	        if(row >=0 && row < 3){
	            i=0;
	        }
	        if(row >=3 && row < 6){
	            i=3;
	        }
	        if(row >=6 && row < 9){
	            i=6;
	        }
	        if(column >=0 && column < 3){
	            j=0;
	        }
	        if(column >=3 && column < 6){
	            j=3;
	        }
	        if(column >=6 && column < 9){
	            j=6;
	        }
	        for(int k = i; k < i+3; k++){
	            for(int l = j; l< j+3; l++){
	                if( grid[k][l]== x){
	                    repeatCount++;
	                }
	            }
	        }
	        if(repeatCount==0){
	            return true;
	        }
	        else 
	            return false;
	    }
	    

	    // Method to remove numbers from random cells in each row. So that the user can play.
	    public void EmptyingBlocks() { 
	    	int colcount=0,j;
	    	for (int i =0; i<9;i++) {
	    		while (colcount < 4 ) {
	    			j = random.nextInt(8);
	    			grid[i][j] = 0;
	    			solGrid[i][j] = 0; 
	    			colcount++;
	    		}
	    		colcount = 0;
	    	}
	    }
	    //method to set the value of moves.
	    public void setMoves(int x) {
	    	moves =x;
	    }
	    // method to return the value of moves.
	    public int getMoves() {	       
	    	return moves;
	    }
	    // Method to count the total number of empty cells in the grid, to decide the total number of moves allowed.
	    public int countEmpty(){
	        int emptyCount=0;
	        for(int i=0;i<9; i++){
	            for(int j=0;j<9;j++){
	                if(grid[i][j]==0){
	                    emptyCount++;
	                }
	            }
	        }
	        return emptyCount;
	    }
	    // Method to return the whole "grid" array.
	    public int[][] getGrid() {
	    	return grid;
	    
	}
	 // Function to add the user inputs to the grid.
	    public void editGrid(int num, int row, int column) {
	    	
	    	grid[row][column] = num;
	    }
/* Method to check user's answers. The whole of the sudoku grid is stored inside the 2D refGrid array. So if the number at a particular cell matches the number at
 * the same cell of the refGrid, it means that the number is correct. Therefore every number entered by the user is checked against the number at its 
 * corresponding cell in the refGrid array. If the two numbers do not match then the number at that cell in the grid array is removed. 	    
 */
	    public void checkrefGrid() {
	    	for(int i = 0; i < 9; i++) {
	    		for(int j=0 ; j < 9; j++) {
	    			if(grid[i][j] == refGrid[i][j])
	    				continue;
	    			else
	    				grid[i][j] = 0;
	    		}
	    	}
	    }	
		
			
			//method to set the number of checks.
			public void setChecks(int x) {
				CHECKS = x;
			}
			
			public int getChecks() {
				return CHECKS;
			}
			
			/* Method to check if the user has won or not. If the grid, after the user has entered all the numbers matches
			 * the refGrid, it means that the user has entered all the answers correctly, therefore the user has won.
			 */
			public boolean CheckIfWin() {
				
				int count =0;
				for (int i=0; i<9;i++) {
					for (int j = 0; j< 9 ; j++) {
						if (grid[i][j] == refGrid[i][j]){
							continue;
						}
						else 
							count++;
					}
				}
				if(count==0)
					return true;
			
				else
					return false;
			}

}