package BookServices;

public class BookStoreApplication {
	public static void main(String[] args) {
		Book book = new Book("A Great Book", "John Doe", "Shelf 1, Room 101");

		book.updateCurrentPageContent("This is the content of the next page.");

		PlainTextPrinter plainTextPrinter = new PlainTextPrinter();
		plainTextPrinter.printPage(book.getCurrentPageContent());

		HtmlPrinter htmlPrinter = new HtmlPrinter();
		htmlPrinter.printPage(book.getCurrentPageContent());

		BookStoreService persistenceService = new BookStoreService();
		persistenceService.saveBook(book);

		LibraryLocationService locationService = new LibraryLocationService();
		String updatedLibraryLocation = locationService.determineLibraryLocation("Shelf 2", "Room 202");
		System.out.println("Updated Library Location: " + updatedLibraryLocation);
	}
}
