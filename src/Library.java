import java.util.ArrayList;
   import java.util.List;

   public class Library {
       private List<String> books = new ArrayList<>();

       public void addBook(String bookName) {
           books.add(bookName);
           System.out.println(bookName + " added to library.");
       }

       public void listBooks() {
           System.out.println("Books in library: " + books);
       }   }
 
