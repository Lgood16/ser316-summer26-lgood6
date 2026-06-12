import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Sample White-Box tests for the Checkout system.
 * This class demonstrates how to write white-box tests using:
 * - Control Flow Graph (CFG) analysis
 * - Statement coverage
 * - Branch coverage
 * - Path coverage
 *
 * White-box testing focuses on testing the IMPLEMENTATION by
 * examining the code structure and ensuring all paths are tested.
 */
public class CheckoutWhiteBoxSample {

    private Checkout checkout;

    @BeforeEach
    public void setUp() {
        checkout = new Checkout();
    }

    @Test
    @DisplayName("WB Test: checkoutBook - unavailable book")
    public void test_CheckoutBook_unavailableBook() {
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 0);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); 
        checkout.registerPatron(patron); 

        double result = checkout.checkoutBook(book, patron);

        assertEquals(2.0, result, 0.01,
                "Expected unsuccessful checkout (2.0)");

    }

    @Test
    @DisplayName("WB Test: checkoutBook - available book")
    public void test_CheckoutBook_availableBook() {
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 3);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); 
        checkout.registerPatron(patron); 

        double result = checkout.checkoutBook(book, patron);

        assertEquals(0.0, result, 0.01,
                "Expected successful checkout (1.0)");

    }

    @Test
    @DisplayName("WB Test: checkoutBook - null book")
    public void test_checkoutBook_nullBook() {
        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.registerPatron(patron); 

        double result = checkout.checkoutBook(null, patron);

        assertEquals(2.1, result, 0.01,
                "Expected unsuccessful checkout (2.1)");
    }

    // Node Coverage Sequence: 1 and Edge Coverage Sequence: 1
    @Test
    @DisplayName("WB Test: countBooksByType - null type branch")
    public void testCountBooksByType_NullType() {
        // Branch: type == null → TRUE
        int result = checkout.countBooksByType(null, false);
        assertEquals(0, result, "Should return 0 for null type");
    }

    // Node Coverage Sequence: 2
    @Test
    @DisplayName("WB Test: countBooksByType - onlyAvailable false branch")
    public void testCountBooksByType_onlyAvailable_false() {
        // Branch: onlyAvailable → FALSE
        // Setup: Create book and add it to checkout
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);
        checkout.addBook(book);
        
        int result = checkout.countBooksByType(Book.BookType.FICTION, false);
        assertEquals(1, result, "Should return 1 for onlyAvailable = false");
    }

    // Edge Coverage Sequence: 2
    @Test
    @DisplayName("WB Test: countBooksByType - empty list branch")
    public void testCountBooksByType_emptyList() {
        // Branch: bookList -> is empty
        int result = checkout.countBooksByType(Book.BookType.FICTION, true);
        assertEquals(0, result, "Should return 0 for empty list");
    }

    @Test
    @DisplayName("WB Test: checkoutBook and return book")
    public void test_CheckoutBook_returnBook() {
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 3);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); 
        checkout.registerPatron(patron); 

        double result = checkout.returnBook("978-0-123456-78-9", patron);
        assertEquals(0.0, result, 0.01,
                "Expected successful return (0.0)");

    }

    @Test
    @DisplayName("WB Test: validatePatronEligibility - null patron")
    public void test_validatePatronEligibility_null() {
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 3);

        checkout.addBook(book); 

        double result = checkout.checkoutBook(book, null);

        assertEquals(3.1, result, 0.01,
                "Expected unsuccessful checkout (3.1)");
    }

    @Test
    @DisplayName("WB Test: isValidISBN - valid ISBN")
    public void test_isValidISBN_valid() {
        boolean result = checkout.isValidISBN("978-0-123456-78-9");

        assertTrue(result, "Expected valid ISBN");
    }

    @Test
    @DisplayName("WB Test: isPatronType - valid and invalid patron types")
    public void test_isPatronType() {
        boolean result = checkout.isPatronType("STUDENT", Patron.PatronType.STUDENT);
        assertTrue(result, "Expected valid patron type");

        result = checkout.isPatronType(null, Patron.PatronType.STUDENT);
        assertFalse(result, "Expected invalid patron type");
    }

    @Test
    @DisplayName("WB Test: returnBook - book overdue")
    public void test_returnBook_overdue() {
        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.TEXTBOOK, 3);

        checkout.addBook(book);
        patron.addCheckedOutBook("978-0-123456-78-9", LocalDate.now().minusDays(15));

        double result = checkout.returnBook("978-0-123456-78-9", patron);

        assertEquals(12.5, result, 0.01,
                "Expected fine for 15 days overdue textbook");
    }

}
