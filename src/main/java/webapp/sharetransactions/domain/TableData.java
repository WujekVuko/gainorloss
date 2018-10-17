package webapp.sharetransactions.domain;

import basic.dbconnect.Model.HistoricValue;

import java.time.LocalDate;

public class TableData extends HistoricValue {
    private float numberOfShares;
    private float totalValue;


    public TableData(String name,float numberOfShares, float price, float totalValue, LocalDate date) {
        super(name, price, date);
        this.numberOfShares = numberOfShares;
        this.totalValue = totalValue;
    }

    public float getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(float numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
