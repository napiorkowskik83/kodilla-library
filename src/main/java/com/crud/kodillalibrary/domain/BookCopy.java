package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
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

}
