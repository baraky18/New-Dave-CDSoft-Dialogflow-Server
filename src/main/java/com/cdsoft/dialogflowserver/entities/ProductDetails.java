package com.cdsoft.dialogflowserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;

//@Entity
//@Table(name = "ps_product_lang")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@PrimaryKeyJoinColumn(name = "id_product")
public class ProductDetails extends Product{

//    @Column(name = "name")
    private String productName;

//    @Column(name = "description")
    private String productDescription;

//    @Column(name = "description_short")
    private String productShortDescription;
}
