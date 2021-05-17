package weatherAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LocationController {
    @Autowired
    LocationRepository repository;

    //Authentication
    //username: user
    //password: password
    @RequestMapping(method = RequestMethod.GET, value = "locations")
    public @ResponseBody ResponseEntity<?> findAll() throws JsonProcessingException {
        List<Location> locations = repository.findAll();
        List<LocationResourceSupport> resources = new ArrayList<>();
        for(Location location : locations){
            LocationResourceSupport resource = toResource(location);
            resources.add(resource);
        }
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(method = RequestMethod.GET, value = "locations/{locationName}")
    public @ResponseBody
    ResponseEntity<?> findByName(@PathVariable String locationName) throws JsonProcessingException {
        Location location = repository.findByName(locationName);
        LocationResourceSupport resource = toResource(location);
        return ResponseEntity.ok(resource);
    }

    private LocationResourceSupport toResource(Location location) throws JsonProcessingException {
        LocationResourceSupport resourceSupport = new LocationResourceSupport();
        resourceSupport.setId(location.getId());
        resourceSupport.setName(location.getName());
        resourceSupport.setcI(stringToMap(location.getCI()));
        resourceSupport.setMaxT(stringToMap(location.getMaxT()));
        resourceSupport.setMinT(stringToMap(location.getMinT()));
        resourceSupport.setPoP(stringToMap(location.getPoP()));
        resourceSupport.setWx(stringToMap(location.getWx()));

        return resourceSupport;
    }

    private List<Map<String, Object>> stringToMap (List<String> stringList) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        List<Map<String, Object>> mapList = new ArrayList<>();

        for(String string: stringList) {
            Map<String, Object> temp = om.readValue(string, Map.class);
            mapList.add(temp);
        }

        return mapList;
    }
}
