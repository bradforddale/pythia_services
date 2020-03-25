package pythia.za.servcies.models.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pythia.za.servcies.models.NewProfileRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Profile {
    private String id;
    private PersonalInfo personalInfo;
    private ArrayList<AwardAchieved> awardsAchieved;
    private ArrayList<Position> positions;

    public Profile() {
        this.id = generateId();
        this.awardsAchieved = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = new PersonalInfo();
    }

    public Profile(String id) {
        this.id = id;
        this.awardsAchieved = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = new PersonalInfo();
    }

    public Profile(String id, PersonalInfo pi) {
        this.id = id;
        this.awardsAchieved = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
        this.personalInfo = pi;
    }

    public Profile(String id, ArrayList<AwardAchieved> awardsAchieved, ArrayList<Position> positions, PersonalInfo personalInfo) {
        this.id = id;
        this.awardsAchieved = awardsAchieved;
        this.positions = positions;
        this.personalInfo = personalInfo;
    }

    public Profile(NewProfileRequest newProfileRequest) {
        this.id = generateId();
        this.personalInfo = new PersonalInfo(newProfileRequest.getFullname(), newProfileRequest.getCell(), newProfileRequest.getEmail());
        this.awardsAchieved = new ArrayList<AwardAchieved>();
        this.positions = new ArrayList<Position>();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public ArrayList<AwardAchieved> getawardsAchieved() {
        return awardsAchieved;
    }

    public void addAwardAchieved(AwardAchieved a) {
        this.awardsAchieved.add(a);
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
        return toJSON();
    }

    private String toJSON() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"personalInfo\":" + personalInfo +
                ", \"awardsAchieved\":" + awardsAchieved +
                ", \"positions\":" + positions +
                '}';
    }

}
