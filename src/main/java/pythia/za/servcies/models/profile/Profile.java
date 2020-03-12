package pythia.za.servcies.models.profile;

import java.util.ArrayList;
import java.util.UUID;

public class Profile {
    private String id;
    private ArrayList<AwardAchieved> awardAchieveds;
    private ArrayList<Position> positions;
    private PersonalInfo personalInfo;

    public Profile() {
        this.id = UUID.randomUUID().toString();;
        this.awardAchieveds = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = new PersonalInfo();
    }

    public Profile(String id) {
        this.id = id;
        this.awardAchieveds = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = new PersonalInfo();
    }

    public Profile(String id, PersonalInfo pi) {
        this.id = id;
        this.awardAchieveds = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = pi;
    }

    public Profile(String id, ArrayList<AwardAchieved> awardAchieveds, ArrayList<Position> positions, PersonalInfo personalInfo) {
        this.id = id;
        this.awardAchieveds = awardAchieveds;
        this.positions = positions;
        this.personalInfo = personalInfo;
    }

    public ArrayList<AwardAchieved> getAwardAchieveds() {
        return awardAchieveds;
    }

    public void addAwardAchieved(AwardAchieved a) {
        this.awardAchieveds.add(a);
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void addPosition(Position p) {
        this.positions.add(p);
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getId() {
        return id;
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }

    public boolean equals(Profile p) {
        return this.equals(p.getId());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", awardAchieveds=" + awardAchieveds +
                ", positions=" + positions +
                ", personalInfo=" + personalInfo +
                '}';
    }

    //    @Override
//    public boolean equals(Object o) {
//        try {
//            return this.equals((Profile) o);
//        } finally
//        }
//    }

}
