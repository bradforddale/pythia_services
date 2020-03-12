package pythia.za.servcies.models.profile;

public class ProfileNotFoundException extends Exception {
    public ProfileNotFoundException(String id) {
        super("Profile with id " + id + " could not be found");
    }
}
