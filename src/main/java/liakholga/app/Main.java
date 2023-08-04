package liakholga.app;

import liakholga.app.service.Service;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Service service = new Service();
        List<Souvenir> souvenirs = service.parseSouvenirInputList(service.readFromFiles());
        //List<Souvenir> s = new ArrayList<>();
        // add

       /* service.getAllSouvenirs(souvenirs);
        service.terminalOutput(service.getAllProducers(souvenirs));
        *//*souvenirs.set(service.getIndex(souvenirs, "NPCup1", new NovaPost("UK")),
                service.updateSouvenir(souvenirs,"NPCup1", new NovaPost("UK")));*//*
        service.getAllSouvenirs(souvenirs);
        souvenirs.subList(0, service.removeSouvenirByProducer(souvenirs, "Nova Post").size());
        service.getAllSouvenirs(souvenirs);
        service.getSouvenirsByCountry(souvenirs, "USA");
        service.getProducerByPrice(souvenirs,200.0);
        service.printMap(service.getProducerAndItsSouvenirs(souvenirs),1);
        service.getProducerByYear(souvenirs, 2022);
        service.printMap(service.getSouvenirsByYear(souvenirs), 2);*/
        boolean write = service.writeInFile(souvenirs);
        System.out.println(write);

       /* Main main = new Main();
        main.run();*/
    }

    /*private void run() throws Exception {
        //Service service = Service.getInstance();
        Service service = new Service();

        List<String[]> str = service.readFromFiles();
        List<Souvenir> souvenirs = service.parseSouvenirInputList(str);

        //Get Input Data
        ;
        service.printMap(service.getProducerAndItsSouvenirs(souvenirs), 2);
        *//*   souvenirs.forEach(System.out::println);*//*


    }*/
}
