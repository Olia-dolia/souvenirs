package liakholga.app.souvenirs;

import liakholga.app.producers.PrivatBank;
import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Souvenir;
import java.util.Date;

public class PrivatBankKeychain implements Keychain {

    private String name;
    private PrivatBank producer;
    private Date date;
    private double price;

    public PrivatBankKeychain(String name, PrivatBank producer, Date date, double price) {
        this.name = name;
        this.producer = producer;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Souvenir findByNameAndProducer(String name, Producer producer) {
        if(this.name.equals(name) && this.producer.equals(producer)){
            return this;
        }
        return null;
    }

    @Override
    public PrivatBank getProducer() {
        return producer;
    }

    public void setProducer(PrivatBank producer) {
        this.producer = producer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Souvenir setSouvenir(String name, Producer producer, Date date, double price) {
        return new PrivatBankKeychain(name, (PrivatBank) producer, date, price);
    }

    @Override
    public String getSouvenir() {
        return "PrivatBankKeychain{" +
                "name='" + name + '\'' +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public String toString() {
        return "PrivatBankKeychain{" +
                "name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
