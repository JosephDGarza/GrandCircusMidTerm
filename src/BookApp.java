import java.util.ArrayList;
import java.util.Scanner;
 // Joseph Garza
// This app is created to be a library terminal

public class BookApp {

	public static void main(String[] args) {

		String userInput = " "; // string to accept user input
		String menuPrompt = " ";
		int userChoice = 0; // user menu selection
		ArrayList<Book> bookList = new ArrayList<>();
		Scanner scnr = new Scanner(System.in);

		Catalog.readFromFile(bookList); // Bringing the text file into the array list
		
		System.out.println("Welcome to The Grand Circus Library!");

		while (userChoice != 7) {
			
			userChoice = menu(scnr, userChoice);
			System.out.println();

			switch (userChoice) {
			case 1: //list books
				Catalog.libraryList(bookList);
				break;
			case 2: // search by author
				menuPrompt = "Please enter an AUTHOR search term\n";
				userInput = Validator.getString(scnr, menuPrompt);
				System.out.println();
				Catalog.SearchAuthor(userInput, bookList);
				break;
			case 3: // search by name
				menuPrompt = "Please enter a TITLE search term\n";
				userInput = Validator.getString(scnr, menuPrompt);
				System.out.println();
				Catalog.SearchTitle(userInput, bookList);
				break;
			case 4: // book checkout

				
				Catalog.displayAvailableBooks(bookList);
				System.out.println();
				System.out.println("Please enter book name or BookID to check out");
				userInput = scnr.nextLine();
				Catalog.bookCheckout(userInput, bookList);
				break;
			case 5: // book return
				
				Catalog.displayCheckedOutBooks(bookList);
				System.out.println();
				System.out.println("Please enter book name or BookID to return");
				userInput = scnr.nextLine();
				Catalog.returnABook(userInput, bookList);
				break;
			case 6 : // Add a book
				Catalog.addBook(userInput, bookList);
				break;
			case 7 : // quit
				System.out.println("Thank you! Good-Bye!");
				break;
			default:
				System.out.println();
				break;
			}
		}

		// Once finished, this writes the ArrayList into the text 
		// file with the updated information.
		Catalog.writeToFile(bookList); 
	}

	public static int menu(Scanner sc, int i) {

		String prompt = "\nPlease select one of the options below.\n" 
						+ "\t1. Display list of books\n"
						+ "\t2. Search for a book by AUTHOR\n" 
						+ "\t3. Search for a book by TITLE Keyword\n"
						+ "\t4. Select a book from the list to check out\n" 
						+ "\t5. Return a book\n" 
						+ "\t6. Add a book\n"
						+ "\t7. Quit\n";

		return Validator.getInt(sc, prompt, 1, 7);
	}
}
