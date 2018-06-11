package persistence.bookingrequest;


import java.util.List;

import model.booking.BookingRequest;
import persistence.generic.GenericRepository;
import persistence.generic.HibernateGenericDAO;

public class BookingRequestRepository extends HibernateGenericDAO<BookingRequest> implements GenericRepository<BookingRequest> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<BookingRequest> getDomainClass() {
        return BookingRequest.class;
    }
	@Override
	public List<BookingRequest> execute(String stringQuery) {
		return null;
	}

}
