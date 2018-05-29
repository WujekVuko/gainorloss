package webapp.sharetransactions.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Transaction {
    @NotBlank
    private String name;
    @NotNull
    private float price;
    @NotNull
    private int numberOfShares;
    @NotBlank
    @Pattern(regexp="[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
    private String buyDate;
    @NotBlank
    @Pattern(regexp="[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
    private String sellDate;


    public Transaction(){}


    public Transaction (String name, float price, int numberOfShares, String buyDate, String sellDate){
        this.name = name;
        this.price = price;
        this.numberOfShares = numberOfShares;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", numberOfShares=" + numberOfShares +
                ", buyDate='" + buyDate + '\'' +
                ", sellDate='" + sellDate + '\'' +
                '}';
    }
}
