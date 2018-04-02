package model.filter;

import model.publication.Publication;
import model.vehicleType.VehicleType;

import java.util.List;
import java.util.stream.Collectors;

public class ByCategory extends QuestFilter{

    private VehicleType vehicleType;

    @Override
    public List<Publication> filterAndOrder(List<Publication> anyPublications) {
        List publications = anyPublications.stream()
                .filter(p-> p.getPublishedVehicle().itsType(this.vehicleType))
                .collect(Collectors.toList());

        return this.order.order(publications);
    }
}
