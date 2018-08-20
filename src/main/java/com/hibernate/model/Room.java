package com.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rooms")
@Data
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;


    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL
    )
    private List<Message> messages = new ArrayList<>();


    @ManyToMany(mappedBy = "")
    private List<User> users = new ArrayList<>();
}
