package CollectiveIntelligence;

public enum Movies {
    lADY_WATER("Lady in the Water"),
    sNAKES("Snakes on a Plane"),
    jUST_LUCK("Just My Luck"),
    sUPERMAN_RETURNS("Superman Returns"),
    nIGHT_LISTENER("The Night Listener"),
    yOU_ME_DUPREE("You, Me and Dupree");

    private final String value;

    Movies(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
