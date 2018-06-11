package dummies;

import model.builders.UserBuilder;
import model.creditsaccount.CreditsAccount;
import model.email.Email;
import model.publication.Publication;
import model.score.OwnerScoreType;
import model.score.Score;
import model.score.ScoreManager;
import model.user.User;
import model.website.WebSite;
import service.user.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersDummy implements DummyData{

    private List<User> users = new ArrayList<>();
    private UserBuilder builder = new UserBuilder();
    private UserService service;

    public UsersDummy(){
        User user1 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score(new OwnerScoreType())))))
                .withCreditsAccount(new CreditsAccount())
                .withEmail(new Email("dariofg93@gmail.com"))
                .build();
        user1.getMovementsOfMonth().addToHistory("Hoy alquile");
        user1.getMovementsOfMonth().addToHistory("Hoy alquile otro auto");
        this.users.add(user1);

        User user2 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score(new OwnerScoreType())))))
                .withCreditsAccount(new CreditsAccount())
                .withEmail(new Email("fabri1108@gmail.com"))
                .build();
        this.users.add(user2);
        CreditsAccount account = new CreditsAccount();
        account.addCredits(1000.0);
        User user3 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score(new OwnerScoreType())))))
                .withCreditsAccount(account)
                .withEmail(new Email("otroUsuarioQueNoExiste@gmail.com"))
                .build();
        user1.getMovementsOfMonth().addToHistory("Hoy alquile pero era muy feo");
        user1.getMovementsOfMonth().addToHistory("Hoy alquile otro auto y era mas feo q el anterior");
        this.users.add(user3);
    }

    public void setUserBuilder(UserBuilder userBuilder) { this.builder= userBuilder; }
    public UserBuilder getUserBuilder() { return this.builder; }

    public void setUsers(List<User> users) { this.users = users; }
    public List<User> getUsers() { return this.users; }

    public void setService(UserService service) {
        this.service = service;
    }
    public UserService getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.users.forEach(
                (User user) -> this.service.save(user)
        );
    }
}
