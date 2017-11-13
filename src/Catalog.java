import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
// Joseph Garza
//All methods hosted here to check out, return, write to text file and read from text file
public class Catalog {

	public static void displayAvailableBooks(ArrayList<Book> arrayList1) {
		for (int i = 0; i < arrayList1.size(); i++) {
			if (arrayList1.get(i).getStatus().contains("Available")) {
				System.out.print(String.format("%-13s", "Bookid: " + arrayList1.get(i).getIdNum()));
				System.out.print(String.format("%-70s", "Title: " + arrayList1.get(i).getName()));
				System.out.print(String.format("%-30s", "Status: " + arrayList1.get(i).getStatus()));
				System.out.println();
			}
		}
	}

	// THIS METHOD IS TO DETERMINE IF A LIBRARY ITEM IS CHECKED OUT. IF NOT, TO CHECK IT OUT
	public static ArrayList bookCheckout(String input, ArrayList<Book> arrayList1) {
		Date dateOfOrder;
		dateOfOrder = new Date();
		int noOfDays = 14; // 2 week return time
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfOrder);
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays); // adds the 2 weeks to the date for retrning
		Date returnDate = calendar.getTime();
		String returnTime = "Return " + returnDate.toString(); // creates the string for date to input

		for (int i = 0; i < arrayList1.size(); i++) {

			// This tests to see if input is equal to the ID of the item.
			if (arrayList1.get(i).getIdNum().equalsIgnoreCase(input)) {
				// This tests status to see if it is checked out or not
				if (arrayList1.get(i).getStatus().contains("Available")) { 
					// if status isn't set to change it to checked out
					System.out.println("You checked out " + arrayList1.get(i).getName()); 
					arrayList1.get(i).setStatus("Checked out");
					arrayList1.get(i).setDate(returnTime);
					System.out.println("Return by:" + arrayList1.get(i).getdate());
					return arrayList1;
				}
				
				// if book is checked out this is returned
				if (arrayList1.get(i).getStatus().contains("Checked out")) { 
					System.out.println(arrayList1.get(i).getName() + " is already checked out!");
					return arrayList1;
				}
			}
		}

		for (int i = 0; i < arrayList1.size(); i++) {

			// This tests to see if input is equal to the name of the item.
			if (arrayList1.get(i).getName().equalsIgnoreCase(input)) {
				// This tests status to see if it is checked out or not
				if (arrayList1.get(i).getStatus().contains("Available")) { 

					// if status isn't set to change it to checked out
					System.out.println("\nYou checked out " + arrayList1.get(i).getName()); 
					arrayList1.get(i).setStatus("Checked out");
					arrayList1.get(i).setDate(returnTime);
					System.out.println("Due Date:" + arrayList1.get(i).getdate());
					return arrayList1;
				}
				// if book is checked out this is returned
				if (arrayList1.get(i).getStatus().contains("Checked out")) { 
					System.out.println(arrayList1.get(i).getName() + " is already checked out!");
					return arrayList1;
				}
			}
		}

		System.out.println("That wasn't an option!");

		return arrayList1;
	}

	// reads from text file to write arraylist
	public static ArrayList readFromFile(ArrayList<Book> bookList) {
		Path writeFile = Paths.get("BookList.txt");
		File file = writeFile.toFile();

		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();

			while (line != null) {
				// Grabs the information on the line up until the point of finding the back slash.
				// Then adds it to the array list accordingly.
				String[] lineArr = line.split("/ "); 
				bookList.add(new Book(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4]));
				// scnr.nextLine essentially, pushes to the next line
				line = reader.readLine(); 
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	// Writes to the text file from the arraylist
	public static void writeToFile(ArrayList<Book> arrayList1) {
		Path writeFile = Paths.get("BookList.txt");
		File file = writeFile.toFile();
		try {

			PrintWriter out = new PrintWriter(new FileOutputStream(file));
			// iterates through the arraylist to out print each array on a line
			for (Book book : arrayList1) { 
				out.println(book);
			}
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found here...");
			e.printStackTrace();
		}
	}

	// used to print list of current library books and status
	public static void libraryList(ArrayList<Book> arrayList1) { 

		for (int i = 0; i < arrayList1.size(); i++) {
			System.out.print(String.format("%-13s", "Bookid: " + arrayList1.get(i).getIdNum()));
			System.out.print(String.format("%-70s", "Title: " + arrayList1.get(i).getName()));
			System.out.print(String.format("%-35s", "Author: " + arrayList1.get(i).getAuthor()));
			System.out.print(String.format("%-30s", "This book is currently: " + arrayList1.get(i).getStatus()));
			System.out.println();
		}
	}

	public static void SearchTitle(String input, ArrayList<Book> list) {
		String tempValue;
		input = input.toLowerCase();

		for (int i = 0; i < list.size(); i++) {
			tempValue = list.get(i).getName();

			if (tempValue.toLowerCase().contains(input.toLowerCase())) {
				System.out.print(String.format("%-13s", "Bookid: " + list.get(i).getIdNum()));
				System.out.print(String.format("%-70s", "Title: " + list.get(i).getName()));
				System.out.print(String.format("%-35s", "Author: " + list.get(i).getAuthor()));
				System.out.print(String.format("%-30s", "Status: " + list.get(i).getStatus()));
				System.out.println();
			}
		}
		System.out.println("Your search is complete.");
	}

	public static void SearchAuthor(String input, ArrayList<Book> list) {
		String tempValue;
		input = input.toLowerCase();

		for (int i = 0; i < list.size(); i++) {
			tempValue = list.get(i).getAuthor();
			if (tempValue.toLowerCase().contains(input.toLowerCase())) {
				System.out.print(String.format("%-13s", "Bookid: " + list.get(i).getIdNum()));
				System.out.print(String.format("%-70s", "Title: " + list.get(i).getName()));
				System.out.print(String.format("%-35s", "Author: " + list.get(i).getAuthor()));
				System.out.print(String.format("%-30s", "Status: " + list.get(i).getStatus()));
				System.out.println();
			}
		}

		System.out.println("Your search is complete.");
	}

	public static void readFromFile(String nameOfFile) {
		Path writeFile = Paths.get(nameOfFile);
		File file = writeFile.toFile();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static void displayArrayListFromFile() {
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new FileReader("BookList.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> listOfLines = new ArrayList<>();
		String line = null;
		try {
			line = bufReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			listOfLines.add(line);
			try {
				line = bufReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(listOfLines);
	}

	// This method displays a list of Checked Out Books. The user can select from
	// this list to choose which book to return.
	public static void displayCheckedOutBooks(ArrayList<Book> arrayList1) {
		for (int i = 0; i < arrayList1.size(); i++) {
			if (arrayList1.get(i).getStatus().contains("Checked out")) {
				System.out.print(String.format("%-13s", "Bookid: " + arrayList1.get(i).getIdNum()));
				System.out.print(String.format("%-70s", "Title: " + arrayList1.get(i).getName()));
				System.out.print(String.format("%-30s", "Status: " + arrayList1.get(i).getStatus()));
				System.out.print(String.format("%-30s", "This book is due on: " + arrayList1.get(i).getdate() + "\n"));
			}
		}
	}

	public static ArrayList returnABook(String userChoice, ArrayList<Book> arrayList1) {
		for (int i = 0; i < arrayList1.size(); i++) {

			// This tests to see if input is equal to the ID of the item.
			if (arrayList1.get(i).getIdNum().equalsIgnoreCase(userChoice)) {
				// This tests status to see if it is checked out or not
				if (arrayList1.get(i).getStatus().contains("Checked out")) { 
					// if status isn't set to change it to available
					System.out.println("You returned: " + arrayList1.get(i).getName()); 
					arrayList1.get(i).setStatus("Available");
					arrayList1.get(i).setDate(" ");
					return arrayList1;
				}
				
				// if book is available this is returned
				if (arrayList1.get(i).getStatus().contains("Available")) { 
					System.out.println(arrayList1.get(i).getName() + " cannot be returned.");
					return arrayList1;
				}
			}
		}

		for (int i = 0; i < arrayList1.size(); i++) {

			// This tests to see if input is equal to the name of the item.
			if (arrayList1.get(i).getName().equalsIgnoreCase(userChoice)) {
				// This tests status to see if it is checked out or not
				if (arrayList1.get(i).getStatus().contains("Checked out")) { 

					// if status isn't set to change it to available
					System.out.println("\nYou returned: " + arrayList1.get(i).getName()); 
					arrayList1.get(i).setStatus("Available");
					arrayList1.get(i).setDate(" ");
					return arrayList1;
				}
				// if book is available this is returned
				if (arrayList1.get(i).getStatus().contains("Available")) { 
					System.out.println(arrayList1.get(i).getName() + " cannot be returned.");
					return arrayList1;
				}
			}
		}
		

		System.out.println("That wasn't an option!");

		return arrayList1;
	}
	
	public static ArrayList addBook (String userChoice, ArrayList <Book> arrayList) {
		Scanner sc = new Scanner(System.in);
		String bookID = " ";
		
		System.out.println("Enter book title: ");
		String title = sc.nextLine();
		System.out.println("Enter book Author: ");
		String author = sc.nextLine();
		
		
		
		System.out.println("Enter book ID: ");
		bookID = sc.nextLine();
		for (int i = 0; i < arrayList.size(); i++) {
		while (bookID.equalsIgnoreCase(arrayList.get(i).getIdNum())) {
			System.out.println("Sorry that book id is taken, try another one.");
			bookID = sc.nextLine();
		}}
		String status = "Available";
		String date = " ";
		
		arrayList.add(new Book(title, author, bookID, status, date));
		System.out.println("You added: Title: " + title + " Author: " + author);
		
		return arrayList;
}
	
}	

