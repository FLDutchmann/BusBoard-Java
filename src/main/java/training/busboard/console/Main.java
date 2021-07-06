package training.busboard.console;

import training.busboard.jsonAPIs.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please input the postcode");
        String postcode = myObj.nextLine();

        List<BusStopAndArrivals> busStopsAndArrivals = TransportAPI.getNextArrivalsAtBusStopsNearPostcode(postcode);
        for(BusStopAndArrivals busStopAndArrivals: busStopsAndArrivals) {
            StopPointByLonAndLat stopPoint = busStopAndArrivals.getBusStop();
            System.out.println("Expected arrivals at stop " + stopPoint.commonName + ":");
            for(ArrivalPrediction prediction: busStopAndArrivals.getArrivals()) {
                System.out.println("Line " + prediction.lineName + " bus towards " +
                        prediction.towards + " will arrive at " + prediction.expectedArrival);
            }
        }

    }
}	
