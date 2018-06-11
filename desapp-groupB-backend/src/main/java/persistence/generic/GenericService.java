package persistence.generic;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

public class GenericService<T> implements Serializable {

    private static final long serialVersionUID = -6540963495078524186L;

    private GenericRepository<T> repository;

    public GenericRepository<T> getRepository() {
        return this.repository;
    }

    public void setRepository(final GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public void save(final T object) {
        this.getRepository().save(object);
    }

    @Transactional
    public void update(final T object) {
        this.getRepository().update(object);
    }
    
	@Transactional(readOnly = true)
	public void saveOrUpdate(final T object) {
	    	this.getRepository().saveOrUpdate(object);
	    }
	
	@Transactional(readOnly = true)
	public List<T> selectByFunction(Function<T, Boolean> function) {
	    	return this.retriveAll().stream().filter((object) -> function.apply(object)).collect(Collectors.toList());
	    }
	
	@Transactional(readOnly = true)
	public List<T> execute(String queryString) {
	    	return this.getRepository().execute(queryString);
	    }

}