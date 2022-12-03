package com.cdsoft.dialogflowserver.entities;

import com.cdsoft.dialogflowserver.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private Gender gender;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer")
    private Session session;
}

