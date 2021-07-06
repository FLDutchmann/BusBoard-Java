package training.busboard;


import org.glassfish.jersey.jackson.JacksonFeature;

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

        List<StopPointByLonAndLat> response = tflAPI.getStopPointsByLonAndLat(postcodeInfo.longitude, postcodeInfo.latitude);

        if(response.isEmpty()){
            System.out.println("No London bus stops found near your postcode");
            return;
        }

        response.stream().limit(2).forEach( (stopPoint) -> {
            System.out.println("Expected arrivals at stop " + stopPoint.commonName + ":");
            List<ArrivalPrediction> arrivals = tflAPI.getArrivalPredictionsByStopPointId(stopPoint.naptanId);
            arrivals.stream().limit(5).forEach((prediction) -> {
                System.out.println("Line " + prediction.lineName + " bus towards " +
                                   prediction.towards + " will arrive at " + prediction.expectedArrival);
            });
        });

    }
}	
