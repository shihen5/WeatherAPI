package weatherAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    LocationRepository repository;

    //service is scheduled to run once ever hour
    @Scheduled(cron = "0 0 * * * ?")
    public void fetchWeatherInfo(){
        String urlString = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-E1CFCFD5-D29D-4DBB-8950-3B8F93850EEA&format=JSON&locationName=%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82&sort=time";

        List<Location> locations = new ArrayList<>();
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null){
                result.append(line);
            }
            rd.close();


            Map<String, Object> recordMap;
            List<Map<String, Object>> locationList = new ArrayList<>();

            ObjectMapper om = new ObjectMapper();
            Map<String, Object> map = om.readValue(result.toString(), Map.class);
            if(map.get("records") != null){
                recordMap = (Map<String, Object>) map.get("records");
                if(recordMap.get("location") != null){
                    locationList = (List<Map<String, Object>>) (recordMap.get("location"));
                }
            }

            for(Map<String, Object> datum : locationList){
                Location location = new Location();

                if(repository.findByName((String) datum.get("locationName")) != null){
                    location = repository.findByName((String) datum.get("locationName"));
                } else {
                    location.setName((String) datum.get("locationName"));
                }

                List<Map<String, Object>> weatherElements = (List<Map<String, Object>>) datum.get("weatherElement");
                for(Map<String, Object> weatherElement: weatherElements){
                    List<Map<String, Object>> elementTime = (List<Map<String, Object>>) weatherElement.get("time");

                    List<String> strings = elementTime.stream()
                            .map(object -> new JSONObject(object).toString())
                            .collect(Collectors.toList());

                    if(weatherElement.get("elementName").equals("Wx")){
                        location.setWx(strings);
                    } else if(weatherElement.get("elementName").equals("PoP")){
                        location.setPoP(strings);
                    } else if(weatherElement.get("elementName").equals("MinT")){
                        location.setMinT(strings);
                    } else if(weatherElement.get("elementName").equals("CI")){
                        location.setCI(strings);
                    } else if(weatherElement.get("elementName").equals("MaxT")){
                        location.setMaxT(strings);
                    }
                }
                locations.add(location);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        repository.saveAll(locations);
        log.info("Weather information fetched successfully.");
    }

}
