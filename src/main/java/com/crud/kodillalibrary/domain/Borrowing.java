package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BORROWINGS")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @ManyToOne(cascade = CascadeType.ALL)
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
