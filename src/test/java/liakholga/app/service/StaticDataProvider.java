package liakholga.app.service;

import liakholga.app.producers.NovaPost;
import liakholga.app.producers.UkrPost;
import liakholga.app.souvenirs.NovaPostCup;
import liakholga.app.souvenirs.UkrPostCup;
import org.testng.annotations.DataProvider;

import java.util.Date;

public class StaticDataProvider {

    @DataProvider(name = "remove")
    public static Object[][] createDataForRemoveProducer() {
        return new Object[][]{{
        }};
    }
}
