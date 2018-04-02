package model.filter;

import model.order.Order;
import model.publication.Publication;

import java.util.List;

public abstract class QuestFilter {
    protected Order order;

    public abstract List<Publication> filterAndOrder(List<Publication> anyPublications);

    /** Setters and Getters **/

    public void setOrder(Order order) {
        this.order = order;
    }
}
