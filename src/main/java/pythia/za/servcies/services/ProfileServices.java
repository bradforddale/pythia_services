package pythia.za.servcies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pythia.za.servcies.models.MessageResponse;
import pythia.za.servcies.models.NewProfileRequest;
import pythia.za.servcies.models.profile.Profile;
import pythia.za.servcies.models.profile.ProfileIdNotValidException;
import pythia.za.servcies.models.profile.ProfileNotFoundException;

import java.util.ArrayList;

@Service
public class ProfileServices {

    @Autowired
    private ProfileRepo profileRepo;

    public Profile[] getAllProfiles() {
        return profileRepo.getAll().toArray(new Profile[0]) ;
    }

    public Profile getProfile(String id) throws ProfileNotFoundException, ProfileIdNotValidException {
        if (this.validateProfileId(id)) {
            return profileRepo.get(id);
        } else {
            throw new ProfileIdNotValidException("Profile id " + id + " is invalid");
        }
    }

    public MessageResponse createProfile(NewProfileRequest newProfileRequest) {
        Profile newProfile = new Profile(newProfileRequest);
        profileRepo.add(newProfile);
        return new MessageResponse("Profile created with id " + newProfile.getId());
    }

    public MessageResponse updateProfile(String id, Profile updatedProfile) throws ProfileIdNotValidException, ProfileNotFoundException {
        Profile currentProfile = this.getProfile(id);
        if (currentProfile != null && id.equalsIgnoreCase(updatedProfile.getId()) && currentProfile.getId().equalsIgnoreCase(updatedProfile.getId())) {
            profileRepo.update(id, updatedProfile);
            return new MessageResponse("Profile with id " + id + " was updated");
        } else {
            throw new ProfileIdNotValidException("Profile with id " + id + " is invalid" + updatedProfile);
        }
    }

    private boolean validateProfileId (String id) {
        return !id.isEmpty();
    }
}
