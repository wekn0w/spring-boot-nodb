package app.web.domain;

public enum BookGenres {
    fiction("Fiction"),
    romance("Romance"),
    novel("Novel"),
    history("History"),
    drama("Drama"),
    biography("Biography"),
    poem("Poem");

    private final String displayValue;

    BookGenres(String value){
        this.displayValue=value;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
