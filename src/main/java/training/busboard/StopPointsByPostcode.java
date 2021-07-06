package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointsByPostcode {
    public String vehicleId;
    public String expectedArrival;
}
