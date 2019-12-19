package com.company.wrappers;

/**
 * Обертка для обеспечения возможности сравнения объектов разных типов по принципу, описанному в методе compareTo();
 * @param <T> любой тип проксируемого объекта.
 */
public class ObjectComparableWrapper<T> implements Comparable<ObjectComparableWrapper> {

	private T object;

	public ObjectComparableWrapper(T object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	/**
	 * Алгоритм сравнения работает по следующему принципу:
	 * самыми меньшими (первыми) идут все Integer, затем все String, наибольшие (последние) - все другие типы объектов
	 * (не являющихся подклассами ни Integer, ни String).
	 * @param that - объект-обертка, с чьим внутренним объектом происходит сравнение.
	 *
	 * @return -1, если этот объект меньше того, 1 - если этот обект больше того, 0 - если объекты считаются равными.
	 */
	@Override
	public int compareTo(ObjectComparableWrapper that) {
		if (object instanceof Integer && that.getObject() instanceof Integer) {
			return ((Integer) object).compareTo((Integer) that.getObject());
		} else if (object instanceof String && that.getObject() instanceof String) {
			return ((String) object).compareTo((String) that.getObject());
		} else {
			return object instanceof Integer ?
				   -1 : object instanceof String && that.getObject() instanceof Integer ?
						1 : (that.getObject() instanceof Integer || that.getObject() instanceof String) ?
								1 : 0;
		}
	}
}
