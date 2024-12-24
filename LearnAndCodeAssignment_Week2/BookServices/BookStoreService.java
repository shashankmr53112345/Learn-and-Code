
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class BookStoreService {
    public void saveBook(Book book) {
        String fileName = "./documents/" + book.getTitle().replace(" ", "_") + "_by_" 
                + book.getAuthor().replace(" ", "_") + ".ser";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(book);
            System.out.println("Book saved successfully to " + fileName);
        } catch (Exception exception) {
            System.err.println("Error saving the book: " + exception.getMessage());
        }
    }
}
