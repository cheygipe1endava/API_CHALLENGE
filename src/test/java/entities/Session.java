package entities;

public class Session {

    private String success;
    private String request_token;
    private String session_id;
    private String guest_session_id;
    private String delete_session_id;



    public Session(String success, String request_token, String session_id, String guest_session_id,
                   String delete_session_id) {
        this.success = success;
        this.request_token = request_token;
        this.session_id = session_id;
        this.guest_session_id = guest_session_id;
        this.delete_session_id = delete_session_id;
    }

    public String getSuccess() {
        return success;
    }
    public String getRequest_token() {
        return request_token;
    }
    public String getSession_id() {
        return session_id;
    }
    public String getGuest_session_id() {return guest_session_id;}
    public String getDelete_session_id() {return delete_session_id;}
    //public void setDelete_session_id(String delete_session_id) {this.delete_session_id = delete_session_id;}



}