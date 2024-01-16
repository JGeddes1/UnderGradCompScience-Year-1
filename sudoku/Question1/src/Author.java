
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Author {
    private static String name;
    private static String email;
    private static char gender;
    private static Book obj;
    static ArrayList<String> names = new ArrayList();
    static ArrayList<String> stock = new ArrayList();

    public Author() {
        name = "Bob";
        email = "@gmail";
        gender = 'm';
//        System.out.println("The author name is: " + name);
//        System.out.println("The author email is: " + email);
//        System.out.println("The author gender is: " + gender);
    }

    public static void main(String[] args) {
        obj = new Book(email, (Author)null, (double)gender, gender);
        boolean inUse = true;
        Scanner scan = new Scanner(System.in);

        label32:
        while(inUse) {
            boolean num = false;
            System.out.println("1.Add books");
            System.out.println("2.List books");
            System.out.println("3.Buy books");
            System.out.println("4.Add stock");
            System.out.println("5.Save to file");
            System.out.print("6.To exit the program");

            while(true) {
                String input = scan.next();

                int numb;
                try {
                    numb = Integer.parseInt(input);
                } catch (NumberFormatException var6) {
                    System.out.println("Incorrect input please enter an integer.");
                    continue;
                }

                switch(numb) {
                    case 1:
                        Addbooks();
                        continue label32;
                    case 2:
                        Listbooks();
                        continue label32;
                    case 3:
                        Buybooks();
                        continue label32;
                    case 4:
                        Addstock();
                        continue label32;
                    case 5:
                        Savetofile();
                        continue label32;
                    case 6:
                        Toexittheprogram();
                    default:
                        continue label32;
                }
            }
        }

    }

    public static void Addbooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add the book name:");
        names.add(scanner.next());
    }

    public static void Listbooks() {
        System.out.println(name);
        System.out.println(names);
    }

    private static void Toexittheprogram() {
        System.exit(0);
    }

    private static void Savetofile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter the name of your file");
        String V = scan.next();
        Path path = Paths.get(V);
    }

    private static void Addstock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add the stock number:");
        stock.add(scanner.next());
    }

    private static void Buybooks() {
    }
}
