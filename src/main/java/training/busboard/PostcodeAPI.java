package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PostcodeAPI {
    private static String endpointURL = "https://api.postcodes.io/postcodes";
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    private static <T extends Object> T call(GenericType<T> type, String method) {
        T response = client.target(endpointURL+method)
                .request(MediaType.APPLICATION_JSON)
                .get(type);
        return response;
    }

    public static PostcodeInfo getPostcodeInfo(String postcode) {
        return call(new GenericType<ResultWrapper<PostcodeInfo>>(){}, "/"+postcode).result;
    }
}
