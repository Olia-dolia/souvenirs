package liakholga.app.producers;

import liakholga.app.Factory.SouvenirsFactory;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirs.PrivatBankKeychain;

import java.util.Date;

public class PrivatBank implements SouvenirsFactory, Producer {

    private String name;
    private String country;

    public PrivatBank(String country) {
        this.name = "Privat Bank";
        this.country = country;
    }

    @Override
    public Cup createCup(String nameCup, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Keychain createKeychain(String name, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Pen createPen(String nameCup, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Postcard createPostCard(String name, Producer producer, Date date, double price) {
        return null;
    }

    public void setName() {
        this.name = "Privat Bank";
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
