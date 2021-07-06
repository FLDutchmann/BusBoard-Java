package training.busboard.jsonAPIs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWrapper<T>{
    public String getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String status;
    public T result;
}
