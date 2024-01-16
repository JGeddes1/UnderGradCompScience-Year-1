public class Book {
    private String title = "";
    private Author author = new Author();
    private double price = 0;
    private int inStock = 0;

    public Book(String title, Author author, double price, int inStock) {
        System.out.println("The title of book is: " + this.title);
        System.out.println("The price of book is: " + this.price);
        System.out.println("how many in stock? " + this.inStock);
    }

    public static void main(String[] args) {

    }
}
