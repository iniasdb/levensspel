package levensspel;

import java.util.Scanner;

public class Bord {
	
	//constructor
	public Bord() {
		bord = new String[10][10];		
	}
	
	//random board constructor
	public void randomBoard() {
		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord.length; j++) {	
				
				if (i == 0 || i == 9 || j == 0 || j == 9) {
					//creates border
					bord[i][j] = ".";
				} else {
					int x = (int) Math.round(Math.random());
					if (x == 1) {
						bord[i][j] = "x";
					} else if (x == 0) {
						bord[i][j] = ".";
					} else {
						System.out.println("error");
						j--;
					}
				}
			}
		}
	}
	
	//fill board by console	line by line
	public void read() {
		scanner = new Scanner(System.in);
		int x = 0; // one column (used on line 66)
		
		for (int i = 0; i < bord.length; i++) {
			//creates border
			for (int j = 0; j < bord.length; j++) {
				if (i == 0 || i == 9 || j == 0 || j == 9) {
					bord[i][j] = ".";
				}
			}
			
			if (i != 0 && i != 9) {
				System.out.println("1 for occupied, 0 for empty");
				System.out.println(	"please enter line by line (eg.: '11001100')");
				System.out.println("row " + i + " of 8");
				
				String row = scanner.nextLine();
				int rest = 8-row.length();
				
				if (row.length() < 8) {
					for (int a = 0; a < rest; a++) {					//fills uncompleted row with 0's
						row += "0";
					}
				} else if (row.length() > 10) {
					System.out.println("error");						//checks if row counts more than 10 numbers
				} 
				
				if (i != 0 || i != 9) {									//only runs within border
					for (int z = 1; z < 9; z++) {
						x = Integer.parseInt(row.substring(z-1, z));	//converts string to integers
							
						if (x != 0 && x != 1) {
							System.out.println("only 1 or 0 allowed");	//checks if 1 or 0
						} else {
							if (x == 1) {
								bord[i][z] = "x";
							} else if (x == 0) {
								bord[i][z] = ".";
							}
						}
					}
				}
			}
		}
	}
	
	//fill board by console 1 by 1
	/*
	public void read() {
		scanner = new Scanner(System.in);
		int x;

		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord.length; j++) {
				//creates border
				if (i == 0 || i == 9 || j == 0 || j == 9) {
					bord[i][j] = ".";
				} else {
					System.out.println("1 voor vol, 0 voor leeg");
					System.out.println(	"geef rij per rij in");
					System.out.println("coordinate: " + i + " " + j);
					
					x = scanner.nextInt();
					
					if (x == 1) {
						bord[i][j] = "x";
					} else if (x == 0) {
						bord[i][j] = ".";
					} else {
						System.out.println("error");
						j--;  //stay on same coordinate
					}
				}
			}
		}
	}*/
	
	//print board
	public void printBoard() {
		if (gen == 0) {
			System.out.println("starting point");
			System.out.println("-----------");
		} else {
			System.out.println();
			System.out.println("generation " + gen);
			System.out.println("-----------");
		}
		
		//print board without border		
		for (int i = 1; i < bord.length - 1; i++) {
			for (int j = 1; j < bord.length - 1; j++) {
				System.out.print(bord[i][j]);
			}
			System.out.println();
		}
	}
	
	//calculate amount of neighbours
	public int getNeighbours(int i, int j) {
		neighbours = 0; //reset neighbours

		if (bord[i-1][j-1].equals("x")) {
			//top left diagonal
			neighbours ++;
		}
		if (bord[i-1][j].equals("x")) {
			//top
			neighbours ++;
		}
		if (bord[i-1][j+1].equals("x")) {
			//top right diagonal
			neighbours ++;
		}
		if (bord[i][j-1].equals("x")) {
			// left
			neighbours ++;
		}
		if (bord[i][j+1].equals("x")) {
			//right
			neighbours ++;
		}
		if (bord[i+1][j-1].equals("x")) {
			//bottom left diagonal
			neighbours ++;
		}
		if (bord[i+1][j].equals("x")) {
			//bottom
			neighbours ++;
		}
		if (bord[i+1][j+1].equals("x")) {
			//bottom right diagonal
			neighbours ++;
		}
		
		return neighbours;
	}
	
	//create the next generation and check living conditions	
	public void nextGen() {
		for (int i = 1; i < bord.length - 1; i++) {
			for (int j = 1; j < bord.length - 1; j++) {
				
				//check whether column is occupied or not
				boolean occupied = false;
				if (bord[i][j].equals("x")) {
					occupied = true;
				}
				
				//call method to calculate neighbours
				getNeighbours(i, j);
				
				if (!occupied && neighbours == 3) {
					//birth
					bord[i][j] = "x";
				} else if (occupied && (neighbours > 3 || neighbours < 2)) {
					//death
					bord[i][j] = ".";
				} else {
					//survive
				}
			}
		}
		gen++;  //to display right generation while printing
	}
	
	public Scanner scanner;
	public String[][] bord;
	public int gen = 0;
	public int neighbours;
}
