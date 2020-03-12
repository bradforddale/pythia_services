package pythia.za.servcies.models.profile;

public class ProfileIdNotValidException extends Exception {
    public ProfileIdNotValidException(String id) {
        super("Profile id " + id + " is invalid");
    }
}
