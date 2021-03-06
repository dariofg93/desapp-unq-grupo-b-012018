package model.builders;

import model.creditsaccount.CreditsAccount;
import model.email.Email;
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

    public UserBuilder withEmail(Email anyEmail) {
        buildUser.setEmail(anyEmail);
        return this;
    }

    public UserBuilder withFirstName(String anyFirstName) {
        buildUser.setFirstName(anyFirstName);
        return this;
    }

    public UserBuilder withLastName(String anyLastName) {
        buildUser.setLastName(anyLastName);
        return this;
    }

    public UserBuilder withCuil(String anyCuil) {
        buildUser.setCuil(anyCuil);
        return this;
    }


}
