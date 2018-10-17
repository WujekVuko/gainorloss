package webapp.sharetransactions.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class ChartData {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String variableName;
    private int numberOfShares;
    private float[] valuesForDays;
    private String[] dates;

    public ChartData(String name, String variableName,int numberOfShares, float[] valuesForDays,  String[] dates) {
        this.name = name;
        this.variableName = variableName;
        this.valuesForDays = valuesForDays;
        this.numberOfShares = numberOfShares;
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public float[] getValuesForDays() {
        return valuesForDays;
    }

    public void setValuesForDays(float[] valuesForDays) {
        this.valuesForDays = valuesForDays;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }
}
