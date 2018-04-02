package model.builders;

import model.creditsAccount.CreditsAccount;
import model.publication.Publication;
import model.score.ScoreManager;
import model.user.User;
import model.website.WebSite;

import java.util.List;

public class UserBuilder {

    private User buildUser;

    public UserBuilder createUser() {
        this.buildUser = new User();
        return this;
    }

    public User build() {
        return this.buildUser;
    }

    public UserBuilder withWebSite(WebSite anyWebSite) {
        this.buildUser.setWebSite(anyWebSite);
        anyWebSite.addUSer(buildUser);
        return this;
    }

    public UserBuilder withPublications(List<Publication> anyPublications) {
        this.buildUser.setMyPublications(anyPublications);
        return this;
    }

    public UserBuilder withScoreManager(ScoreManager anyScoreManager) {
        buildUser.setScoreManager(anyScoreManager);
        return this;
    }

    public UserBuilder withCreditsAccount(CreditsAccount anyCreditsAccountMock) {
        buildUser.setCreditsAccount(anyCreditsAccountMock);
        return this;
    }
}
