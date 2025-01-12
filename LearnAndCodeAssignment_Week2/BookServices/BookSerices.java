
public class BookSerices {
    public static void main(String[] args) {
        // Create a book instance
        Book book = new Book("A Great Book", "John Doe", "Shelf 1, Room 101");

        // Update the content of the current page
        book.updateCurrentPageContent("This is the content of the next page.");

        // Print the current page using PlainTextPrinter
        PlainTextPrinter plainTextPrinter = new PlainTextPrinter();
        plainTextPrinter.printPage(book.getCurrentPageContent());

        // Print the current page using HtmlPrinter
        HtmlPrinter htmlPrinter = new HtmlPrinter();
        htmlPrinter.printPage(book.getCurrentPageContent());

        // Save the book to a file
        BookStoreService persistenceService = new BookStoreService();
        persistenceService.saveBook(book);

        // Determine and print the library location for the book
        LibraryLocationService locationService = new LibraryLocationService();
        String updatedLibraryLocation = locationService.determineLibraryLocation("Shelf 2", "Room 202");
        System.out.println("Updated Library Location: " + updatedLibraryLocation);
    }
}
