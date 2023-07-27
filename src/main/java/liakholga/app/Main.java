package liakholga.app;

import liakholga.app.producers.KyivCity;
import liakholga.app.producers.NovaPost;
import liakholga.app.producers.PrivatBank;
import liakholga.app.producers.UkrPost;
import liakholga.app.servise.Service;
import liakholga.app.souvenirInterface.Souvenir;
import liakholga.app.souvenirs.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        List<Souvenir> souvenirs = new ArrayList<>();
        souvenirs.add(new NovaPostCup("NPCup1", new NovaPost("UK"), new Date("02/11/2022"), 200.0));
        souvenirs.add(new NovaPostCup("NPCup2", new NovaPost("USA"), new Date("25/09/2021"), 150.0));
        souvenirs.add(new UkrPostCup("UPCup1", new UkrPost("USA"), new Date("16/10/2022"), 150.0));
        souvenirs.add(new PrivatBankKeychain("PBKeychain1", new PrivatBank("UA"), new Date("22/05/2020"), 50.0));
        souvenirs.add(new KyivCityPostcard("KCPostCard1", new KyivCity("UA"), new Date("07/02/2019"), 25.0));

        /*Souvenir s = (name, producer, date, price) -> new NovaPostCup("j", new NovaPost("CH"), new Date("09/10/2020"), 90.0);
        souvenirs.add(s);// add
        service.getAllSouvenirs(souvenirs);
        service.terminalOutput(service.getAllProducers(souvenirs));
        souvenirs.set(service.getIndex(souvenirs, "NPCup1", new NovaPost("UK")),
                service.updateSouvenir(souvenirs,"NPCup1", new NovaPost("UK")));
        service.getAllSouvenirs(souvenirs);
        souvenirs.subList(0, service.removeSouvenirByProducer(souvenirs, "Nova Post").size());
        service.getAllSouvenirs(souvenirs);
        service.getSouvenirsByCountry(souvenirs, "USA");
        service.getProducerByPrice(souvenirs,200.0);*/
    }
}
