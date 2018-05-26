package persistence.publication;

import org.hibernate.Query;

import model.publication.Publication;
import persistence.generic.GenericRepository;
import persistence.generic.HibernateGenericDAO;

public class PublicationRepository extends HibernateGenericDAO<Publication> implements GenericRepository<Publication> {

	    private static final long serialVersionUID = -8543996946304099004L;

	    @Override
	    protected Class<Publication> getDomainClass() {
	        return Publication.class;
	    }
		@Override
		public void execute(String stringQuery) {
			Query query = this.getSessionFactory().getCurrentSession().createQuery(stringQuery);
			query.executeUpdate();
		}


}
