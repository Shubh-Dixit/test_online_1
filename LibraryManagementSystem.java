import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void display() {
        System.out.println(bookId + " " + title + " " + author + " " +
                (isAvailable ? "Available" : "Issued"));
    }
}

class Member {
    private int memberId;
    private String name;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
}

class StudentMember extends Member {
    public StudentMember(int memberId, String name) {
        super(memberId, name);
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<Integer, List<Book>> issuedBooks = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book b : books) {
            b.display();
        }
    }

    public void issueBook(int memberId, int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId && b.isAvailable()) {
                b.setAvailable(false);

                issuedBooks.putIfAbsent(memberId, new ArrayList<>());
                issuedBooks.get(memberId).add(b);

                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available!");
    }

    public void returnBook(int memberId, int bookId) {
        List<Book> list = issuedBooks.get(memberId);
        if (list != null) {
            for (Book b : list) {
                if (b.getBookId() == bookId) {
                    b.setAvailable(true);
                    list.remove(b);
                    System.out.println("Book returned successfully!");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    public void search(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.display();
            }
        }
    }

    public void searchByAuthor(String author) {
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                b.display();
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();

        lib.addBook(new Book(1, "Java Basics", "James Gosling"));
        lib.addBook(new Book(2, "DSA", "Mark Allen"));
        lib.addBook(new Book(3, "OOP Concepts", "Bjarne Stroustrup"));

        System.out.println("All Books:");
        lib.displayBooks();

        Member m1 = new StudentMember(101, "Shubh");

        lib.issueBook(m1.getMemberId(), 1);

        System.out.println("\nAfter Issue:");
        lib.displayBooks();

        lib.returnBook(m1.getMemberId(), 1);

        System.out.println("\nSearch by Title:");
        lib.search("DSA");

        System.out.println("\nSearch by Author:");
        lib.searchByAuthor("James Gosling");
    }
}
