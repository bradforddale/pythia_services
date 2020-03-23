package pythia.za.servcies.models.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AwardAchieved {
    private String id;
    private String description;
    private LocalDateTime dateAchieved;

    public AwardAchieved(String id, String description, LocalDateTime dateAchieved) {
        this.id = id;
        this.description = description;
        this.dateAchieved = dateAchieved;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }

    @Override
    public String toString() {
        return "AwardAchieved{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", dateAchieved=" + dateAchieved +
                '}';
    }
}
