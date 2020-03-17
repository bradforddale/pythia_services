package pythia.za.servcies.models;

public class NewProfileRequest {
    private String fullname;
    private String cell;
    private String email;

    public NewProfileRequest(String fullname, String cell, String email) {
        this.fullname = fullname;
        this.cell = cell;
        this.email = email;
    }

    public boolean areFieldsValid() {
        return !this.fullname.isEmpty()
        && !this.cell.isEmpty()
        && !this.email.isEmpty();
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
}
