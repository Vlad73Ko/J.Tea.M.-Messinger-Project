package task_Server_development.server;

import java.util.List;

public class AddUserToList {

    void add (UserOnServer user, List<UserOnServer> list ) {
        list.add(list.size()+1, user);

    }

}
