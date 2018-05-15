package model.filter;

import junit.framework.TestCase;
import model.builders.FilterByCityBuilder;
import model.city.City;
import model.order.*;
import model.publication.Publication;
import org.junit.Before;


import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


public class FilterByCityTest extends TestCase {

    private FilterByCityBuilder byLocalityBuilder;

    private Order orderMock;
    private City cityMock;
    private Publication anyPublicationMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.byLocalityBuilder = new FilterByCityBuilder();

        this.orderMock = new ByPrice();
        this.cityMock = mock(City.class);
        this.anyPublicationMock = mock(Publication.class);
	}

    @SuppressWarnings("unlikely-arg-type")
	public void testFilterAndOrder(){
        List<Publication> publications = Collections.singletonList(anyPublicationMock);

        FilterByCity filterByLocality = byLocalityBuilder.createFilterByLocality()
                .withOrder(orderMock)
                .withLocality(cityMock)
                .build();

        when(anyPublicationMock.getCity()).thenReturn(cityMock);
        when(cityMock.isSame(cityMock)).thenReturn(true);

        List<Publication> filteredPublications = filterByLocality.filterAndOrder(publications);
        
	
        assertEquals(filteredPublications.size(), 1);
        assertTrue(filteredPublications.contains(anyPublicationMock));
    }
}
