
public abstract class LibraryItem {
	String name;
	String idNum;
	String status; // checked out or returned value. if returned " "
	String date; // date if the book is checked out or not

	public abstract String LibraryItem();

	//overloaded constructor
	public LibraryItem(String name, String idNum, String status, String date) {
		super();
		this.name = name;
		this.idNum = idNum;
		this.status = status;
		this.date = date;
	}

	//all of our getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getdate() {
		return date;
	}

	//overriding our toString method
	@Override
	public String toString() {
		return name + "/ " + idNum + "/ " + status + "/ " + date;
	}
}
