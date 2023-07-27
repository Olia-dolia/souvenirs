package liakholga.app.Factory;

import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.*;

import java.util.Date;

public interface SouvenirsFactory {

    Cup createCup(String name, Producer producer, Date date, double price);

    Keychain createKeychain(String name, Producer producer, Date date, double price);

    Pen createPen(String name, Producer producer, Date date, double price);

    Postcard createPostCard(String name, Producer producer, Date date, double price);
}
