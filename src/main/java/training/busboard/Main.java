package training.busboard;


import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        Scanner myObj = new Scanner(System.in);
        System.out.println("Please input the bus-stop code");
        String busStopCode = myObj.nextLine();

        List<ArrivalPrediction> response = client.target("https://api.tfl.gov.uk/StopPoint/"+busStopCode+"/Arrivals")
            .request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<ArrivalPrediction>>() {});

        List<ArrivalPrediction> sortedResponse = response.stream().sorted(
                (t1, t2) -> t1.expectedArrival.compareTo(t2.expectedArrival)
        ).limit(5).collect(Collectors.toList());
        for (ArrivalPrediction bus: sortedResponse){
            System.out.println(bus.vehicleId+ " "+ bus.expectedArrival);
        }
    }

}	
