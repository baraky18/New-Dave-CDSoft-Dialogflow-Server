package com.cdsoft.dialogflowserver.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "text")
    private String text;

    @Column(name = "sent_date")
    private Date sentDate;

    @ManyToOne
    @JoinColumn(name = "session")
    @ToString.Exclude
    private Session session;
}
