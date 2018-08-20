package com.hibernate.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="messages")
@Data
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
