package epam.enums;

public enum HOME_PAGE_DATA {
    URL("https://jdi-framework.github.io/tests/index.htm"),
    LINK_INDEX("Index Page"),
    ID("epam"),
    PASSWORD("1234"),
    USERNAME("PITER CHAILOVSKII"),
    FIRST_TEXT("To include good practices\nand ideas from successful\nEPAM projec"),
    SECOND_TEXT("To be flexible and\ncustomizable"),
    THIRD_TEXT("To be multiplatform"),
    FOURTH_TEXT("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"),
    FRAMEWORK("EPAM FRAMEWORK WISHES…"),
    WEIRD("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");


    HOME_PAGE_DATA(final String value) {
        this.value = value;
    }


    public String value;
}