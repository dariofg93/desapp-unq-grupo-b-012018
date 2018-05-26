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

        assertEquals(movements.getAllHistory(),"");
    }
    
    public void testAddHistoryRecord(){
        movements.cleanHistory();
        movements.addToHistory("un registro");
        assertEquals(movements.getAllHistory(),"un registro\n");
    }
    
    public void testAddSomeHistoryRecord(){
        movements.cleanHistory();
        movements.addToHistory("un registro");
        movements.addToHistory("otro registro");
        assertEquals(movements.getAllHistory(),"un registro\n" + "otro registro\n");
    }
    
    public void testResetMovements(){
        movements.cleanHistory();
        movements.addToHistory("un registro");
        movements.addToHistory("otro registro");
        
        movements.cleanHistory();
        assertEquals(movements.getAllHistory(),"");
    }
}
