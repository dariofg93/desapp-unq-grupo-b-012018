package model.website;

import model.publication.Publication;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class WebSite {

    private List<User> users;
    private List<Publication> publications;

    public WebSite(){
        users = new ArrayList<User>();
        publications = new ArrayList<Publication>();
    }

    public void addPublication(Publication anyPublication) {
        this.publications.add(anyPublication);
    }
}
