package webapp.sharetransactions.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Transactional
@Validated
public class TransactionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "not blank")
    private String name;
    private String linkVariable;
    @NotNull
    @Min(0)
    private float price;
    @NotNull
    @Min(0)
    private int numberOfShares;
    @NotBlank
    @Pattern(regexp="[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
    private String buyDate;
    @NotBlank
    @Pattern(regexp="[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
    private String sellDate;
    @NotNull
    @Min(0)
    private float initialValue;

    public TransactionDTO(){}

    public TransactionDTO(String name, String linkVariable, float price, int numberOfShares, String buyDate, String sellDate) {
        this.name = name;
        this.linkVariable = linkVariable;
        this.price = price;
        this.numberOfShares = numberOfShares;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.initialValue = price*numberOfShares;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkVariable() {
        return linkVariable;
    }

    public void setLinkVariable(String linkVariable) {
        this.linkVariable = linkVariable;
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

    public float getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }
}


