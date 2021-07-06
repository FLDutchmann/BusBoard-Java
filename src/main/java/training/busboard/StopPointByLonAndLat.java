package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointByLonAndLat {
    public String naptanId;
    public Double distance;
    public String commonName;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getNaptanId() {
        return naptanId;
    }

    public void setNaptanId(String naptanId) {
        this.naptanId = naptanId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
