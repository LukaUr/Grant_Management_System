package lukaur.grant_management_system.app.web.model.dictionaries;

public enum IndicatorType {

    PRODUCT_INDICATOR("product"),
    RESULT_INDICATOR("result");

    String display;
    IndicatorType(String display) {
        this.display = display;
    }

    public String display() {
        return this.display;
    }
}
