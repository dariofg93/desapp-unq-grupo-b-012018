package model.filter;

import model.city.City;
import model.publication.Publication;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByCity extends QuestFilter {

    private City city;

    @Override
    public List<Publication> filterAndOrder(List<Publication> anyPublications) {
    	 List<Publication> publications = anyPublications.stream()
                .filter(p-> p.getCity().isSame(this.city))
                .collect(Collectors.toList());

        return this.order.order(publications);
    }

    /** Setters and Getters **/

    public void setCity(City city) {
        this.city = city;
    }
}
