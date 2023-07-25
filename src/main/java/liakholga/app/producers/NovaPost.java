package liakholga.app.producers;

import liakholga.app.producerInterface.SouvenirsFactory;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirs.NovaPostCup;
import liakholga.app.souvenirs.NovaPostPen;

import java.util.Date;

public class NovaPost implements SouvenirsFactory, Producer {

    private String name;
    private String country;

    public NovaPost(String country) {
        this.name = "Nova Post";
        this.country = country;
    }

    @Override
    public Cup createCup(String nameCup, Producer producer, Date date, double price) {
        return new NovaPostCup(nameCup, (NovaPost) producer, date, price);
    }

    @Override
    public Keychain createKeychain() {
        return null;
    }

    @Override
    public Pen createPen() {
        return new NovaPostPen();
    }

    @Override
    public Postcard createPostCard() {
        return null;
    }

    public void setName() {
        this.name = "Nova Post";
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCountry() {
        return this.country;
    }
}
