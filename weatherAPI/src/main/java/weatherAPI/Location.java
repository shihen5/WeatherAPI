package weatherAPI;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;


@Entity
public class Location {
    private  @Id @GeneratedValue Long id;
    private String name;

    //weather experience
    @ElementCollection
    private List<String> wx;

    //maximum temperature
    @ElementCollection
    private List<String> maxT;

    //minimum temperature
    @ElementCollection
    private List<String> minT;

    //comfort index
    @ElementCollection
    private List<String> cI;

    //percentage of precipitation
    @ElementCollection
    private List<String> poP;

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

    public List<String> getWx() {
        return wx;
    }

    public void setWx(List<String> wx) {
        this.wx = wx;
    }

    public List<String> getMaxT() {
        return maxT;
    }

    public void setMaxT(List<String> maxT) {
        this.maxT = maxT;
    }

    public List<String> getMinT() {
        return minT;
    }

    public void setMinT(List<String> minT) {
        this.minT = minT;
    }

    public List<String> getCI() {
        return cI;
    }

    public void setCI(List<String> cI) {
        this.cI = cI;
    }

    public List<String> getPoP() {
        return poP;
    }

    public void setPoP(List<String> poP) {
        this.poP = poP;
    }
}
