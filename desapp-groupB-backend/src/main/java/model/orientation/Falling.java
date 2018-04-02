package model.orientation;

import model.publication.Publication;

public class Falling extends OrderOrientation{

    @Override
    public Integer comparePrices(Publication p1, Publication p2) {
        return p2.getPricePerHour() > p1.getPricePerHour()? 1: p2.getPricePerHour().equals(p1.getPricePerHour())? 0: -1;
    }
}
