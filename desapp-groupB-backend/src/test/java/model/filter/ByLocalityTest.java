package model.filter;

import junit.framework.TestCase;
import model.builders.ByLocalityBuilder;
import model.locality.Locality;
import model.order.Order;
import model.publication.Publication;
import org.junit.Before;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ByLocalityTest extends TestCase {

    private ByLocalityBuilder byLocalityBuilder;

    private Order orderMock;
    private Locality localityMock;
    private Publication anyPublicationMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.byLocalityBuilder = new ByLocalityBuilder();

        this.orderMock = mock(Order.class);
        this.localityMock = mock(Locality.class);
        this.anyPublicationMock = mock(Publication.class);
	}

    public void testFilterAndOrder(){
        List<Publication> publications = Collections.singletonList(anyPublicationMock);

        ByLocality filterByLocality = byLocalityBuilder.createFilterByLocality()
                .withOrder(orderMock)
                .withLocality(localityMock)
                .build();

        when(anyPublicationMock.getLocality()).thenReturn(localityMock);
        when(localityMock.isSame(localityMock)).thenReturn(true);

        filterByLocality.filterAndOrder(publications);

        //No hay control sobre la lista que le llega al order, no se puede verificar nada.
    }
}
