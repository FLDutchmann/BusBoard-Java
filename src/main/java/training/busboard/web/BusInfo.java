package training.busboard.web;

import training.busboard.jsonAPIs.*;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private List<BusStopAndArrivals> busStopsAndArrivals;

    public BusInfo(String postcode) {
        this.postcode = postcode;
        busStopsAndArrivals = TransportAPI.getNextArrivalsAtBusStopsNearPostcode(postcode);
    }

    public List<BusStopAndArrivals> getBusStopsAndArrivals() {
        return busStopsAndArrivals;
    }

    public String getPostcode() {
        return postcode;
    }

}
