import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Library {
	// Using TreeSet for sorted order and faster search
	private Set<String> books = new TreeSet<>();

	// A simple caching mechanism using a ConcurrentHashMap
	private Map<String, Boolean> searchCache = new ConcurrentHashMap<>();

	// Add book to the library
	public void addBook(String bookName) {
		books.add(bookName);
		System.out.println(bookName + " added to library.");
		// Clear the cache because book data has changed
		searchCache.clear();
	}

	// List all books
	public void listBooks() {
		System.out.println("Books in library: " + books);
	}

	// Remove book from the library
	public void removeBook(String bookName) {
		if (books.remove(bookName)) {
			System.out.println(bookName + " removed from library.");
			// Clear the cache as books have been modified
			searchCache.clear();
		} else {
			System.out.println(bookName + " not found in library.");
		}
	}

	// Optimized search with caching mechanism
	public boolean searchBook(String bookName) {
		// First check in the cache for previous search results
		if (searchCache.containsKey(bookName)) {
			System.out.println("Search result (from cache): " + searchCache.get(bookName));
			return searchCache.get(bookName);
		}

		// Perform search
		boolean found = books.contains(bookName);

		// Store the result in the cache
		searchCache.put(bookName, found);

		System.out.println("Search result: " + found);
		return found;
	}

	// Performance testing by adding a batch of books
	public void addBooksBatch(List<String> bookNames) {
		books.addAll(bookNames);
		searchCache.clear();
		System.out.println(bookNames.size() + " books added to library.");
	}
}

public class Main {
	public static void main(String[] args) {
		Library myLibrary = new Library();

		// Adding books
		myLibrary.addBook("Harry Potter");
		myLibrary.addBook("Lord of the Rings");

		// Searching books
		myLibrary.searchBook("Harry Potter");
		myLibrary.searchBook("The Hobbit"); // Not found
		myLibrary.searchBook("Harry Potter"); // Cached result

		// Listing books
		myLibrary.listBooks();

		// Removing a book
		myLibrary.removeBook("Harry Potter");

		// Testing after removing
		myLibrary.searchBook("Harry Potter");

		// Performance testing with a batch add
		myLibrary.addBooksBatch(Arrays.asList("Book A", "Book B", "Book C"));
		myLibrary.listBooks();
	}
}
