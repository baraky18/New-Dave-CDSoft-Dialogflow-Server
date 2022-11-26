package com.cdsoft.dialogflowserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "session")
    private Session session;
}
