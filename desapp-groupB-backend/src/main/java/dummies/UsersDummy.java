package dummies;

import model.builders.UserBuilder;
import model.creditsaccount.CreditsAccount;
import model.email.Email;
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
        List<Score> scores = new ScoresDummy().getScores();

        scores.add(scores.get(0));
        scores.add(scores.get(1));

        User user1 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score()))))
                .withCreditsAccount(this.createCredistsAccountWith(700.0))
                .withEmail(new Email("dariofg93@gmail.com"))
                .withFirstName("Dario")
                .withLastName("Gutierrez")
                .withCuil("1234567890")
                .build();
        user1.getMovementsOfMonth().addToHistory("Hoy alquile");
        user1.getMovementsOfMonth().addToHistory("Hoy alquile otro auto");
        this.users.add(user1);

        User user2 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score()))))
                .withCreditsAccount(this.createCredistsAccountWith(100.0))
                .withEmail(new Email("fabri1108@gmail.com"))
                .withFirstName("Fabrizio")
                .withLastName("Britez")
                .withCuil("0987654321")
                .build();
        this.users.add(user2);

        User user3 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(10000.0))
                .withEmail(new Email("otroUsuarioQueNoExiste@gmail.com"))
                .withFirstName("Gustavo")
                .withLastName("Cerati")
                .build();
        this.users.add(user3);
        User user4 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(7400.0))
                .withEmail(new Email("unMail@gmail.com"))
                .withFirstName("Homero")
                .withLastName("Thompson")
                .withCuil("35337222")
                .build();
        user4.getMovementsOfMonth().addToHistory("Se esta por vencer su alquiler");
        user4.getMovementsOfMonth().addToHistory("N/A");
        this.users.add(user4);
        User user5 = builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(8400.0))
                .withEmail(new Email("cherly@gmail.com"))
                .withFirstName("Charly")
                .withLastName("Garcia")
                .withCuil("35327222")
                .build();
        this.users.add(user5);
        this.users.add(builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(1480.0))
                .withEmail(new Email("elFlaco@gmail.com"))
                .withFirstName("El Flaco")
                .withLastName("Spinetta")
                .withCuil("35317122")
                .build());
        this.users.add(builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(1000.0))
                .withEmail(new Email("Leon@gmail.com"))
                .withFirstName("Leon")
                .withLastName("Gieco")
                .withCuil("35317212")
                .build());
        this.users.add(builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(1300.0))
                .withEmail(new Email("ValeLinch@gmail.com"))
                .withFirstName("Vale")
                .withLastName("Lynch")
                .withCuil("35317221")
                .build());
        this.users.add(builder.createUser()
                .withWebSite(new WebSite())
                .withPublications(new ArrayList<>())
                .withScoreManager(new ScoreManager(new ArrayList<Score>(Collections.singleton(new Score( )))))
                .withCreditsAccount(this.createCredistsAccountWith(1400.0))
                .withEmail(new Email("fabrizio.a.britez@gmail.com"))
                .withFirstName("Paolo")
                .withLastName("El Rockero")
                .withCuil("35317751")
                .build());
    }

    private CreditsAccount createCredistsAccountWith(Double d) {
    	CreditsAccount account = new CreditsAccount();
        account.addCredits(d);
        return account;
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
