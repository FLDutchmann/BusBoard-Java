package training.busboard.console;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.jsonAPIs.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    public static void main(String args[]) {


        Scanner myObj = new Scanner(System.in);
        System.out.println("Please input the postcode");
        String postcode = myObj.nextLine();

        PostcodeInfo postcodeInfo = PostcodeAPI.getPostcodeInfo(postcode);

        List<StopPointByLonAndLat> response = tflAPI.getStopPointsByLonAndLat(postcodeInfo.getLongitude(), postcodeInfo.getLatitude());

        if(response.isEmpty()){
            System.out.println("No London bus stops found near your postcode");
            return;
        }

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
