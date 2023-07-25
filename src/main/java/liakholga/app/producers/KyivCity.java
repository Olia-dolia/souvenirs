package liakholga.app.producers;

import liakholga.app.producerInterface.SouvenirsFactory;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirs.KyivCityPostcard;

import java.util.Date;

public class KyivCity implements SouvenirsFactory, Producer {

    private String name;
    private String country;

    public KyivCity(String country) {
        this.name = "Kyiv City";
        this.country = country;
    }

    @Override
    public Cup createCup(String nameCup, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Keychain createKeychain() {
        return null;
    }

    @Override
    public Pen createPen() {
        return null;
    }

    @Override
    public Postcard createPostCard() {
        return new KyivCityPostcard();
    }

    public void setName() {
        this.name = "Kyiv City";
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