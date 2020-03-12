package pythia.za.servcies.models.profile;

public enum ClubPosition {
    PRESIDENT ("President"),
    VPE("Vice President Education"),
    VPM("Vice President Membership");

    private String name;
    ClubPosition(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
