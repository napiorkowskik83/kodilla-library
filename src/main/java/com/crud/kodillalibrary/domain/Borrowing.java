package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedNativeQuery(
        name = "Borrowing.getAllBorrowingsOfBookCopy",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE BOOK_COPY_ID = :BOOK_COPY_ID",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getActiveBorrowingsOfBookCopy",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE (RETURN_DATE IS NULL AND BOOK_COPY_ID = :BOOK_COPY_ID)",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getAllBorrowingsOfReader",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE READER_ID = :READER_ID",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getActiveBorrowingsOfReader",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE (RETURN_DATE IS NULL AND READER_ID = :READER_ID)",
        resultClass = Borrowing.class
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BORROWINGS")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @NotNull
    @Column(name = "BORROW_DATE" )
    private Date borrowDate = new Date();

    @Column(name = "RETURN_DATE" )
    private Date returnDate;

    public Borrowing(BookCopy bookCopy , Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
    }

    public Borrowing(Long id, BookCopy bookCopy , Reader reader) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.reader = reader;
    }
}
