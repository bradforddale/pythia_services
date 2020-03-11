package pythia.za.servcies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pythia.za.servcies.models.SystemHealth;
import pythia.za.servcies.services.HealthServices;

@RestController
public class HealthController {

    @Autowired
    private HealthServices healthServices;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<SystemHealth> getHealth() {
        return new ResponseEntity<SystemHealth>(healthServices.getSystemHealth(), HttpStatus.OK);
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<SystemHealth[]> getAllHealth() {
        return new ResponseEntity<SystemHealth[]>(healthServices.getAllSystemHealth(), HttpStatus.OK);
    }

}
