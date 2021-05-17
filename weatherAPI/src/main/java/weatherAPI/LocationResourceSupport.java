package weatherAPI;

import java.util.List;
import java.util.Map;

public class LocationResourceSupport {
    private Long id;
    private String name;
    private List<Map<String, Object>> wx;
    private List<Map<String, Object>> maxT;
    private List<Map<String, Object>> minT;
    private List<Map<String, Object>> cI;
    private List<Map<String, Object>> poP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getWx() {
        return wx;
    }

    public void setWx(List<Map<String, Object>> wx) {
        this.wx = wx;
    }

    public List<Map<String, Object>> getMaxT() {
        return maxT;
    }

    public void setMaxT(List<Map<String, Object>> maxT) {
        this.maxT = maxT;
    }

    public List<Map<String, Object>> getMinT() {
        return minT;
    }

    public void setMinT(List<Map<String, Object>> minT) {
        this.minT = minT;
    }

    public List<Map<String, Object>> getcI() {
        return cI;
    }

    public void setcI(List<Map<String, Object>> cI) {
        this.cI = cI;
    }

    public List<Map<String, Object>> getPoP() {
        return poP;
    }

    public void setPoP(List<Map<String, Object>> poP) {
        this.poP = poP;
    }
}
