package pythia.za.servcies.models.health;

import java.io.Serializable;

public class SystemHealth implements Serializable {
    private String component;
    private SystemHealthState componentHealth;

    public SystemHealth(String component, SystemHealthState componentHealth) {
        this.component = component;
        this.componentHealth = componentHealth;
    }

    public String getComponent() {
        return component;
    }

    public SystemHealthState getComponentHealth() {
        return componentHealth;
    }

    @Override
    public String toString() {
        return component + ": " + componentHealth;
    }

}
