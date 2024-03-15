import java.util.Random;
class User
{
    //Object for random user ID
    Random random = new Random();

    //User attributes
    private int userID;
    private String name;
    private String contact;
    private String[] borrowedBooks;
    private int bookCurrentEmptySlot=0;

    //Setter for books
    public void booktoBorrow(String bookName)
    {

        boolean isBookAdded = false;
        while(isBookAdded==false)
        {
            if(this.borrowedBooks[bookCurrentEmptySlot].isEmpty())
            {
                this.borrowedBooks[bookCurrentEmptySlot] = bookName;
                isBookAdded=true;
            }
            else
            {
                bookCurrentEmptySlot++;
            }
        }
    }
    public void booktoReturn(int i)
    {
        this.borrowedBooks[i] = null;
    }

    //Constructor
    public User (String Name, String Contact)
    {
        this.name=Name;
        this.contact=Contact;
        //Random 5 digit ID
        this.userID = random.nextInt(100000-10000)+10000;
    }

    //Getters
    public String getUserName()
    {return this.name;}

    public String getBookatIndex(int i)
    {
        return borrowedBooks[i];
    }

}
