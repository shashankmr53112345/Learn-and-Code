
public class Book {
    private String title;
    private String author;
    private String currentPageContent;
    private String libraryLocation;

    public Book(String title, String author, String libraryLocation) {
        this.title = title;
        this.author = author;
        this.libraryLocation = libraryLocation;
        this.currentPageContent = "Initial Page Content";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCurrentPageContent() {
        return currentPageContent;
    }

    public String getLibraryLocation() {
        return libraryLocation;
    }

    public void updateCurrentPageContent(String newPageContent) {
        this.currentPageContent = newPageContent;
    }
}
