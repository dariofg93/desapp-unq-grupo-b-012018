package model.filter;

import junit.framework.TestCase;
import model.builders.ByCategoryBuilder;
import model.order.Order;
import model.publication.Publication;
import model.vehicle.Vehicle;
import model.vehicleType.Category;
import org.junit.Before;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ByCategoryTest extends TestCase {

    private ByCategoryBuilder byCategoryBuilder;

    private Order orderMock;
    private Vehicle vehicleMock;
    private Category categoryMock;
    private Publication anyPublicationMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.byCategoryBuilder = new ByCategoryBuilder();

        this.orderMock = mock(Order.class);
        this.vehicleMock = mock(Vehicle.class);
        this.categoryMock = mock(Category.class);
        this.anyPublicationMock = mock(Publication.class);
	}

    public void testFilterAndOrder(){
        List<Publication> publications = Collections.singletonList(anyPublicationMock);

        ByCategory filterByCategory = byCategoryBuilder.createFilterByCategory()
                .withOrder(orderMock)
                .withCategory(categoryMock)
                .build();

        when(anyPublicationMock.getPublishedVehicle()).thenReturn(vehicleMock);
        when(vehicleMock.itsCategory(categoryMock)).thenReturn(true);

        filterByCategory.filterAndOrder(publications);

        //No hay control sobre la lista que le llega al order, no se puede verificar nada.
    }
}
