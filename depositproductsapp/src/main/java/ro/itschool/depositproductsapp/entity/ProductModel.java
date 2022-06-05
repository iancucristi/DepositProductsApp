package ro.itschool.depositproductsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductModel {

    @Id
    private int seriesNumber;
    private String name;
    private String producer;
    private String description;
    private int quantity;

    @ManyToOne
    private DepositModel deposit;

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DepositModel getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositModel deposit) {
        this.deposit = deposit;
    }
}
