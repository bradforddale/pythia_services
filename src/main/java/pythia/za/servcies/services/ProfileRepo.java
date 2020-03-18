package pythia.za.servcies.services;

import org.springframework.stereotype.Repository;
import pythia.za.servcies.models.profile.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

//TODO This should be an actual database with services that exist as a seperate microservice
@Repository
public class ProfileRepo {

    private ArrayList<Profile> profiles = new ArrayList<Profile>();

    public ProfileRepo() {
        Profile p1 = new Profile("2169d99d-0ed2-4e00-a09d-a78096ff052a", new PersonalInfo("James Aglioti", "071318333", "gafhjd@gmail.com"));
        p1.addAwardAchieved(new AwardAchieved("Competent Communicator", LocalDateTime.now()));
        p1.addAwardAchieved(new AwardAchieved("Competent Leadership", LocalDateTime.now()));
        p1.addPosition(new Position(ClubPosition.VPE, "Melrose Arch Toastmasters", LocalDateTime.now().minusDays(1), LocalDateTime.now() ));
        profiles.add(p1);

        Profile p2 = new Profile("938edbac-f288-420b-ae6f-c6d80b5be527", new PersonalInfo("Billy dfd", "0834445555", "fdsfdsd@gmail.com"));
        p2.addAwardAchieved(new AwardAchieved("Competent Communicator", LocalDateTime.now()));
        p2.addAwardAchieved(new AwardAchieved("Advanced Communicator Bronze", LocalDateTime.now()));
        p1.addPosition(new Position(ClubPosition.PRESIDENT, "Woodlands Toastmasters", LocalDateTime.now().minusDays(1), LocalDateTime.now() ));
        profiles.add(p2);
    }

    public void add(Profile p) {
        profiles.add(p);
    }

    public ArrayList<Profile> getAll() {
        return profiles;
    }

    public Profile get(String id) throws ProfileNotFoundException{
        for (Profile p: profiles) {
            System.out.println(p);
            if (p.equals(id)) {
                System.out.println("Found the right profile");
                return p;
            }
        }
        throw new ProfileNotFoundException(id);
    }

    public void update(String id, Profile updatedProfile) {
        this.delete(id);
        this.add(updatedProfile);
    }

    public void delete(String profileId) {
        this.profiles.removeIf(createSameIdPredicate(profileId));
    }

    private Predicate<Profile> createSameIdPredicate(String profileId) {
        return (Profile profile) -> profile.getId().equalsIgnoreCase(profileId);
    }
}
