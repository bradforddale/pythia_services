package pythia.za.servcies.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
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
        System.out.println(request);
        return (new RestTemplate()).postForEntity(url, request, String.class);
    }

    public List<Profile> getAll() {
        try {
            ResponseEntity  responseEntity = post(PROFILES_URL,restRequest( new DataRequest<Profile>("getAll", null)));
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                JsonNode responseJson = readJsonFromString((String)responseEntity.getBody());
                return convertProfileList(responseJson);
            } else {
                System.out.println("An error occurred with Pythia Data: " + responseEntity.toString());
                return new ArrayList<Profile>();
            }
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException occurred:");
            e.printStackTrace();
        } catch (InvalidClubPosition invalidClubPosition) {
            System.out.println("InvalidClubPosition occurred:");
            invalidClubPosition.printStackTrace();
        } catch (RuntimeException re) {
            System.out.println("RuntimeException occurred:");
            re.printStackTrace();
        }
        return new ArrayList<Profile>();
    }

    public Profile get(String id) {
        try {
            ResponseEntity responseEntity = post(PROFILES_URL, restRequest(new DataRequest("get", id)));
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                JsonNode responseJson = readJsonFromString((String)responseEntity.getBody());
                JsonNode resultJson = readJsonFromString(responseJson.at("/result").textValue());
                return convertProfile(resultJson);
            } else {
                System.out.println("An error occurred with Pythia Data: " + responseEntity.toString());
                return new Profile();
            }
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException occurred:");
            e.printStackTrace();
        } catch (InvalidClubPosition invalidClubPosition) {
            System.out.println("InvalidClubPosition occurred:");
            invalidClubPosition.printStackTrace();
        } catch (RuntimeException re) {
            System.out.println("RuntimeException occurred:");
            re.printStackTrace();
        }
        return new Profile();
    }

    public void create(Profile newProfile) {
        ResponseEntity responseEntity = post(PROFILES_URL, restRequest(new DataRequest("create", newProfile)));
    }

    public void update(Profile updatedProfile) {
        ResponseEntity responseEntity = post(PROFILES_URL, restRequest(new DataRequest("update", updatedProfile)));
    }

    public void delete(String id) {
        ResponseEntity responseEntity = post(PROFILES_URL, restRequest(new DataRequest("delete", id)));
    }

    private JsonNode readJsonFromString(String jsonString) throws JsonProcessingException {
        return (new ObjectMapper()).readTree(jsonString);
    }

    private List<Profile> convertProfileList(JsonNode responseJson) throws JsonProcessingException, InvalidClubPosition {
        List<Profile> profiles = new ArrayList<>();
        JsonNode resultJson = (new ObjectMapper()).readTree(responseJson.at("/result").textValue());
        for (int i = 0; i < resultJson.size(); i++) {
            profiles.add(convertProfile(resultJson.get(i)));
        }
        return profiles;
    }

    private Profile convertProfile(JsonNode currentNode) throws InvalidClubPosition {
        Profile p = createInitialProfileFromRootJson(currentNode);
        addAwardsAchievedToProfile(currentNode, p);
        addPositionsToProfile(currentNode, p);
        return p;
    }

    private Profile createInitialProfileFromRootJson(JsonNode currentNode) {
        return new Profile(currentNode.path("id").textValue(),
                new PersonalInfo(currentNode.at("/personalInfo/fullname").textValue(),
                        currentNode.at("/personalInfo/cell").textValue(),
                        currentNode.at("/personalInfo/email").textValue()));
    }

    private void addPositionsToProfile(JsonNode currentNode, Profile p) throws InvalidClubPosition {
        JsonNode positionsJson = currentNode.at("/positions");
        for (int j = 0 ; j < positionsJson.size(); j++) {
            Position po = new Position(positionsJson.get(j).at("/id").textValue(),
                    ClubPosition.of(positionsJson.get(j).at("/clubPosition").textValue()),
                    positionsJson.get(j).at("/club").textValue(),
                    LocalDate.parse(positionsJson.get(j).at("/dateStarted").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay(),
                    LocalDate.parse(positionsJson.get(j).at("/dateEnded").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
            p.addPosition(po);
        }
    }

    private void addAwardsAchievedToProfile(JsonNode currentNode, Profile p) {
        JsonNode awardsAchievedJson = currentNode.at("/awardsAchieved");
        for (int j = 0 ; j < awardsAchievedJson.size(); j++) {
            AwardAchieved a = new AwardAchieved(awardsAchievedJson.get(j).at("/id").textValue(),
                    awardsAchievedJson.get(j).at("/description").textValue(),
                    LocalDate.parse(awardsAchievedJson.get(j).at("/dateAchieved").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
            p.addAwardAchieved(a);
        }
    }
}
