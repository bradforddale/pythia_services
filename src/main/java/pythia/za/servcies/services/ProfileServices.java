package pythia.za.servcies.services;

import org.springframework.stereotype.Service;
import pythia.za.servcies.models.Profile;

@Service
public class ProfileServices {

    public Profile[] getAllProfiles() {
        return new Profile[5] ;
    }
}
