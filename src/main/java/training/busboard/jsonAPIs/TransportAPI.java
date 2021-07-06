package training.busboard.jsonAPIs;

import java.util.List;
import java.util.stream.Collectors;

public class TransportAPI {
    public static List<BusStopAndArrivals> getNextArrivalsAtBusStopsNearPostcode(String postcode){
        PostcodeInfo postcodeInfo = PostcodeAPI.getPostcodeInfo(postcode);

        List<StopPointByLonAndLat> busStops = tflAPI.getStopPointsByLonAndLat(
                postcodeInfo.getLongitude(),
                postcodeInfo.getLatitude()
        );

        return busStops
                .stream()
                .limit(2)
                .map((stop) -> {
                    List<ArrivalPrediction> arrivals = tflAPI.getArrivalPredictionsByStopPointId(stop.getNaptanId())
                            .stream()
                            .sorted((t1, t2) -> t1.expectedArrival.compareTo(t2.expectedArrival))
                            .limit(5)
                            .collect(Collectors.toList());
                    return new BusStopAndArrivals(stop, arrivals);
                })
                .collect(Collectors.toList());
    }
}
