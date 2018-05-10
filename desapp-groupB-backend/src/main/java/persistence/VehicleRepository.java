package persistence;

import org.hibernate.Query;

import model.vehicle.Vehicle;

public class VehicleRepository extends HibernateGenericDAO<Vehicle> implements GenericRepository<Vehicle> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }

	@Override
	public void execute(String stringQuery) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.executeUpdate();
	}
    
    

}
