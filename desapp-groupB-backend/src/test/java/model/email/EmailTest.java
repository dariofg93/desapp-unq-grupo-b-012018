package model.email;

import junit.framework.TestCase;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class EmailTest extends TestCase {

    private Email email;
    private MailCarpnd mailCarpndMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.email = new Email();
        this.mailCarpndMock = mock(MailCarpnd.class);
    }

    public void testAddMailCarpnd(){
        assertEquals(email.getReceived().size(),0);

        email.addMailCarpnd(mailCarpndMock);

        assertEquals(email.getReceived().size(),1);
    }
}
