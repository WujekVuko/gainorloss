package webapp.sharetransactions.domain;

import basic.dbconnect.Model.Transaction;

public class TableData extends Transaction {
    private float sellPrice;
    private float initialValue;
    private float finalValue;
    private float profitLoss;


    public TableData(String name, int numberOfShares, float price, float sellPrice, String buyDate, String sellDate, Long transactionID) {
        super(name, numberOfShares, price, buyDate, sellDate, transactionID);
        this.sellPrice = sellPrice;
        this.initialValue = price*numberOfShares;
        this.finalValue = sellPrice*numberOfShares;
        this.profitLoss = (this.finalValue-this.initialValue)/this.initialValue*100;

    }

    @Override
    public float getInitialValue() {
        return initialValue;
    }

    @Override
    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public float getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(float finalValue) {
        this.finalValue = finalValue;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(float profitLoss) {
        this.profitLoss = profitLoss;
    }
}
