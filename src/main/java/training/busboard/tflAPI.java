package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class tflAPI {
    private static String stopPointURL = "https://api.tfl.gov.uk/StopPoint";
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    private static <T extends Object> T call(GenericType<T> type, String method) {
        T response = client.target(stopPointURL+method)
                .request(MediaType.APPLICATION_JSON)
                .get(type);
        return response;
    }

    public static List<ArrivalPrediction> getArrivalPredictionsByStopPointId(String id){
        return call(new GenericType<List<ArrivalPrediction>>(){}, "/"+id+"/Arrivals");
    }
}
