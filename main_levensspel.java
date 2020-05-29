package levensspel;

import java.util.Scanner;

public class main_levensspel {
	
	public static Bord bord;
	public static Scanner scanner;

	public static void main(String[] args) {
		bord = new Bord();
		scanner = new Scanner(System.in);
		
		System.out.println("Generate a random situation (1) or make your own situation (2)");
		int choice = scanner.nextInt();
		System.out.println("How many generations would you like to simulate?");
		int xGen = scanner.nextInt();
		
		method(choice, xGen);
		
		scanner.close();
	}
	
	public static void method(int choice, int xGen) {
		if (choice == 1) {
			bord.randomBoard();
			simulate(xGen);
		} else if (choice == 2) {
			bord.read();
			simulate(xGen);
		}
	}
	
	public static void simulate(int xGen) {
		bord.printBoard();
		for (int i = 0; i < xGen; i ++) {
			bord.nextGen();
			bord.printBoard();
			if (i == xGen-1) {
				System.out.println();
				System.out.println("this was the last generation");
			} else {
				System.out.println();
				System.out.println("'n' to go to the next generation");
				scanner.next();
			}
		}
	}

}
