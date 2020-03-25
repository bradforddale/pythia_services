package pythia.za.servcies.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class DataRequest<T> {
    private String operation;
    private T requestObj;

    public DataRequest(String operation, T requestObj) {
        this.operation = operation;
        this.requestObj = requestObj;
    }

    public String getOperation() {
        return operation;
    }


    public T getRequestObj() {
        return requestObj;
    }

    @Override
    public String toString() {
        return  this.toJSON();
    }

    private String toJSON() {
        return "{" +
                "\"operation\":\"" + operation + '\"' +
                ", \"requestObj\":" + (requestObj instanceof String ? "\"" + requestObj + "\"" : requestObj)  +
                '}';
    }
}