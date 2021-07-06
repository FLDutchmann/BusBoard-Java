package training.busboard.web;

import training.busboard.jsonAPIs.*;

import java.util.List;
import java.util.stream.Collectors;

public class BusInfo {
    private final String postcode;
    private List<BusStopAndArrivals> busStopsAndArrivals;

    public BusInfo(String postcode) {
        this.postcode = postcode;

        PostcodeInfo postcodeInfo = PostcodeAPI.getPostcodeInfo(postcode);

        List<StopPointByLonAndLat> busStops = tflAPI.getStopPointsByLonAndLat(
                postcodeInfo.getLongitude(),
                postcodeInfo.getLatitude()
        );

        busStopsAndArrivals = TransportAPI.getNextArrivalsAtBusStopsNearPostcode(postcode);
    }

    public List<BusStopAndArrivals> getBusStopsAndArrivals() {
        return busStopsAndArrivals;
    }

    public String getPostcode() {
        return postcode;
    }

}
