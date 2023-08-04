package liakholga.app;

import liakholga.app.Factory.SouvenirsFactory;
import liakholga.app.producers.NovaPost;
import liakholga.app.producers.PrivatBank;
import liakholga.app.service.Service;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }

    private void run() throws Exception {
        Service service = Service.getInstance();
        //Get data from files
        List<Souvenir> souvenirs = service.parseSouvenirInputList(service.readFromFiles());

        //Print of all products
        service.getAllSouvenirs(souvenirs);
        //Print all producers
        service.terminalOutput(service.getAllProducers(souvenirs));

        //Add souvenir
        SouvenirsFactory factory = new PrivatBank();
        Souvenir s = factory.createKeychain("Fashion Keychain", new PrivatBank("UA"), new Date("08/27/2014"), 95.5);
        souvenirs.add(s);

        //Update certain souvenir
        souvenirs.set(service.getIndex(souvenirs, "Cup1", new NovaPost("UK")),
                service.updateSouvenir(souvenirs,"Cup1", new NovaPost("UK")));
        service.getAllSouvenirs(souvenirs);

        //Print all souvenirs by certain country
        service.getSouvenirsByCountry(souvenirs, "USA");

        //Print all producers info where price on souvenirs < certain_price
        service.getProducerByPrice(souvenirs,170.0);

        //Print all producers info and their souvenirs
        service.printMap(service.getProducerAndItsSouvenirs(souvenirs),1);

        //Print producers by certain product and year
        service.getProducerByYearAndSouvenir(souvenirs, "cup",2022);

        //Print all souvenirs produced by year
        service.printMap(service.getSouvenirsByYear(souvenirs), 2);

        //Remove all souvenirs by certain producer
        souvenirs.subList(0, service.removeSouvenirsByProducer(souvenirs, "Nova Post").size());

        //Write in CSV file
        boolean write = service.writeInFile(souvenirs);
        System.out.println(write);
    }
}
