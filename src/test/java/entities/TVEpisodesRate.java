package entities;

public class TVEpisodesRate {
    private String status_code;
    private String status_message;

    public TVEpisodesRate(String status_code, String status_message)
    {
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public String getStatus_code() {return status_code;}
    public String getStatus_message() {return status_message;}
}
