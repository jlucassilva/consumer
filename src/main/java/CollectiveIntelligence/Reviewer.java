package CollectiveIntelligence;

public enum Reviewer {

    Rose("Lisa Rose"),
    Seymour("Gene Seymour"),
    Phillips("Michael Phillips"),
    Puig("Claudia Puig"),
    LaSalle("Mick LaSalle"),
    Matthews("Jack Matthews"),
    Toby("Toby");

    private final String name;

    Reviewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
