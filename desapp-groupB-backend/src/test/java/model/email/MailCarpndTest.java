package model.email;

import junit.framework.TestCase;
import model.builders.MailCarpndBuilder;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class MailCarpndTest extends TestCase {

    private MailCarpnd mailCarpnd;
    private MailBody mailBodyMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.mailBodyMock = mock(MailBody.class);
        this.mailCarpnd = new MailCarpnd();
	}

    public void testSetAndGetSubject(){
        mailCarpnd.setSubject("Test Subject");
        assertEquals(mailCarpnd.getSubject(), "Test Subject");
    }

    public void testSetAndGetBody() {
        mailCarpnd.setBody(mailBodyMock);
        assertEquals(mailCarpnd.getBody(), mailBodyMock);
    }

    public void testBuilder() {
        MailCarpnd anyMailCarpnd = new MailCarpndBuilder().createMail()
                .withSubject("This's a subject")
                .withBody(mailBodyMock)
                .build();

        assertEquals(anyMailCarpnd.getSubject(),"This's a subject");
        assertEquals(anyMailCarpnd.getBody(),mailBodyMock);
    }
}
