package pythia.za.servcies.services;

import org.springframework.stereotype.Service;
import pythia.za.servcies.models.SystemHealth;
import pythia.za.servcies.models.SystemHealthState;

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
        return (SystemHealth[])systems.toArray();
    }
}
