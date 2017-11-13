
public class Book extends LibraryItem {
	 // Joseph Garza
	// subclass of Library to create book objects
	private String author = " ";

	//overloaded constructor
	public Book(String name, String author, String idNum, String status, String date) {
		super(name, idNum, status, date);
		this.author = author;
	}

	
	//all of our getters and setters
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String LibraryItem() {
		return null;
	}

	//overriding our toString method
	@Override
	public String toString() {
		return name + "/ " + author + "/ " + idNum + "/ " + status + "/ " + date;
	}
}
