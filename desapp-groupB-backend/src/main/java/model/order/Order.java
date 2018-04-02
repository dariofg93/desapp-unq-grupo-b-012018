package model.order;

import model.orientation.OrderOrientation;
import model.publication.Publication;

import java.util.List;

public abstract class Order {
    protected OrderOrientation orientation;

    public abstract List<Publication> order(List<Publication> anyPublications);
}
