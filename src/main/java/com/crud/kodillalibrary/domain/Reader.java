package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "created")
    private Date created = new Date();

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "reader",
            //cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Borrowing> borrowings = new ArrayList<>();

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
