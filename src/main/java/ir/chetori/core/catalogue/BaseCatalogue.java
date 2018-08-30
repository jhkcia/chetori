package ir.chetori.core.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.core.BaseEntityDAO;
import ir.chetori.core.api.PageList;
import ir.chetori.core.entity.BaseEntity;

public class BaseCatalogue<T extends BaseEntity> {

	@Autowired
	BaseEntityDAO<T> dao;

	public long count() {
		return dao.count();
	}

	public void add(T entity) throws IllegalArgumentException, IllegalAccessException {
		dao.add(entity);
	}

	public T getById(String id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return dao.getById(id);
	}

	public List<T> getAll()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return dao.getAll();
	}

	public void update(T entity) throws IllegalArgumentException, IllegalAccessException {
		dao.update(entity);
	}

	public void delete(String id) {
		dao.delete(id);
	}

	public void clear() {
		dao.clear();
	}

	public PageList<T> getPage(int pageNumber, int pageSize)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return dao.getPage(pageNumber, pageSize);
	}
}
