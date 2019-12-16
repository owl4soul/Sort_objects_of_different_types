package com.company.wrappers;

public class ObjectComparableWrapper <T> implements Comparable<ObjectComparableWrapper> {

	private T object;

	public ObjectComparableWrapper(T object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	@Override
	public int compareTo(ObjectComparableWrapper o) {
		if (object instanceof Integer && o.getObject() instanceof Integer) {
			return ((Integer) object).compareTo((Integer) o.getObject());
		} else if (object instanceof String && o.getObject() instanceof String) {
			return ((String) object).compareTo((String) o.getObject());
		} else {
			return object instanceof Integer ? -1 : 1;
		}
	}
}
