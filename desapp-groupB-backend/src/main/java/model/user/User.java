package model.user;

import model.publication.Publication;
import model.website.WebSite;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Publication> myPublications;
    private WebSite webSite;

    public User(){
        this.myPublications = new ArrayList<Publication>();
        this.webSite = new WebSite();
    }

    public void publish(Publication anyPublication) {
        this.webSite.addPublication(anyPublication);
        this.myPublications.add(anyPublication);
    }

    public Integer numberOfPublications() {
        return this.myPublications.size();
    }

    public void setWebSite(WebSite anyWebSite) {
        this.webSite = anyWebSite;
    }
}
