package model.filter;

import model.locality.Locality;
import model.publication.Publication;

import java.util.List;
import java.util.stream.Collectors;

public class ByLocality extends QuestFilter {

    private Locality locality;

    @Override
    public List<Publication> filterAndOrder(List<Publication> anyPublications) {
        List publications = anyPublications.stream()
                .filter(p-> p.getLocality().isSame(this.locality))
                .collect(Collectors.toList());

        return this.order.order(publications);
    }

    /** Setters and Getters **/

    public void setLocality(Locality locality) {
        this.locality = locality;
    }
}
