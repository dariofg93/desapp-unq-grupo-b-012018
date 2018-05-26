package model.website;

import model.notifier.Notifier;
import model.publication.Publication;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class WebSite {

    private List<User> users;
    private Notifier notifier;

    public WebSite(){
        this.users = new ArrayList<>();
    }

    public void addUSer(User anyUser) {
        this.users.add(anyUser);
    }

    /** Setters and Getters **/

    public Notifier getNotifier() {
        return this.notifier;
    }

    public List<Publication> publications() {
        return users.stream()
                .map(User::getMyPublications)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }

    public void sendMovementsOfTheMonth(){
        this.getNotifier().sendMovementsOfTheMonthToUsers(this.users);
    }
}
