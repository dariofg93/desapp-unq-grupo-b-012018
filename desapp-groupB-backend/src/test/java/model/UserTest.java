package model;

import junit.framework.TestCase;
import model.builders.UserBuilder;
import model.publication.Publication;
import model.user.User;
import model.website.WebSite;
import org.junit.*;
import static org.mockito.Mockito.*;

public class UserTest extends TestCase {

    private UserBuilder userBuilder;
    private WebSite carpnd;
    private Publication anyPublication;

    @Before
	public void setUp() {
        this.userBuilder = new UserBuilder();
        this.carpnd = mock(WebSite.class);
        this.anyPublication = mock(Publication.class);
	}

    public void testPublish(){
	    User anyUser = userBuilder.createUser()
                .withWebSite(carpnd)
                .build();

        anyUser.publish(anyPublication);

        assertEquals(anyUser.numberOfPublications(),1,0);
        verify(carpnd).addPublication(anyPublication);
    }
}
