package liakholga.app.producers;

import liakholga.app.producerInterface.SouvenirsFactory;
import liakholga.app.souvenirInterface.Cup;
import liakholga.app.souvenirInterface.Keychain;
import liakholga.app.souvenirInterface.Pen;
import liakholga.app.souvenirInterface.Postcard;
import liakholga.app.souvenirs.PrivatBankKeychain;

import java.util.Date;

public class PrivatBank implements SouvenirsFactory {

    @Override
    public Cup createCup(String nameCup, Producer producer, Date date, double price) {
        return null;
    }

    @Override
    public Keychain createKeychain() {
        return new PrivatBankKeychain();
    }

    @Override
    public Pen createPen() {
        return null;
    }

    @Override
    public Postcard createPostCard() {
        return null;
    }
}
