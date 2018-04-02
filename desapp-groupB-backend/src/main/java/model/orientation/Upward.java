package model.orientation;

import model.publication.Publication;

public class Upward extends OrderOrientation{

    @Override
    public Integer comparePrices(Publication p1, Publication p2) {
        return p1.getPricePerHour() > p2.getPricePerHour()? 1: p1.getPricePerHour().equals(p2.getPricePerHour())? 0: -1;
    }
}
