package model.filter;

import model.order.Order;
import model.publication.Publication;
import model.vehicleType.Category;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByCategory extends QuestFilter{

    private Category category;

    public FilterByCategory(Order anOrder, Category aCategory) {
    	category = aCategory;
    	order = anOrder;
	}

	public FilterByCategory() {
		// para el builder y la persistencia
	}
	
	@Override
    public List<Publication> filterAndOrder(List<Publication> anyPublications) {
        List<Publication> publications = anyPublications.stream()
                .filter(p-> p.getPublishedVehicle().itsCategory(this.category))
                .collect(Collectors.toList());

        return this.order.order(publications);
    }

    /** Setters and Getters **/

    public void setCategory(Category category) {
        this.category = category;
    }
}
