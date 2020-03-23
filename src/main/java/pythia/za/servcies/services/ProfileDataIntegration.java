package pythia.za.servcies.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pythia.za.servcies.models.InvalidClubPosition;
import pythia.za.servcies.models.profile.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileDataIntegration {
    public List<Profile> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DataRequest<Profile> dataRequest = new DataRequest<Profile>("getAll", null);
        HttpEntity<String> request = new HttpEntity<>(dataRequest.toString(), headers);
        System.out.println("Request is: " + request.toString());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity  responseEntity = restTemplate.postForEntity("http://localhost:8090/profiles/", request, String.class);
//        System.out.println(responseEntity);
        ArrayList<Profile> profiles = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj = mapper.readTree((String)responseEntity.getBody());
            System.out.println("JSON obj " +  actualObj);
            System.out.println("JSON root " + actualObj.size());
            System.out.println("JSON root " + actualObj.getClass().getName());

//            DateTimeFormatter fmt = new DateTimeFormat();

            for (int i = 0; i < actualObj.size(); i++) {

                System.out.println(actualObj.get(i).path("id").textValue());
                System.out.println(actualObj.get(i).path("personalInfo").path("fullname").textValue());
                System.out.println(actualObj.get(i).at("/personalInfo/fullname").textValue());
                Profile p = new Profile(actualObj.get(i).path("id").textValue(),
                                        new PersonalInfo(actualObj.get(i).at("/personalInfo/fullname").textValue(),
                                                actualObj.get(i).at("/personalInfo/cell").textValue(),
                                                actualObj.get(i).at("/personalInfo/email").textValue()));
                JsonNode awardsAchievedJson = actualObj.get(i).at("/awardsAchieved");
                System.out.println(awardsAchievedJson.size());
                for (int j = 0 ; j < awardsAchievedJson.size(); j++) {
                    AwardAchieved a = new AwardAchieved(awardsAchievedJson.get(j).at("/id").textValue(),
                            awardsAchievedJson.get(j).at("/description").textValue(),
                            LocalDate.parse(awardsAchievedJson.get(j).at("/dateAchieved").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
                    System.out.println(a);
                    p.addAwardAchieved(a);
                }
                JsonNode positionsJson = actualObj.get(i).at("/positions");
                for (int j = 0 ; j < positionsJson.size(); j++) {
                    Position po = new Position(positionsJson.get(j).at("/id").textValue(),
                            ClubPosition.of(positionsJson.get(j).at("/clubPosition").textValue()),
                            positionsJson.get(j).at("/club").textValue(),
                            LocalDate.parse(positionsJson.get(j).at("/dateStarted").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay(),
                            LocalDate.parse(positionsJson.get(j).at("/dateEnded").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
                    System.out.println(po);
                    p.addPosition(po);
                }
                System.out.println(p);
                profiles.add(p);

            }

//            Profile[] toValue = mapper.treeToValue(actualObj, Profile[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InvalidClubPosition invalidClubPosition) {
            invalidClubPosition.printStackTrace();
        }
        return profiles;
    }

//    private Object getJSONProperty(JsonNode json, String... path) {
//        actualObj.get(i).path("personalInfo").path("fullname")
//    }
}
