package pythia.za.servcies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pythia.za.servcies.models.Profile;
import pythia.za.servcies.services.ProfileServices;

@RestController(value = "profile")
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Profile[]> getAllProfiles() {
        return new ResponseEntity<Profile[]>(profileServices.getAllProfiles(), HttpStatus.OK);
    }
}
