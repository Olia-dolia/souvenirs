package liakholga.app.souvenirInterface;

import liakholga.app.producers.Producer;

import java.util.Date;

public interface Souvenir {
    Souvenir setSouvenir(String name, Producer producer, Date date, double price);

    String getSouvenir();

    String getName();

    Producer getProducer();

    Date getDate();

    double getPrice();
}
