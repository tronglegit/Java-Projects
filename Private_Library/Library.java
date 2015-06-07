import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

// An array instance variable (field) is used here to hold all the  Book objects 

public class Library {
	// arraylist of library
	ArrayList<Genre> library;
	// arraylist of objects that have been deleted
	ArrayList<Genre> deletedFile = new ArrayList<Genre>();

	// Declares/Instantiates array library, an array of Book, with final int as
	// size
	public Library() {
		library = new ArrayList<Genre>();
	}

	// Accessor Method for reading data from file
	public ArrayList<Genre> readFile(Scanner scan) {
		ArrayList<Genre> l = new ArrayList<Genre>();

		String title = "";
		String author = "";
		String isbn = "";
		String year = "";
		String genre = "";
		String pages = "";

		while (scan.hasNext()) // at beginning of a line, tests if there is more
								// read in file
		{

			// Read the data from the file and assign to variables
			title = scan.nextLine();
			author = scan.nextLine();
			isbn = scan.nextLine();
			year = scan.nextLine();
			genre = scan.nextLine();
			pages = scan.nextLine();

			Genre b = new Genre(title, author, isbn, year, genre, pages);
			l.add(b);

		} // while
		return l;
	} // readFile()

	public void displayLibrary() throws FileNotFoundException {
		File file = new File("BookData.txt");
		Scanner fileScan = new Scanner(file);

		ArrayList<Genre> lists = new ArrayList<Genre>();
		lists = readFile(fileScan);
		String words = "";
		int numbook = lists.size();
		for (Genre genre : lists) {
			words += genre.toString();
		}
		if (!words.equals("")) {
			System.out.println(words);
			System.out.println("Total Books: " + numbook);
		} else
			System.out.println("No Books in library.Thank You.");

	}

	public void addBook() throws IOException {
		String title = "";
		String author = "";
		String isbn = "";
		String year = "";
		String genre = "";
		String pages = "";
		Scanner keyboard = new Scanner(System.in);

		// Getting data from user
		System.out.println("Please enter a book title: ");
		title = keyboard.nextLine();
		System.out.println("Please enter author name:  ");
		author = keyboard.nextLine();
		System.out.println("Please enter ISBN:  ");
		isbn = keyboard.nextLine();
		System.out.println("Please enter pulished year:  ");
		year = keyboard.nextLine();
		System.out.println("Please enter genre:  ");
		genre = keyboard.nextLine();
		System.out.println("Please enter pages:  ");
		pages = keyboard.nextLine();

		String fileName = "BookData.txt";
		PrintWriter writer = null;

		// Writing user input data on file
		FileWriter fw = new FileWriter(fileName, true);
		writer = new PrintWriter(fw);
		writer.println(title.toUpperCase());
		writer.println(author.toUpperCase());
		writer.println(isbn.toUpperCase());
		writer.println(year.toUpperCase());
		writer.println(genre.toUpperCase());
		writer.println(pages.toUpperCase());

		writer.close();
		printstar();
		System.out.println("						BOOK ADDED");
		printstar();

	}

	public void removeBook() throws IOException {
		// file
		File file = new File("BookData.txt");
		Scanner fileScan = new Scanner(file);

		ArrayList<Genre> lists = new ArrayList<Genre>();

		lists = readFile(fileScan);

		int remove = -1;
		Scanner key = new Scanner(System.in);
		// get user inputs
		System.out.println("Please enter a title to delete:  ");
		String title = key.nextLine();
		title = title.toUpperCase();
         // find title
		for (int i = 0; i < lists.size(); i++) {
			if (title.equals(lists.get(i).getTitle())) {
				remove = i;
				// add deleted book to deletedFile arraylist
				deletedFile.add(lists.get(remove));
				break;
			}

		}
		// remove book from lists
		if (remove != -1) {
			lists.remove(remove);
		}
		// message
		printstar();
		System.out.println("					FOUND AND DELETED");
		printstar();
		// display deleted book
		for (Genre g : deletedFile) {
			System.out.println(g);
		}
		// update deletedFile.txt
		String deleted = "deletedFile.txt";
		PrintWriter pwriter = null;

		FileWriter fwriter = new FileWriter(deleted, true);
		pwriter = new PrintWriter(fwriter);
		for (Genre g : deletedFile) {
			pwriter.println(g.getTitle());
			pwriter.println(g.getAuthor());
			pwriter.println(g.getISBN());
			pwriter.println(g.getYear());
			pwriter.println(g.getGenre());
			pwriter.println(g.getPages());
		}
		pwriter.close();

		// update BookData.txt
		String fileName = "BookData.txt";
		PrintWriter writer = null;

		FileWriter fw = new FileWriter(fileName, false);
		writer = new PrintWriter(fw);
		for (Genre genre : lists) {
			writer.println(genre.getTitle());
			writer.println(genre.getAuthor());
			writer.println(genre.getISBN());
			writer.println(genre.getYear());
			writer.println(genre.getGenre());
			writer.println(genre.getPages());
		}
		writer.close();

	}

