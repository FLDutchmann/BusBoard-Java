package training.busboard;


import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    public static void main(String args[]) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Please input the postcode");
        String postcode = myObj.nextLine();

        PostcodeInfo postcodeInfo = PostcodeAPI.getPostcodeInfo(postcode);
        System.out.println("Longitude: " + postcodeInfo.longitude);
        System.out.println("Latitude: " + postcodeInfo.latitude);
        System.out.println("Country: " + postcodeInfo.country);

        List<ArrivalPrediction> response = tflAPI.getStopPointsByLonAndLat(postcodeInfo.longitude, postcodeInfo.latitude);

        List<ArrivalPrediction> sortedResponse = response.stream().sorted(
                (t1, t2) -> t1.expectedArrival.compareTo(t2.expectedArrival)
        ).limit(5).collect(Collectors.toList());
        for (ArrivalPrediction bus: sortedResponse) {
            System.out.println(bus.vehicleId + " " + bus.expectedArrival);
        }
*/
    }

}	
