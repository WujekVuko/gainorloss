package webapp.sharetransactions.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    @NotBlank
    private String name;
    private String webShareName;
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


    public Transaction (String name,int numberOfShares, float price, String buyDate, String sellDate){
        this.name = name;
        this.webShareName = getShareName(name);
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

    public String getWebShareName() {
        return webShareName;
    }

    public void setWebShareName(String webShareName) {
        this.webShareName = webShareName;
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

    private String getShareName(String name) {
        String fileName = "src/main/resources/static/txt/names.txt";
        Map<String,String> linkShareNames = new HashMap<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String row;
            while ((row = br.readLine()) != null){
                String holder[] = row.split(";");
                linkShareNames.put(holder[0],holder[1]);
            }
        }
        catch (Exception e){
            System.err.println("No file to import employees list");
            e.printStackTrace();
        }
        return linkShareNames.get(name);
    }
}
