package lukaur.grant_management_system.app.web.model.project.misc;

public enum ConsentOption {
    YES("yes"),
    NO("no"),
    NOT_APPLICABLE("not applicable");

    String display;

    ConsentOption(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return this.display;
    }
}
