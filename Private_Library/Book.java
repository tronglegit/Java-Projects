class Book {

	private String title;
	private String author;
	private String isbn;
	private String year;

	// Default constructor
	public Book() {

	}

	// User constructor
	// 4 parameter string type
	public Book(String t, String a, String i, String y) {
		title = t;
		author = a;
		isbn = i;
		year = y;

	}

	// Accessor method that return title
	public String getTitle() {
		return title;
	}

	// Accessor method that return author
	public String getAuthor() {
		return author;
	}

	// Accessor method that return isbn
	public String getISBN() {
		return isbn;
	}

	// Accessor method that return year
	public String getYear() {
		return year;
	}

	// Mutator method sets title
	public void setTitle(String t) {

		title = t;
	}

	// Mutator method sets author
	public void setAuthor(String a) {

		author = a;
	}

	// Mutator method sets isbn
	public void setISBN(String i) {

		isbn = i;
	}

	// Mutator method sets year
	public void setYear(String y) {

		year = y;
	}

	// toString to display books
	public String toString() {

		return ("Book Title: " + title + "\n" + "Book Author: " + author + "\n"
				+ "Book ISBN: " + isbn + "\n" + "Book Published Year: " + year);
	}

}
