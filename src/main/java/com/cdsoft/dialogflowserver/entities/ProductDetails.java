package com.cdsoft.dialogflowserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ps_product_lang")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long productId;

    @Column(name = "name")
    private String productName;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "description_short")
    private String productShortDescription;

    @Column(name = "available_now")
    private String isInStock;
}
