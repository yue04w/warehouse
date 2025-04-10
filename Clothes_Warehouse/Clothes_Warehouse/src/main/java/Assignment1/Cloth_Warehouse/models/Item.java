package Assignment1.Cloth_Warehouse.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING) 
    private Brand brand;

    private int yearOfCreation;
    private double price;
}