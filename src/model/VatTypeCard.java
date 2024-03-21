package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VatTypeCard {
    private int id;
    private String code;
    private String name;
    private double rate;
}