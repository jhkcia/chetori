package ir.chetori.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import ir.chetori.core.api.PageList;
import ir.chetori.core.entity.BaseEntity;

public abstract class BaseEntityDAO<T extends BaseEntity> {
	private final Class<T> clazz;

	public BaseEntityDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void clear() {
		getCollection().drop();
	}

	/*
	 * public Document convert(T entity) throws IllegalArgumentException,
	 * IllegalAccessException { Document document = new Document(); for (Field field
	 * : entity.getClass().getDeclaredFields()) { if
	 * (field.isAnnotationPresent(EntityField.class)) { String key =
	 * field.getName(); field.setAccessible(true); Object value = field.get(entity);
	 * document.append(key, value); } } document.append("_id", entity.getId());
	 * return document; }
	 * 
	 * public T convert(Document document) throws InstantiationException,
	 * IllegalAccessException, NoSuchFieldException, SecurityException { if
	 * (document == null) return null; T t = clazz.newInstance(); document.keySet();
	 * for (String key : document.keySet()) { if ("_id".equals(key)) {
	 * t.setId(document.getString("_id")); } else { Field declaredField =
	 * clazz.getDeclaredField(key); boolean accessible =
	 * declaredField.isAccessible();
	 * 
	 * declaredField.setAccessible(true); declaredField.set(t, document.get(key));
	 * declaredField.setAccessible(accessible);
	 * 
	 * } } return t; }
	 */
	public String getCollectionName() {
		return getCollection().getName();
	}

	// private static MongoDatabase mongoDb = new MongoClient("localhost",
	// 27017).getDatabase("testdb");

	private static Datastore dataStore = null;

	protected static Datastore getDataStore() {
		if (dataStore == null) {
			final Morphia morphia = new Morphia();

			// tell Morphia where to find your classes
			// can be called multiple times with different packages or classes
			morphia.mapPackage("ir.chetori.article.model");

			// create the Datastore connecting to the default port on the local host
			dataStore = morphia.createDatastore(new MongoClient("localhost", 27017), "testdb");
			dataStore.ensureIndexes();
		}
		return dataStore;
	}

	public void add(T entity) throws IllegalArgumentException, IllegalAccessException {
		if (entity.getId() == null)
			entity.setId(generateNewId());
		getDataStore().save(entity);
		// Document document = convert(entity);
		// getCollection().insertOne(document);
	}

	private String generateNewId() {
		return UUID.randomUUID().toString();
	}

	protected DBCollection getCollection() {
		return getDataStore().getCollection(clazz);
	}

	public T getById(String value) {
		return getByFieldValue("_id", value).get(0);
	}

	public List<T> getByFieldValues(HashMap<String, Object> fieldValues) {
		final Query<T> query = getDataStore().createQuery(clazz);
		for (Entry<String, Object> t : fieldValues.entrySet()) {
			query.field(t.getKey()).equal(t.getValue());
		}
		return query.asList();
	}

	public List<T> getByFieldValue(String fieldName, Object value) {
		final Query<T> query = getDataStore().createQuery(clazz).field(fieldName).equal(value);
		return query.asList();

		/*
		 * BasicDBObject searchQuery = new BasicDBObject(); searchQuery.put(fieldName,
		 * value); Document item = getCollection().find(searchQuery).first(); return
		 * convert(item);
		 */
	}

	public void update(T item) throws IllegalArgumentException, IllegalAccessException {
		delete(item);
		add(item);
	}

	public void delete(String id) {
		delete(getById(id));
	}

	public void delete(T item) {
		getDataStore().delete(item);
	}

	public PageList<T> getPage(int pageNumber, int pageSize)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

		final Query<T> query = getDataStore().createQuery(clazz).offset((pageNumber - 1) * pageSize).limit(pageSize);
		List<T> outList = query.asList();
		return new PageList<T>(count(), outList);
	}

	public List<T> getAll() {

		final Query<T> query = getDataStore().createQuery(clazz);
		return query.asList();

		// return getCollection().find(new Document(), clazz).into(new ArrayList<T>());
	}

	public long count() {
		final Query<T> query = getDataStore().createQuery(clazz);
		return query.countAll();
	}

}
