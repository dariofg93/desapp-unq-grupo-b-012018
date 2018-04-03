package model.notifier;

import junit.framework.TestCase;
import model.booking.BookingRequest;
import model.user.User;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class NotifierTest extends TestCase {

    private Notifier notifier;

    private User anyUserMock;
    private BookingRequest anyRequestMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.notifier = new Notifier();

        this.anyUserMock = mock(User.class);
        this.anyRequestMock = mock(BookingRequest.class);
	}

    public void testNotifyRequestByMail(){
        //notifier.notifyRequestByMail(anyUserMock,anyRequestMock);

        //assertEquals(notifier.getHistory(),"");
    }
}
