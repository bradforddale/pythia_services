package pythia.za.servcies.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pythia.za.servcies.models.InvalidClubPosition;
import pythia.za.servcies.models.profile.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileDataIntegration {
    private final String PROFILES_URL = "http://localhost:8090/profiles/";

    private HttpEntity<String> restRequest(DataRequest body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(body.toString(), headers);
    }

    private ResponseEntity post(String url, HttpEntity<String> request) {
        return (new RestTemplate()).postForEntity(url, request, String.class);
    }

    public List<Profile> getAll() {
        ResponseEntity  responseEntity = post(PROFILES_URL,restRequest( new DataRequest<Profile>("getAll", null)));
        System.out.println(responseEntity.getStatusCode());
        List<Profile> profiles = new ArrayList<>();
        try {
//            TODO Needs some excpetion handling code
            JsonNode responseJson = (new ObjectMapper()).readTree((String)responseEntity.getBody());
            profiles = successfulResults(responseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InvalidClubPosition invalidClubPosition) {
            invalidClubPosition.printStackTrace();
        }
        return profiles;
    }

    private List<Profile> successfulResults(JsonNode responseJson) throws JsonProcessingException, InvalidClubPosition {
        List<Profile> profiles = new ArrayList<>();
        JsonNode resultJson = (new ObjectMapper()).readTree(responseJson.at("/result").textValue());
        for (int i = 0; i < resultJson.size(); i++) {
            JsonNode currentNode = resultJson.get(i);
            Profile p = parseProfileJson(currentNode);
            addAwardsAcheivedToProfile(currentNode, p);
            addPositionsToProfile(currentNode, p);
            profiles.add(p);
        }
        return profiles;
    }

    private void addPositionsToProfile(JsonNode currentNode, Profile p) throws InvalidClubPosition {
        JsonNode positionsJson = currentNode.at("/positions");
        for (int j = 0 ; j < positionsJson.size(); j++) {
            Position po = new Position(positionsJson.get(j).at("/id").textValue(),
                    ClubPosition.of(positionsJson.get(j).at("/clubPosition").textValue()),
                    positionsJson.get(j).at("/club").textValue(),
                    LocalDate.parse(positionsJson.get(j).at("/dateStarted").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay(),
                    LocalDate.parse(positionsJson.get(j).at("/dateEnded").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
            System.out.println(po);
            p.addPosition(po);
        }
    }

    private void addAwardsAcheivedToProfile(JsonNode currentNode, Profile p) {
        JsonNode awardsAchievedJson = currentNode.at("/awardsAchieved");
        for (int j = 0 ; j < awardsAchievedJson.size(); j++) {
            AwardAchieved a = new AwardAchieved(awardsAchievedJson.get(j).at("/id").textValue(),
                    awardsAchievedJson.get(j).at("/description").textValue(),
                    LocalDate.parse(awardsAchievedJson.get(j).at("/dateAchieved").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
            p.addAwardAchieved(a);
        }
    }

    private Profile parseProfileJson(JsonNode currentNode) {
        return new Profile(currentNode.path("id").textValue(),
                        new PersonalInfo(currentNode.at("/personalInfo/fullname").textValue(),
                                currentNode.at("/personalInfo/cell").textValue(),
                                currentNode.at("/personalInfo/email").textValue()));
    }

}
