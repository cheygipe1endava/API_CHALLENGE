package entities;

public class ListRequests {

    private String list_id;
    private String status_code;
    private String success;

    //public ListRequests(){ }

    public ListRequests(String list_id, String status_code, String success)
    {
        this.list_id = list_id;
        this.status_code = status_code;
        this.success = success;
    }

    public String getList_id() {return list_id;}
    public String getStatus_code() {return status_code;}
    public String getSuccess() {return success;}
}