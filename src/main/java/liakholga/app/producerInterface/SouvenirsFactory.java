package liakholga.app.producerInterface;

import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.*;

import java.util.Date;

public interface SouvenirsFactory {

    Cup createCup(String name, Producer producer, Date date, double price);

    Keychain createKeychain();

    Pen createPen();

    Postcard createPostCard();
}
