package liakholga.app.souvenirs;

import liakholga.app.producers.KyivCity;
import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;

public class KyivCityPostcard implements Postcard {

    private String name;
    private KyivCity producer;
    private Date date;
    private double price;

    public KyivCityPostcard(String name, KyivCity producer, Date date, double price) {
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
    public KyivCity getProducer() {
        return producer;
    }

    @Override
    public Souvenir findByNameAndProducer(String name, Producer producer) {
        if(this.name.equals(name) && this.producer.equals(producer)){
            return this;
        }
        return null;
    }

    public void setProducer(KyivCity producer) {
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
        return new KyivCityPostcard(name, (KyivCity) producer, date, price);
    }

    @Override
    public String getSouvenir() {
        return "KyivCityPostcard{" +
                "name='" + name + '\'' +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public String toString() {
        return "KyivCityPostcard{" +
                "name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", country= " + producer.getCountry() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
