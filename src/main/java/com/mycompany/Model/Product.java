/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LebyanaWT
 */
@Entity
@Table(name="products")
public class Product implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="prod_id")
    private Integer product_id;
    @Column(name="product_image")
    private String image;
    @Column(name="prod_Name")
    private String name;
    @Column(name="prod_description")
    private String description;
   
    @Column(name="Unity_Price")
    private float price;

    public Product() {
    }

    public Product(Integer product_id, String image, String name, String description, float price) {
        this.product_id = product_id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    
    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdescription() {
        return description;
    }

    public void setProd_descrip(String description) {
        this.description = description;
    }
    public float getprice() {
        return price;
    }

    public void setUnit_price(float price) {
        this.price = price;
    }
    
    
    
}
