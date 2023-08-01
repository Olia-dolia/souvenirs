package liakholga.app.producers;

import liakholga.app.Factory.SouvenirsFactory;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirs.UkrPostCup;

import java.util.Date;

public class UkrPost implements SouvenirsFactory, Producer {

    private String name;
    private String country;

    public UkrPost(String country) {
        this.name = "Ukr Post";
        this.country = country;
    }

    @Override
    public Cup createCup(String nameCup, Producer producer, Date date, double price) {
        return new UkrPostCup(nameCup, (UkrPost) producer, date, price);
    }

    @Override
    public Keychain createKeychain(String name, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Pen createPen(String name, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Postcard createPostCard(String name, Producer producer, Date date, double price) {
        return null;
    }

    public void setName() {
        this.name = "Ukr Post";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
