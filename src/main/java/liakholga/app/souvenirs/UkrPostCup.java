package liakholga.app.souvenirs;

import liakholga.app.producers.Producer;
import liakholga.app.producers.UkrPost;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;
import java.util.List;

public class UkrPostCup implements Cup {

    private String name;
    private UkrPost producer;
    private Date date;
    private double price;

    public UkrPostCup() {
    }

    public UkrPostCup(String name, UkrPost producer, Date date, double price) {
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

    public UkrPost getProducer() {
        return producer;
    }

    public void setProducer(UkrPost producer) {
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
    public String toString() {
        return "UkrPostCup{" +
                "name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public Souvenir addSouvenir(String name, Producer producer, Date date, double price) {
        return new UkrPostCup(name, (UkrPost) producer, date, price);
    }

    @Override
    public List<Souvenir> getAllSouvenirs(List<Souvenir> souvenirs) {
        return null;
    }

    @Override
    public Souvenir updateSouvenir(int index) {
        return null;
    }

    @Override
    public void removeSouvenir(int id) {

    }
}
