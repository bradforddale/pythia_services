package pythia.za.servcies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pythia.za.servcies.models.profile.Profile;
import pythia.za.servcies.models.profile.ProfileIdNotValidException;
import pythia.za.servcies.models.profile.ProfileNotFoundException;
import pythia.za.servcies.services.ProfileServices;

@RestController
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @RequestMapping(value = "/profile/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllProfiles() {
        return new ResponseEntity(profileServices.getAllProfiles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProfile(@PathVariable("id") String id ) {
        try {
            return new ResponseEntity(profileServices.getProfile(id), HttpStatus.OK);
        } catch (ProfileNotFoundException pe) {
            return new ResponseEntity(pe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProfileIdNotValidException pv) {
            return new ResponseEntity(pv.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
