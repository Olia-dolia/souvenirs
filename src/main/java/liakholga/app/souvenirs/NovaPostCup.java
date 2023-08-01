package liakholga.app.souvenirs;

import liakholga.app.producers.NovaPost;
import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;

public class NovaPostCup implements Cup {

    private String name;
    private NovaPost producer;
    private Date date;
    private double price;


    public NovaPostCup(String name, NovaPost producer, Date date, double price) {
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
    public NovaPost getProducer() {
        return producer;
    }

    @Override
    public Souvenir findByNameAndProducer(String name, Producer producer) {
        if(this.name.equals(name) && this.producer.equals(producer)){
            return this;
        }
        return null;
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
    public Souvenir setSouvenir(String name, Producer producer, Date date, double price) {
        return new NovaPostCup(name, (NovaPost) producer, date, price);
    }

    @Override
    public String getSouvenir() {
        return "NovaPostCup{" +
                "name='" + name + '\'' +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public String toString() {
        return "NovaPostCup{" +
                "name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
