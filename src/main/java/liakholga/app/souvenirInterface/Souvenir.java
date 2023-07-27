package liakholga.app.souvenirInterface;

import liakholga.app.producers.Producer;

import java.util.Date;

public interface Souvenir {
    Souvenir setSouvenir(String name, Producer producer, Date date, double price);

    Producer getProducer();

    Souvenir findByNameAndProducer(String name, Producer producer);

    boolean findProducerByPrice(double price);
}
