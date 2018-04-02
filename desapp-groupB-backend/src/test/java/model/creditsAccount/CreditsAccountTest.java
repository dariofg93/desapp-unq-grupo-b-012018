package model.creditsAccount;

import junit.framework.TestCase;
import model.exceptions.NotEnoughCreditsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class CreditsAccountTest extends TestCase {

    @Rule
    private ExpectedException thrown= ExpectedException.none();

    private CreditsAccount creditsAccount;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.creditsAccount = new CreditsAccount();
	}

    public void testAddCredits(){
        creditsAccount.addCredits(50.0);
        assertEquals(creditsAccount.getAmount(), 50.0);
    }

    public void testSustractCreditsWithSuficientCredits() throws NotEnoughCreditsException {
        creditsAccount.addCredits(50.0);
        creditsAccount.sustractCredits(30.0);
        assertEquals(creditsAccount.getAmount(), 20.0);
    }
/*
    public void testSustractCreditsWithOutSuficientCredits() throws NotEnoughCreditsException {
        creditsAccount.addCredits(50.0);

        thrown.expect(NotEnoughCreditsException.class);
        creditsAccount.sustractCredits(80.0);
    }
*/
}
