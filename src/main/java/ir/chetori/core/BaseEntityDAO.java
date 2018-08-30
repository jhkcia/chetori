package ir.chetori.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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

	public Document convert(T entity) throws IllegalArgumentException, IllegalAccessException {
		Document document = new Document();
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(EntityField.class)) {
				String key = field.getName();
				field.setAccessible(true);
				Object value = field.get(entity);
				document.append(key, value);
			}
		}
		document.append("_id", entity.getId());
		return document;
	}

	public T convert(Document document)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if (document == null)
			return null;
		T t = clazz.newInstance();
		document.keySet();
		for (String key : document.keySet()) {
			if ("_id".equals(key)) {
				t.setId(document.getString("_id"));
			} else {
				Field declaredField = clazz.getDeclaredField(key);
				boolean accessible = declaredField.isAccessible();

				declaredField.setAccessible(true);
				declaredField.set(t, document.get(key));
				declaredField.setAccessible(accessible);

			}
		}
		return t;
	}

	public String getCollectionName() {
		return clazz.getSimpleName();
	}

	private static MongoDatabase mongoDb = new MongoClient("localhost", 27017).getDatabase("testdb");

	public void add(T entity) throws IllegalArgumentException, IllegalAccessException {
		if (entity.getId() == null)
			entity.setId(generateNewId());
		Document document = convert(entity);
		getCollection().insertOne(document);
	}

	private String generateNewId() {
		return UUID.randomUUID().toString();
	}

	protected MongoCollection<Document> getCollection() {
		return mongoDb.getCollection(getCollectionName());
	}

	public T getById(String value)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return getByFieldValue("_id", value);
	}

	public T getByFieldValue(String fieldName, Object value)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(fieldName, value);
		Document item = getCollection().find(searchQuery).first();
		return convert(item);
	}

	public void update(T item) throws IllegalArgumentException, IllegalAccessException {
		delete(item);
		add(item);
	}

	public void delete(String id) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", id);
		getCollection().deleteOne(searchQuery);
	}

	public void delete(T item) {
		delete(item.getId());
	}

	public PageList<T> getPage(int pageNumber, int pageSize) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		MongoCursor<Document> result = getCollection().find().skip((pageNumber-1)*pageSize).limit(pageSize).iterator();
		List<T> outList = new ArrayList<>();
		while (result.hasNext()) {
			outList.add(convert(result.next()));
		}
		return new PageList<T>(count(), outList);
		
	}

	public List<T> getAll()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		MongoCursor<Document> result = getCollection().find().iterator();
		List<T> outList = new ArrayList<>();
		while (result.hasNext()) {
			outList.add(convert(result.next()));
		}
		return outList;
	}

	public long count() {
		return getCollection().count();
	}

}
