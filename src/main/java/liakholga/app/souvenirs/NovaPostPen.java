package liakholga.app.souvenirs;

import liakholga.app.producers.NovaPost;
import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;
import java.util.List;

public class NovaPostPen implements Pen {

    private String name;
    private NovaPost producer;
    private Date date;
    private double price;

    public NovaPostPen() {
    }

    public NovaPostPen(String name, NovaPost producer, Date date, double price) {
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

    public NovaPost getProducer() {
        return producer;
    }

    public void setProducer(NovaPost producer) {
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
    public Souvenir addSouvenir(String name, Producer producer, Date date, double price) {
        return new NovaPostPen(name, (NovaPost) producer, date, price);
    }

    @Override
    public String toString() {
        return "NovaPostPen{" +
                "name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
