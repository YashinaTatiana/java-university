package dto;

import java.io.Serializable;

public class UserResponseDto implements Serializable {

    String response;
    String error;

    public UserResponseDto(){
        this("", "");
    }

    public UserResponseDto(String response) {
        this(response, "");
    }

    public UserResponseDto(String response, String error) {
        setResponse(response);
        setError(error);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response)  {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
