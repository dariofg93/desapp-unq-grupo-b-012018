package model.filter;

import model.publication.Publication;
import model.vehicleType.Category;

import java.util.List;
import java.util.stream.Collectors;

public class ByCategory extends QuestFilter{

    private Category category;

    @Override
    public List<Publication> filterAndOrder(List<Publication> anyPublications) {
        List publications = anyPublications.stream()
                .filter(p-> p.getPublishedVehicle().itsCategory(this.category))
                .collect(Collectors.toList());

        return this.order.order(publications);
    }

    /** Setters and Getters **/

    public void setCategory(Category category) {
        this.category = category;
    }
}
