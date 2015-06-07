public class Genre extends Book {
	private String genre;
	private String pages;

	// Default constructor
	public Genre() {

	}

	// User constructor
	// 6 parameter string type
	public Genre(String t, String a, String i, String y, String g, String p) {
		super(t, a, i, y);
		genre = g;
		pages = p;

	}

	// accessor method that return genre
	public String getGenre() {
		return genre;
	}

	// accessor method that return pages
	public String getPages() {
		return pages;
	}

	// toString return a string on genre and pages.
	// internal call to parent to string
	public String toString() {
		// return String
		return ("\n" + super.toString() + "\nGenre: " + genre + "\nPages: "
				+ pages + "\n" + "===================================");
	}

}
