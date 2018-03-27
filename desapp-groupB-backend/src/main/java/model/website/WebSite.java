package model.website;

import model.notifier.Notifier;
import model.user.User;

import java.util.List;

public class WebSite {

    private List<User> users;
    private Notifier notifier;

    public void addUSer(User anyUser) {
        this.users.add(anyUser);
    }

    /** Setters and Getters **/

    public Notifier getNotifier() {
        return this.notifier;
    }
}
