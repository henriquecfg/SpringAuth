package com.henrique.modelsDTO;

import com.henrique.models.Car;
import lombok.Data;
import java.io.Serializable;

@Data
public class CarDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String color;
    private String model;
    private float price;

    public CarDTO(Car obj) {
        this.id = obj.getId();
        this.color = obj.getColor();
        this.model = obj.getModel();
        this.price = obj.getPrice();
    }
}
