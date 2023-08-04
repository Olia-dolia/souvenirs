package liakholga.app.service;

import liakholga.app.Factory.SouvenirsFactory;
import liakholga.app.producers.KyivCity;
import liakholga.app.producers.NovaPost;
import liakholga.app.producers.PrivatBank;
import liakholga.app.producers.UkrPost;
import liakholga.app.souvenirInterface.Souvenir;
import liakholga.app.souvenirs.KyivCityPostcard;
import liakholga.app.souvenirs.NovaPostCup;
import liakholga.app.souvenirs.PrivatBankKeychain;
import liakholga.app.souvenirs.UkrPostCup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceTest {
    Service service;
    List<Souvenir> souvenirs = new ArrayList<>();

    @BeforeTest
    public void setUp(){
        service = Service.getInstance();
        souvenirs.add(new PrivatBankKeychain("Keychain 2", new PrivatBank("UK"), new Date("05/20/2019"),20.0));
        souvenirs.add(new KyivCityPostcard("Postcard 3", new KyivCity("UA"), new Date("05/20/2022"),170.7));
        souvenirs.add(new NovaPostCup("Cup 3", new NovaPost("UA"), new Date("05/20/2021"),150.0));
        souvenirs.add(new UkrPostCup("Cup 3", new UkrPost("UK"), new Date("05/20/2019"),125.7));
    }

    @Test
    public void testGetAllProducers() {
        List<String> expected = new ArrayList<>(List.of("Privat Bank", "Kyiv City", "Nova Post", "Ukr Post"));
        List<String> actual = service.getAllProducers(souvenirs);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetIndex() {
        int expected = 1; //for KyivCityPostcard
        int actual = service.getIndex(souvenirs, "Postcard 3", new KyivCity("UA"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testRemoveSouvenirsByProducer() {
        List<String> expected = new ArrayList<>(List.of("Privat Bank", "Nova Post", "Ukr Post"));
        List<Souvenir> s = service.removeSouvenirsByProducer(souvenirs, "Kyiv City");
        List<String> actual = new ArrayList<>();
        for (Souvenir souvenir: s) {
            actual.add(souvenir.getProducer().getName());
        }
        assertThat(actual).isEqualTo(expected);
    }

}