package liakholga.app.souvenirInterface;

import liakholga.app.producers.Producer;

import java.util.Date;
import java.util.List;

public interface Souvenir {
    Souvenir addSouvenir(String name, Producer producer, Date date, double price);
}
