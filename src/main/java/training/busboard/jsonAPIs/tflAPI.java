package training.busboard.jsonAPIs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class tflAPI {
    private static String endpointURL = "https://api.tfl.gov.uk/StopPoint";
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    private static <T extends Object> T call(GenericType<T> type, String method) {
        T response = client.target(endpointURL+method)
                .request(MediaType.APPLICATION_JSON)
                .get(type);
        return response;
    }

    public static List<ArrivalPrediction> getArrivalPredictionsByStopPointId(String id){
        return call(new GenericType<List<ArrivalPrediction>>(){}, String.format("/%s/Arrivals?app_key=%s", id, System.getenv("TFL_API_KEY")));
    }

    //https://api.tfl.gov.uk/StopPoint/?lat={lat}&lon={lon}&stopTypes={stopTypes}[&radius][&useStopPointHierarchy][&modes][&categories][&returnLines]

    public static List<StopPointByLonAndLat> getStopPointsByLonAndLat(Double longitude, Double latitude){
        return call(new GenericType<StopPointByLonAndLatWrapper>(){},
                String.format("/?lat=%f&lon=%f&stopTypes=NaptanPublicBusCoachTram&app_key=%s",
                        latitude,longitude,System.getenv("TFL_API_KEY")))
                .stopPoints;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StopPointByLonAndLatWrapper {
        List<StopPointByLonAndLat> stopPoints;

        public List<StopPointByLonAndLat> getStopPoints() {
            return stopPoints;
        }

        public void setStopPoints(List<StopPointByLonAndLat> stopPoints) {
            this.stopPoints = stopPoints;
        }
    }


}
