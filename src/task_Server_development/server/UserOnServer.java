package task_Server_development.server;

public class UserOnServer {

    final String userName;
    final String userPassword;
    private int userID;
    private boolean isOnLine;

    public UserOnServer(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
       // this.userID = userID;
    }

    void addToUsersList() {

    }

    void logIn() {
        isOnLine = true;
    }

    void  logOut() {
        isOnLine = false;
    }

    public boolean isOnLine() {
        return isOnLine;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
