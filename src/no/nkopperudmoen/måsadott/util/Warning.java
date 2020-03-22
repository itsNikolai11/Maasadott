package no.nkopperudmoen.måsadott.util;

public class Warning {
    private String text;
    private String warnedBy;

    public Warning(String warnedBy, String text) {
        this.text = text;
        this.warnedBy = warnedBy;
    }

    public String getText() {
        return text;
    }

    public String getWarner() {
        return warnedBy;
    }
}
