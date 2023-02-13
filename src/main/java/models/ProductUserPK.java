package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductUserPK implements Serializable {
    private User user;
    private Product product;
}
