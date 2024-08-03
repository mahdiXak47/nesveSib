package NesveSib.Installment.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Product {

    private final String productName;
    private final double price;
    private final String productCategory;
    private final String productModel;
    private final String productColor;


}
