package model.bookingState;

import junit.framework.TestCase;
import model.bookingstate.*;

import org.junit.Before;

public class AwaitingApprobalTest extends TestCase {

    private AwaitingApproval anyAwaitingApprobal;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.anyAwaitingApprobal = new AwaitingApproval();
	}

    public void testSetAcepted(){
        assertTrue(anyAwaitingApprobal.setAcepted() instanceof Approved);
    }

    public void testSetRejected(){
        assertTrue(anyAwaitingApprobal.setRejected() instanceof Rejected);
    }
    
    public void testIsApproved(){
        assertFalse(anyAwaitingApprobal.isApproved());
    }
}
