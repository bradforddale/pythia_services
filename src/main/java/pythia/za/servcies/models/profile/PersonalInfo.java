package pythia.za.servcies.models.profile;

public class PersonalInfo {

    private String fullname;
    private String cell;
    private String email;

    public PersonalInfo() {
        this.fullname = "BLANK";
        this.cell = "BLANK";
        this.email = "BLANK";
    }

    public PersonalInfo(String fullname, String cell, String email) {
        this.fullname = fullname;
        this.cell = cell;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "fullname='" + fullname + '\'' +
                ", cell='" + cell + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
