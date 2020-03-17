package pythia.za.servcies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythia.za.servcies.models.MessageResponse;
import pythia.za.servcies.models.NewProfileRequest;
import pythia.za.servcies.models.profile.ProfileIdNotValidException;
import pythia.za.servcies.models.profile.ProfileNotFoundException;
import pythia.za.servcies.services.ProfileServices;

@RestController
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @RequestMapping(value = "/profile/", method = RequestMethod.GET, produces = "application/json")
    @CrossOrigin
    public ResponseEntity getAllProfiles() {
        return new ResponseEntity(profileServices.getAllProfiles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin
    public ResponseEntity createProfile(@RequestBody NewProfileRequest newProfileRequest) {
        if (newProfileRequest.areFieldsValid()) {
            return new ResponseEntity(profileServices.createProfile(newProfileRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse("The fields given are invalid"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, produces = "application/json")
    @CrossOrigin
    public ResponseEntity getProfile(@PathVariable("id") String id ) {
        try {
            return new ResponseEntity(profileServices.getProfile(id), HttpStatus.OK);
        } catch (ProfileNotFoundException pe) {
            return new ResponseEntity(new MessageResponse(pe), HttpStatus.NOT_FOUND);
        } catch (ProfileIdNotValidException pv) {
            return new ResponseEntity(new MessageResponse(pv), HttpStatus.BAD_REQUEST);
        }
    }
}
