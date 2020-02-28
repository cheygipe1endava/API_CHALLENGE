package entities;

public class ListRequests {

    private String name;
    private String description;
    private String language;
    private String list_id;
    private String guest_session_id;
    private String delete_session_id;

    public ListRequests(){ }

    public ListRequests(String name, String description, String language,
                           String list_id, String guest_session_id, String delete_session_id) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.list_id = list_id;
        this.guest_session_id = guest_session_id;
        this.delete_session_id = delete_session_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description = description;}

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getGuest_session_id() {return guest_session_id;}

    public void setGuest_session_id(String guest_session_id) {this.guest_session_id = guest_session_id;}

    public String getDelete_session_id() {return delete_session_id;}

    public void setDelete_session_id(String delete_session_id) {this.delete_session_id = delete_session_id;}



}
