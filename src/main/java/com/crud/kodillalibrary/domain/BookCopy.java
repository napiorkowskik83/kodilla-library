package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "BookCopy.getAvailableBookCopiesOfBook",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE (STATUS = 'available' AND BOOK_ID = :BOOK_ID)",
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
    //@Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "STATUS")
    private String status = "available";

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "bookCopy",
            //cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Borrowing> borrowingList = new ArrayList<>();

    public BookCopy(Book book) {
        this.book = book;
    }

    public BookCopy(Long id,Book book, String status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }
}
