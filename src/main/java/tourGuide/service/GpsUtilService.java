package tourGuide.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class GpsUtilService {
    private Logger logger = LoggerFactory.getLogger(GpsUtilService.class);

    //TODO: Difference b/w static and non static methods - When to use which
    //TODO: Dependency injection - Working
    public List<Attraction> getAttractions(){
        //TODO: Write loggers for printing the time taken for this API call
        WebClient.Builder webClientBuilder = WebClient.builder();
        String jsonResponseFromGetAttraction = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/getAttractions")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gson = new Gson();
        List<Attraction> attractionList = gson.fromJson(jsonResponseFromGetAttraction,
                new TypeToken<List<Attraction>>(){}.getType());
        return attractionList;
    }

    //TODO: Remove RequestParam annotation & use "attribute" method of webClientBuilder
    public VisitedLocation getUserLocation(@RequestParam UUID userId) {
        WebClient.Builder webClientBuilder = WebClient.builder();
        String JsonResponseFrom = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/getUserLocation?userId="+userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        Gson gson = new Gson();
        return gson.fromJson(JsonResponseFrom, VisitedLocation.class);
    }

}