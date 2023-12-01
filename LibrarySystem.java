package org.example;

import java.util.ArrayList;
import java.util.List;

// Interface for items that can be borrowed
interface Borrowable {
    boolean isAvailable();

    void borrow();

    void returnItem();
}

// Base class for all library resources
abstract class LibraryResource implements Borrowable {
    protected final String title;
    protected final String author;
    protected boolean availability;

    public LibraryResource(String title, String author) {
        this.title = title;
        this.author = author;
        this.availability = true;
    }

    @Override
    public boolean isAvailable() {
        return availability;
    }

    @Override
    public void borrow() {
        if (availability) {
            availability = false;
            System.out.println("Item " + title + " borrowed successfully.");
        } else {
            System.out.println("Item " + title + " is not available for borrowing.");
        }
    }

    @Override
    public void returnItem() {
        availability = true;
        System.out.println("Item " + title + " returned successfully.");
    }
}

// Book class
class Book extends LibraryResource {

    public Book(String title, String author) {
        super(title, author);
    }
}

// Theses/Dissertation class
class ThesesDissertation extends LibraryResource {

    public ThesesDissertation(String title, String author) {
        super(title, author);
    }
}

// CD/DVD class
class CDDVD extends LibraryResource {

    public CDDVD(String title, String author) {
        super(title, author);
    }
}

// Author class
class Author {
    private final List<LibraryResource> authoredBooks;

    public Author() {
        this.authoredBooks = new ArrayList<>();
    }

    public void addAuthoredBook(LibraryResource book) {
        authoredBooks.add(book);
    }
}

// LibraryUser class
class LibraryUser {
    private final List<LibraryResource> borrowedAssets;

    public LibraryUser(int ID) {
        this.borrowedAssets = new ArrayList<>();
    }

    public void borrowItem(Borrowable item) {
        if (item.isAvailable()) {
            item.borrow();
            borrowedAssets.add((LibraryResource) item);
        } else {
            System.out.println("Item is not available for borrowing.");
        }
    }

    public void returnItem(Borrowable item) {
        item.returnItem();
        borrowedAssets.remove(item);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        // Sample usage of the classes
        Book book1 = new Book("Java Programming", "John Doe");
        ThesesDissertation thesis1 = new ThesesDissertation("Data Science", "Jane Smith");
        CDDVD dvd1 = new CDDVD("Movie Title", "Director Name");

        Author author1 = new Author();
        author1.addAuthoredBook(book1);

        LibraryUser user1 = new LibraryUser(1001);
        user1.borrowItem(book1);
        user1.borrowItem(thesis1);
        user1.borrowItem(dvd1);

        user1.returnItem(book1);
    }
}