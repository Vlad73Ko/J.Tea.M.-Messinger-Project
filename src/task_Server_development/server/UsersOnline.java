package task_Server_development.server;

import java.util.List;

public class UsersOnline {

    void check (List<UserOnServer> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isOnLine()) System.out.println(list.get(i).toString());

        }

    }

}
