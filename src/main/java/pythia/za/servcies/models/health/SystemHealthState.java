package pythia.za.servcies.models.health;

public enum SystemHealthState {
    UP ("UP"),
    DOWN( "DOWN");

    SystemHealthState(String description) {
        this.description = description;
    }

    private String description;

    public String toString() {
        return this.description;
    }
}
