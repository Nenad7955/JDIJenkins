package epam3.pages;


import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JComboBox;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.complex.*;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import org.openqa.selenium.support.FindBy;
import com.epam.web.matcher.testng.Assert;
import epam3.entities.Data;



@JPage(url = "metals-colors.html", title = "Metal and Colors")
public class MetalsAndColors extends WebPage {

    @FindBy(css = ".radio")
    public RadioButtons radioButtons;

    //@JFindBy(id = "elements-checklist")
    @JFindBy(css = "#elements-checklist | .checkbox label")
    public CheckList elements;

    @JDropdown(
            jroot = @JFindBy(id = "colors"),  // kinda disappointed that it doesn't work with
            jlist = @JFindBy(tagName = "a")  //jroot, jlist, jfind and jcombobox =(
    )
    public IDropDown colors;

    @JComboBox(root = @FindBy(css = ".salad"),
            list = @FindBy(tagName = "li"),
            value = @FindBy(tagName = "button")
    )

    public ComboBox vegetables;

/*    @JDropList( root = @FindBy(id="vegetables"),
            list = @FindBy(tagName = "li")
            //expand = @FindBy()*/ // doesn't work at all


    @JComboBox(root = @FindBy(css = ".metals"),
            expand = @FindBy(css = ".caret"),
            list = @FindBy(tagName = "a"))
    public IComboBox metals;

    @JFindBy(id = "submit-button")
    public Button submit;


    @Step("Fiiling up data")
    public void fillMetalsAndColorsForm(Data data) {
        for (int i = 0; i < data.summary.length; i++)
            radioButtons.select(String.valueOf(data.summary[i]));

        for (int i = 0; i < data.elements.length; i++)
            elements.select(data.elements[i]);

        colors.select(data.color);

        metals.select(data.metals);

        for (int i = 0; i < data.vegetables.length; i++)
            vegetables.select(data.vegetables[i]);

        submit.click();

        for (int i = 0; i < data.elements.length; i++) //uncheckAll isn't really working..
            elements.select(data.elements[i]);
        for (int i = 0; i < data.vegetables.length; i++)
            vegetables.select(data.vegetables[i]);

    }

    @JFindBy(css = ".panel-body-list.results")
    public TextList result;

    @Attachment(value = "Validated Data:")
    @Step("Validating values")
    public String validate(Data data) {

        int sum = 0;

        for (int i = 0; i < data.summary.length; i++)
            sum += data.summary[i];

        String elements = new String();
        for (int i = 0; i < data.elements.length; i++) {
            elements += data.elements[i];
            if (i <= data.elements.length - 2)
                elements += ", ";
        }

        String vegetables = new String();
        for (int i = 0; i < data.vegetables.length; i++) {
            vegetables += data.vegetables[i];
            if (i <= data.vegetables.length - 2)
                vegetables += ", ";
        }
        String Text = new String(
                        "Summary: " + Integer.toString(sum) +
                        "\nElements: " + elements +
                        "\nColor: " + data.color +
                        "\nMetal: " + data.metals +
                        "\nVegetables: " + vegetables);


        Assert.contains(result.getValue(),Text);


        return Text;
    }
}