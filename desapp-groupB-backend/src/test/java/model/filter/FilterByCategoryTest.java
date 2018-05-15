package model.filter;

import junit.framework.TestCase;
import model.builders.FilterByCategoryBuilder;
import model.order.Order;
import model.publication.Publication;
import model.vehicle.Vehicle;
import model.vehicletype.Category;
import org.junit.Before;

import java.util.*;

import static org.mockito.Mockito.*;

public class FilterByCategoryTest extends TestCase {

	private Order orderMock;
    private Vehicle vehicleMock;
    private Category categoryMock;
    private Publication anyPublicationMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();

        this.orderMock = mock(Order.class);
        this.vehicleMock = mock(Vehicle.class);
        this.categoryMock = mock(Category.class);
        this.anyPublicationMock = mock(Publication.class);
	}

    public void testFilterAndOrder(){
        List<Publication> publications = spy(new ArrayList<Publication>());
        publications.add(anyPublicationMock);

        FilterByCategory filterByCategory = new FilterByCategory(orderMock, categoryMock);

        when(anyPublicationMock.getPublishedVehicle()).thenReturn(vehicleMock);
        when(vehicleMock.itsCategory(categoryMock)).thenReturn(true);

        filterByCategory.filterAndOrder(publications);

        verify(orderMock).order(any());
        verify(publications).stream();
        verify(vehicleMock).itsCategory(any());
    }
}
