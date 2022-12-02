package com.cdsoft.dialogflowserver.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "session")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "session_uuid")
    private UUID sessionUuid;

    @Column(name = "session_name")
    private String sessionName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Customer customer;

    @OneToMany(mappedBy = "session")
    private List<Message> messages;
}
