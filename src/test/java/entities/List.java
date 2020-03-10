package entities;

public class List {

    private String list_id;
    private String status_code;
    private String success;
    private String created_by;
    private String item_present;



    public List(String list_id, String status_code, String success, String created_by, String item_present)
    {
        this.list_id = list_id;
        this.status_code = status_code;
        this.success = success;
        this.created_by = created_by;
        this.item_present = item_present;
    }

    public String getList_id() {return list_id;}
    public String getStatus_code() {return status_code;}
    public String getSuccess() {return success;}
    public String getCreated_by() {return created_by;}
    public String getItem_present() {return item_present;}
}