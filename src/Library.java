import java.util.Objects;
import java.util.Scanner;

public class Library {
    private static int userCount=0, bookCount=0;

    private User[] listofUsers = new User[100];
    private Book[] listofBooks = new Book[100];

    Scanner input = new Scanner(System.in);


    //Title Case converter
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }

    //Number checker
    public static boolean areAllDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false; // If any character is not a digit, return false
            }
        }
        return true; // All characters are digits
    }



    //Library Function Methods
    public void newBook ()
    {
        boolean isSuccess=false;
        //Inputs
        System.out.println("ADD BOOK");
        System.out.print("Enter book title: ");
        String book = input.nextLine();
        System.out.print("Enter book author: ");
        String author = input.nextLine();
        System.out.print("Enter genre: ");
        String genre = input.nextLine();

        book = toTitleCase(book);
        author = toTitleCase(author);
        genre = toTitleCase(genre);

        //Checking none are empty
        if ((!book.isEmpty())&&(!author.isEmpty())&&(!genre.isEmpty()))
        {
            //Creating book object in array
            listofBooks[bookCount] = new Book (book, author, genre);
            bookCount++;
            isSuccess = true;
        }
        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else {System.out.println("Error.");}
    }
    public void newUser()
    {
        boolean isSuccess=false;
        //Inputs
        System.out.println("ADD USER");
        System.out.print("Enter user name: ");
        String name = input.nextLine();
        System.out.print("Enter user contact: (11 digit number): ");
        String contact = input.nextLine();

        name = toTitleCase(name);
        boolean allDigits = areAllDigits(contact);

         if ((!name.isEmpty())&&(contact.length()==11)&&(allDigits))
        {
            listofUsers[userCount] = new User (name, contact);
            userCount++;
            isSuccess = true;
        }
        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else {System.out.println("Error.");}
    }
    public void borrowBook ()
    {
        boolean isSuccess=false;
        System.out.println("BORROW BOOK");
        System.out.print("Enter user name: ");
        String userName = input.nextLine();
        System.out.print("Enter book name: ");
        String bookName = input.nextLine();
        bookName = toTitleCase(bookName);
        userName = toTitleCase(userName);

        //If and where book exists in catalogue
        if((!bookName.isEmpty())&&(!userName.isEmpty()))
        {
            int bookIndex;
            boolean bookExists=false;
            for (bookIndex=0; bookIndex<=bookCount; ++bookIndex)
            {
                if (Objects.equals(listofBooks[bookIndex].title, bookName))
                {
                    bookExists = true;
                    break;
                }
            }
            if(bookExists)
            {
                //if book available for borrowing
                if (listofBooks[bookIndex].isAvailable)
                {
                    int i;
                    //Does User Exist
                    for (i = 0; i <= userCount; i++) {
                        String tempUserName = listofUsers[i].getUserName();
                        if (Objects.equals(tempUserName, userName)) {
                            break;
                        }
                    }
                    listofUsers[i].booktoBorrow(bookName);
                    isSuccess=true;
                }
            }
            else {System.out.println("Error");}
        }
        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else System.out.println("Error");
    }

    public void returnBook()
    {
        boolean isSuccess=false;
        System.out.println("RETURN BOOK");
        System.out.print("Enter user name: ");
        String userName = input.nextLine();
        System.out.print("Enter book name: ");
        String bookName = input.nextLine();
        bookName = toTitleCase(bookName);
        userName = toTitleCase(userName);

        if ((!bookName.isEmpty())&&(!userName.isEmpty()))
        {
            //If and where user exists
            int userIndex;
            for (userIndex=0; userIndex<=bookCount; userIndex++)
            {
                if (Objects.equals(listofUsers[userIndex].getUserName(), userName))
                {
                    break;
                }
            }

            int userBookIndex;
            //Where is book in user's array
            for(userBookIndex=0;!listofUsers[userIndex].getBookatIndex(userBookIndex).isEmpty(); userBookIndex++)
            {
                if(Objects.equals(listofUsers[userIndex].getBookatIndex(userBookIndex), bookName))
                {
                    break;
                }
                //Return the book
                listofUsers[userIndex].booktoReturn(userBookIndex);

                //where book exists in catalogue
                int bookIndex;
                for (bookIndex=0; bookIndex<=bookCount; bookIndex++)
                {
                    if (Objects.equals(listofBooks[bookIndex].title, bookName))
                    {
                        break;
                    }
                }
                //Make book available again
                listofBooks[bookIndex].isAvailable=true;
                isSuccess=true;
            }
        }
        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else {
            System.out.println("Error.");}
    }

    public void displayBooks()
    {
        boolean isSuccess=false;
        System.out.println("BOOK CATALOGUE");
        int i =0;
        if (bookCount!=0) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "TITLES","AUTHORS","GENRES","ID", "AVAILABLE");
            for (i=0; i<bookCount; i++)
            {
                System.out.printf("%-20s", listofBooks[i].title);
                System.out.printf("%-20s",listofBooks[i].author);
                System.out.printf("%-20s",listofBooks[i].genre);
                System.out.printf("%-20s",listofBooks[i].bookID);
                System.out.printf("%-20s%s",listofBooks[i].isAvailable,"\n");
                isSuccess = true;
            }
        }
        else {System.out.println("Book catalogue is empty.");}

        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
    }

    public void searchBookbyTitle()
    {
        boolean isSuccess=false;
        System.out.println("SEARCH BOOK BY TITLE");
        System.out.print("Enter title: ");
        String bookTitle = input.nextLine();
        bookTitle = toTitleCase(bookTitle);


        if (!bookTitle.isEmpty()) {
            boolean bookFound = false;
            int count;
            for (count = 0; count<=bookCount; count++)
            {
                if(Objects.equals(listofBooks[count].title, bookTitle))
                {
                    bookFound = true;
                    break;
                }
            }

            if(bookFound)
            {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "TITLE","AUTHOR","GENRE","ID", "AVAILABLE");
                System.out.printf("%-20s",listofBooks[count].title);
                System.out.printf("%-20s",listofBooks[count].author);
                System.out.printf("%-20s",listofBooks[count].genre);
                System.out.printf("%-20s",listofBooks[count].bookID);
                System.out.printf("%-20s%s",listofBooks[count].isAvailable,"\n");
                isSuccess = true;

            }
            else
            {
                System.out.println("Book not found.");
            }

        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else {
            System.out.println("Error.");
        }

            }
}

    public void searchBookbyAuthor()
    {
        boolean isSuccess=false;
        System.out.println("SEARCH BOOK BY AUTHOR");
        System.out.print("Enter author: ");
        String bookAuthor = input.nextLine();
        bookAuthor = toTitleCase(bookAuthor);


        if (!bookAuthor.isEmpty()) {
            boolean bookFound = false;
            int count;
            for (count = 0; count<=bookCount; count++)
            {
                if(Objects.equals(listofBooks[count].author, bookAuthor))
                {
                    bookFound = true;
                    break;
                }
            }

            if(bookFound)
            {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "TITLE","AUTHOR","GENRE","ID", "AVAILABLE");
                System.out.printf("%-20s",listofBooks[count].title);
                System.out.printf("%-20s",listofBooks[count].author);
                System.out.printf("%-20s",listofBooks[count].genre);
                System.out.printf("%-20s",listofBooks[count].bookID);
                System.out.printf("%-20s%s",listofBooks[count].isAvailable,"\n");
                isSuccess = true;

            }
            else
            {
                System.out.println("Book not found.");
            }
        }
        if (isSuccess){
            System.out.println("\nOperation Successful\n\n");
        }
        else {
            System.out.println("Error.");
        }

    }

}

