package pythia.za.servcies.models.profile;

import java.time.LocalDateTime;

public class Position {
    private String id;
    private ClubPosition clubPosition;
    private String club;
    private LocalDateTime dateStarted;
    private LocalDateTime dateEnded;


    public Position(String id, ClubPosition clubPosition, String club, LocalDateTime dateStarted, LocalDateTime dateEnded) {
        this.id = id;
        this.clubPosition = clubPosition;
        this.club = club;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
    }

    public ClubPosition getClubPosition() {
        return clubPosition;
    }

    public String getClub() {
        return club;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    @Override
    public String toString() {
        return toJSON();
    }

    private String toJSON() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"clubPosition\":\"" + clubPosition + '\"' +
                ", \"club\":\"" + club + '\"' +
                ", \"dateStarted\":\"" + dateStarted + '\"' +
                ", \"dateEnded\":\"" + dateEnded + '\"' +
                '}';
    }
}
