package com.makers.makersbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity - instances of this class map to database records
@Entity
// Lombok creates no argument constructor, and getters and setters for all fields
@Getter @Setter @NoArgsConstructor
// @Table - those records can be found in the spaces table
@Table(name = "SPACES")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private String rules;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // the full-argument constructor
    public Space(String name, String description, int price, String rules, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rules = rules;
        this.user = user;
    }
}
