package epam;


import com.epam.jdi.uitests.web.selenium.driver.ScreenshotMaker;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import org.apache.commons.io.output.ByteArrayOutputStream;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.testng.annotations.*;
import epam.entities.Data;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static epam.EpamWebsite.login;
import static epam.EpamWebsite.*;
import static org.apache.log4j.helpers.Loader.getResource;

@Listeners//(epam.allure.allure.class)
@Features({"Testing"})
@Stories({"the test"})
public class EpamTest extends TestNGBase {

    @DataProvider
    public static Object[] dataProvider() throws IOException {
        Map<String, Data> data;

        FileReader fileReader = new FileReader("c:\\test.json");
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
    }


    @Test
    public void loginTest() {
        homePage.open();
        login();
        homePage.checkOpened();


        metalsAndColors.open();

        //deselecting default set value
        metalsAndColors.vegetables.select("Vegetables");

        System.out.println("*****************************");
        System.out.println(metalsAndColors.radioButtons.getValues());
        System.out.println("*****************************");

        System.out.println("*****************************");
        System.out.println(metalsAndColors.elements.getValues());
        System.out.println("*****************************");


        System.out.println("*****************************");
        System.out.println(metalsAndColors.colors.getValues());
        System.out.println("*****************************");

        System.out.println("*****************************");
        System.out.println(metalsAndColors.vegetables.getValues());
        System.out.println("*****************************");

        System.out.println("*****************************");
        System.out.println(metalsAndColors.metals.getValues());
        System.out.println("*****************************");


    }

    @Attachment(type = "image/png")
    public byte[] scr() throws IOException {

        String screenLocation = ScreenshotMaker.takeScreen();

        BufferedImage bImage = ImageIO.read(new File(screenLocation));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();
        return data;
        //return ( data.getData() );
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
