package pythia.za.servcies.models.profile;

import pythia.za.servcies.models.InvalidClubPosition;

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

    public static ClubPosition of(String clubPosition) throws InvalidClubPosition {
        switch (clubPosition.trim()) {
            case "PRESIDENT": return PRESIDENT;
            case "VPE": return VPE;
            case "VPM": return VPM;
            default:
                throw new InvalidClubPosition("There is no " + clubPosition);
        }
    }
}