	public void restoreBook() throws IOException {
		// file
		File file = new File("deletedFile.txt");
		Scanner fileScan = new Scanner(file);

		ArrayList<Genre> lists = new ArrayList<Genre>();
		lists = readFile(fileScan);
		String display = "";
		@SuppressWarnings("resource")
		// get user inputs
		Scanner key = new Scanner(System.in);
		System.out
				.println("You can search by title, author, genre, ISBN or year to display the book you want to"
						+ "\n restore or keyword \"all\" for all books in the deletedFile: ");
		
		String title = key.nextLine();
		title = title.toUpperCase();
		
		// if a user wants to display all books
		if(title.equals("ALL")){
		String w = "";
		for (Genre genre : lists) {
			w += genre.toString();
		}
		System.out.println(w);
		}// end if
	
		
		// search by title, author, isbn, pages, genre or year
		for (Book bk : lists) {
			if (title.equals(bk.getTitle())) {
				display = bk.toString();
				System.out.println(display);
			}
			if (title.equals(bk.getAuthor())) {
				display = bk.toString();
				System.out.println(display);

			}
			if (title.equals(bk.getISBN())) {
				display = bk.toString();
				System.out.println(display);

			}

			if (title.equals(bk.getYear())) {
				display = bk.toString();
				System.out.println(display);

			}
		}// end for
		
		for (Genre genre : lists) {
			if (title.equals(genre.getGenre())) {
				display = genre.toString();
				System.out.println(display);

			}// end first if

			if (title.equals(genre.getPages())) {
				display = genre.toString();
				System.out.println(display);
			}// end second if
		}// end for

		@SuppressWarnings("resource")
		
		// ask user what book title to restore
		Scanner keybd = new Scanner(System.in);
		System.out.println("Please enter a title to restore:  ");
		String titl = keybd.nextLine();
		titl = titl.toUpperCase();

		//update BookData.txt after restored
		String restoreBook = "BookData.txt";
		PrintWriter prwriter = null;
		FileWriter fiwriter = new FileWriter(restoreBook, true);
		prwriter = new PrintWriter(fiwriter);
		int removeDeleted = -1;
		for (int i = 0; i < lists.size(); i++) {
			if (titl.equals(lists.get(i).getTitle())) {
				removeDeleted = i;
				prwriter.println(lists.get(i).getTitle());
				prwriter.println(lists.get(i).getAuthor());
				prwriter.println(lists.get(i).getISBN());
				prwriter.println(lists.get(i).getYear());
				prwriter.println(lists.get(i).getGenre());
				prwriter.println(lists.get(i).getPages());
				break;
			}// end if

		}// end for
		prwriter.close();
        // message successfully restored
		printstar();
		System.out.println("BOOK RESTORED");
		printstar();

		// remove deleted object
		if (removeDeleted != -1) {
			lists.remove(removeDeleted);
		}
		// update deletedFile.txt

		PrintWriter printwriter = null;
		FileWriter filewriter = new FileWriter(file, false);
		printwriter = new PrintWriter(filewriter);
		for (Genre genre : lists) {
			printwriter.println(genre.getTitle());
			printwriter.println(genre.getAuthor());
			printwriter.println(genre.getISBN());
			printwriter.println(genre.getYear());
			printwriter.println(genre.getGenre());
			printwriter.println(genre.getPages());
		}
		printwriter.close();

	}// end restoreBook

	public void searchBook() throws FileNotFoundException {
		// file
		File file = new File("BookData.txt");
		Scanner fileScan = new Scanner(file);

		ArrayList<Genre> lists = new ArrayList<Genre>();
		lists = readFile(fileScan);
		String display = "";
		@SuppressWarnings("resource")
		// ask user for what they want to search
		Scanner key = new Scanner(System.in);
		System.out
				.println("You can search by title, author, genre, ISBN or year: ");
		String title = key.nextLine();
		title = title.toUpperCase();
        // search by title, author, year, genre, pages or isbn
		for (Book bk : lists) {
			if (title.equals(bk.getTitle())) {
				display = bk.toString();
				System.out.println(display);
			}
			if (title.equals(bk.getAuthor())) {
				display = bk.toString();
				System.out.println(display);

			}
			if (title.equals(bk.getISBN())) {
				display = bk.toString();
				System.out.println(display);

			}

			if (title.equals(bk.getYear())) {
				display = bk.toString();
				System.out.println(display);

			}
		}
		for (Genre genre : lists) {
			if (title.equals(genre.getGenre())) {
				display = genre.toString();
				System.out.println(display);

			}

			if (title.equals(genre.getPages())) {
				display = genre.toString();
				System.out.println(display);
			}
		}
	}

	public static void printstar() {
		// print beautiful stars ever
		for (int i = 0; i < 100; i++)
			System.out.print("*");
		System.out.println("\n");
	}// printstar
} // class Library