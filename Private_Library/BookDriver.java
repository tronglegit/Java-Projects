import java.io.IOException;
import java.util.Scanner;

public class BookDriver {
	public static void main(String[] args) throws IOException {
		int run = 0;
		while (run != 6) {
			Library library = new Library();
			@SuppressWarnings("resource")
			int number;
			Scanner choice = new Scanner(System.in);
			library.printstar();
			System.out.println("     	Hello. Welcome to your private Library. Numbers are options.");
			System.out.println("1.DISPLAY Books  2.ADD Books  3.DELETE Books  4.RESTORE deleted Books  5.SEARCH Books  6.EXIT");
            library.printstar();
			number = choice.nextInt();
			switch (number) {
			case 1:
				library.displayLibrary();
				break;
			case 2:
				library.addBook();
				break;
			case 3:
				library.removeBook();
				break;
			case 4:
				library.restoreBook();
				break;
			case 5:
				library.searchBook();
				break;
			case 6:
				run = 6;
				break;
			default:
				System.out.println("Invalid Option");
			}// switch
		}// while

		System.out.println("Thanks for using the program");
	}// Main

}
