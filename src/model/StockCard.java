package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCard {

    private String stockCode;
    private String stockName;
    private int stockType;
    private String unit;
    private String barcode;
    private double vatType;
    private String description;
    private Date creationTime;
    private VatTypeCard vatTypeCard;

}

