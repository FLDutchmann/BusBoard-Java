package training.busboard.jsonAPIs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrivalPrediction {
    public String vehicleId;
    public String expectedArrival;
    public String lineName;
    public String towards;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }
}
