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
@Entity(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @NotNull
    @Column(name = "borrow_date" )
    private Date borrowDate = new Date();

    @Column(name = "return_date" )
    private Date returnDate;

    public Borrowing(BookCopy bookCopy , Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
    }
}
