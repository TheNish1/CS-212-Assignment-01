import java.util.Random;
class Book
{
    Random random = new Random();
    int bookID;
    String title;
    String author;
    String genre;
    boolean isAvailable=true;

    public Book (String Title, String Author, String Genre)
    {
        this.title = Title;
        this.author = Author;
        this.genre = Genre;
        //Random 5 digit ID
        this.bookID = random.nextInt(100000-10000)+10000;
    }
}
