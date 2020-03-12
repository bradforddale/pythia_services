package pythia.za.servcies.models.profile;

import java.time.LocalDateTime;

public class AwardAchieved {
    private String description;
    private LocalDateTime dateAchieved;

    public AwardAchieved(String description, LocalDateTime dateAchieved) {
        this.description = description;
        this.dateAchieved = dateAchieved;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }
}
