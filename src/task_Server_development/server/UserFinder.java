package task_Server_development.server;

import java.util.List;

public class UserFinder {

    int userIndex (UserOnServer user, List<UserOnServer> list) {
        for (int i = 0; i < list.size() ; i++) {

            if (list.get(i).userName.equals(user.userName) && (list.get(i).userPassword.equals(user.userPassword))) {
                return i;
            }
        }
        return -1;
    }

}
