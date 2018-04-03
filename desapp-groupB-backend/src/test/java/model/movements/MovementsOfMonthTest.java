package model.movements;

import junit.framework.TestCase;
import org.junit.Before;

public class MovementsOfMonthTest extends TestCase {

    private MovementsOfMonth movements;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.movements = new MovementsOfMonth();
	}

    public void testFilterAndOrder(){
        movements.cleanHistory();

        assertEquals(movements.getHistory(),"");
    }
}
