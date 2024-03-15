import java.util.Scanner;
public class LibraryManagementSystem {

    public static void main (String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System!");
        System.out.println("Select an option by typing its number:");
        System.out.println("1. Add a book to catalogue");
        System.out.println("2. Add a new user");
        System.out.println("3. Display all books");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Search for book with a title");
        System.out.println("7. Search for book with author name");
        System.out.println("8. Stop");


        Library library = new Library();
        boolean again=true;

        do
        {
            System.out.print("Option: ");
            int userInput = input.nextInt();

            switch (userInput)
            {
                case 1 : library.newBook(); break;
                case 2 : library.newUser(); break;
                case 3 : library.displayBooks(); break;
                case 4 : library.borrowBook(); break;
                case 5 : library.returnBook(); break;
                case 6 : library.searchBookbyTitle(); break;
                case 7 : library.searchBookbyAuthor(); break;
                case 8 :
                     {
                         again = false;
                         System.out.println("Program has been stopped");
                         break;
                     }

                default:
                    System.out.println("Error");
            }
        }while(again);

    }
}


