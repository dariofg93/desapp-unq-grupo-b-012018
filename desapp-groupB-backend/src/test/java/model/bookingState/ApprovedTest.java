package model.bookingState;

import junit.framework.TestCase;
import model.bookingstate.*;

import org.junit.Before;

public class ApprovedTest extends TestCase {

    private Approved anyApproved;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.anyApproved = new Approved();
	}

    public void testSetAcepted(){
        assertTrue(anyApproved.acept() instanceof Approved);
    }

    public void testSetRejected(){
        assertTrue(anyApproved.reject() instanceof Rejected);
    }
//
//    public void testSetAndGetConfirmRetreatBuyer(){
//        anyApproved.setConfirmRetreatBuyer(true);
//        assertTrue(anyApproved.getConfirmRetreatBuyer());
//    }
//
//    public void testSetAndGetConfirmRetreatSeller(){
//        anyApproved.setConfirmRetreatSeller(false);
//        assertFalse(anyApproved.getConfirmRetreatSeller());
//    }
//
//    public void testSetAndGetConfirmReturnSeller(){
//        anyApproved.setConfirmReturnSeller(false);
//        assertFalse(anyApproved.getConfirmReturnSeller());
//    }
//
//    public void testSetAndGetConfirmReturnBuyer(){
//        anyApproved.setConfirmReturnBuyer(true);
//        assertTrue(anyApproved.getConfirmReturnBuyer());
//    }
//    
    public void testIsApproved(){
        assertTrue(anyApproved.isApproved());
    }
}
