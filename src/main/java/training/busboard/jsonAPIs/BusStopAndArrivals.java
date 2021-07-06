package training.busboard.jsonAPIs;

import java.util.List;

public class BusStopAndArrivals {
    StopPointByLonAndLat busStop;
    List<ArrivalPrediction> arrivals;

    public StopPointByLonAndLat getBusStop() {
        return busStop;
    }

    public List<ArrivalPrediction> getArrivals() {
        return arrivals;
    }

    public BusStopAndArrivals(StopPointByLonAndLat busStop, List<ArrivalPrediction> arrivals) {
        this.busStop = busStop;
        this.arrivals = arrivals;
    }
}
