package model.utils;

import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = -528544744688654622L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		Entity entity = (Entity) o;
		return (super.equals(o) && (entity.getId() == this.getId()));

	}

	@Override

	public int hashCode() {
		return super.hashCode() + this.id.hashCode();
	}
}