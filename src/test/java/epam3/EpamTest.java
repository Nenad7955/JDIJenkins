package epam3;


import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.driver.ScreenshotMaker;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.apache.commons.io.output.ByteArrayOutputStream;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import com.google.common.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.image.BufferedImage;
import org.testng.annotations.*;
import java.lang.reflect.Type;
import javax.imageio.ImageIO;
import com.google.gson.Gson;
import java.io.IOException;
import epam3.entities.Data;
import java.io.FileReader;
import java.util.Map;
import java.io.File;


import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static epam3.EpamWebsite.login;
import static epam3.EpamWebsite.*;


@Listeners//(epam.allure.allure.class)
@Features({"Testing"})
@Stories({"the test"})
public class EpamTest extends TestNGBase {

    @DataProvider
    public static Object[] dataProvider() throws IOException {
        Map<String, Data> data;

        FileReader fileReader = new FileReader(EpamTest.class.getClassLoader().getResource("data/test.json").getFile());
        JsonReader jsonReader = new JsonReader(fileReader);

        Type type = new TypeToken<Map<String, Data>>() {
        }.getType();

        data = new Gson().fromJson(jsonReader, type);

        return data.values().toArray();
    }


    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        WebSite.init(EpamWebsite.class);
        logger.info("Run Tests");
        driverFactory.getDriver();

        homePage.open();
        login();
        homePage.checkOpened();

        metalsAndColors.open();
        metalsAndColors.checkOpened();

        //deselecting default set value
        metalsAndColors.vegetables.select("Vegetables");
    }


    @Attachment(type = "image/png")
    public byte[] scr() throws IOException {

        String screenLocation = ScreenshotMaker.takeScreen();

        BufferedImage bImage = ImageIO.read(new File(screenLocation));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();
        return data;
    }

    @Test(dataProvider = "dataProvider")
    public void testWithData(Data data) {
        metalsAndColors.fillMetalsAndColorsForm(data);
        metalsAndColors.validate(data);
        try {
            scr();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
