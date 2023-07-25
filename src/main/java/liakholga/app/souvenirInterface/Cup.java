package liakholga.app.souvenirInterface;

import liakholga.app.producers.Producer;

import java.util.Date;
import java.util.List;

public interface Cup {
    Cup addCup(String name, Producer producer, Date date, double price);
    List<Cup> getAllCup(List<Cup> cups);
    Cup updateCup(int index);
    void removeCup(int id);
}
