package liakholga.app.servise;

import liakholga.app.souvenirInterface.Souvenir;

import java.util.List;

public class Service {

    //method to read from file

    public void getAllSouvenirs(List<Souvenir> souvenirs) {
        souvenirs.forEach(System.out::println);
    }

    Souvenir updateSouvenir(int index) {
        return null;
    }

    void removeSouvenir(int id) {
    }

    //method to write in file
}
