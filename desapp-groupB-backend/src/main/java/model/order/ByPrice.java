package model.order;

import model.publication.Publication;

import java.util.List;
import java.util.stream.Collectors;

public class ByPrice extends Order {

    @Override
    public List<Publication> order(List<Publication> anyPublications) {
        return anyPublications.stream()
            .sorted((Publication p1,Publication p2) -> this.orientation.comparePrices(p1, p2))
            .collect(Collectors.toList());
    }
}
