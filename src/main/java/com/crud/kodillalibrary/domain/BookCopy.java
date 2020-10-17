package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NamedNativeQuery(
        name = "BookCopy.getAllAvailableBookCopies",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE STATUS = 'available'",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAllBookCopiesOfBook",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE BOOK_ID = :BOOK_ID",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAvailableBookCopiesOfBook",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE (STATUS = 'available' AND BOOK_ID = :BOOK_ID)",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAllBookCopiesOfTitle",
        query = "SELECT * FROM BOOK_COPIES BC " +
                "JOIN BOOKS B ON B.ID = BC.BOOK_ID " +
                "WHERE B.TITLE = :TITLE",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAvailableBookCopiesOfTitle",
        query = "SELECT * FROM BOOK_COPIES BC " +
                "JOIN BOOKS B ON B.ID = BC.BOOK_ID " +
                "WHERE (BC.STATUS = 'available' AND B.TITLE = :TITLE)",
        resultClass = BookCopy.class
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "STATUS")
    private String status = "available";

    public BookCopy(Book book) {
        this.book = book;
    }
}
