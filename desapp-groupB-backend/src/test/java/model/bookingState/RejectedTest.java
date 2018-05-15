package model.bookingState;

import junit.framework.TestCase;
import model.bookingstate.*;

import org.junit.Before;

public class RejectedTest extends TestCase {

    private Rejected anyRejected;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.anyRejected = new Rejected();
	}

    public void testSetAcepted(){
        assertTrue(anyRejected.setAcepted() instanceof Approved);
    }

    public void testSetRejected(){
        assertTrue(anyRejected.setRejected() instanceof Rejected);
    }
    
    public void testIsApproved(){
        assertFalse(anyRejected.isApproved());
    }
}
