package model.builders;

import model.user.User;
import model.website.WebSite;

public class UserBuilder {

    private User buildUser;

    public UserBuilder createUser() {
        this.buildUser = new User();
        return this;
    }

    public User build() {
        return buildUser;
    }

    public UserBuilder withWebSite(WebSite anyWebSite) {
        this.buildUser.setWebSite(anyWebSite);
        return this;
    }
}
