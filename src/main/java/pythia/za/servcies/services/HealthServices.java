package pythia.za.servcies.services;

import org.springframework.stereotype.Service;
import pythia.za.servcies.models.health.SystemHealth;
import pythia.za.servcies.models.health.SystemHealthState;
import pythia.za.servcies.models.profile.Profile;

import java.util.ArrayList;

@Service
public class HealthServices {
    private String otherSystems;

    public SystemHealth getSystemHealth() {
        return new SystemHealth("Services", SystemHealthState.UP);
    }

    public SystemHealth[] getAllSystemHealth() {
        ArrayList<SystemHealth> systems = new ArrayList();
        systems.add(getSystemHealth());
        systems.add(new SystemHealth("Data", SystemHealthState.DOWN));
        return systems.toArray(new SystemHealth[0]);
    }
}
