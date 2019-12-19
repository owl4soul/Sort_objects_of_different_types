package com.company;

import com.company.wrappers.ObjectComparableWrapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Object[] unsortedTestData = new Object[]{"two", new Object(), new Object(), 0, new Object(), 2, "ten", new Object(), 1, "zero", 10, 3, new Object(), "one"};
		Object[] sortedResult = sortByIterationAndWrapper(unsortedTestData);

		for (Object o : sortedResult) {
			System.out.println(o);
		}
	}

	/**
	 * Сортировка с помощью оберток, реализующих интерфейс Comparable, где описан метод сравнения объектов классов
	 * Integer и String между собой.
	 * @param unsortedNumsAndStrings неотсортированный массив данных.
	 *
	 * @return отсортированный массив данных.
	 */
	private static Object[] sortByIterationAndWrapper(Object[] unsortedNumsAndStrings) {
		// Флаг отсортированности (true - отсортировано, false - не отсортировано).
		boolean isSorted = false;

		while (!isSorted) {
			// если во время полного цикла не произойдет ни одного изменения элементов местами,
			// то значит массив является отсортированным, иначе флаг будет изменен на false.
			isSorted = true;

			for (int i = 0; i < unsortedNumsAndStrings.length; i++) {
				if (i < unsortedNumsAndStrings.length - 1) {
					// завернуть в обертку имплементирующую Comparable и сверять обертки
					ObjectComparableWrapper comparableWrapper = new ObjectComparableWrapper<>(unsortedNumsAndStrings[i]);
					ObjectComparableWrapper comparableWrapper2 = new ObjectComparableWrapper<>(
							unsortedNumsAndStrings[i + 1]);

					if (comparableWrapper.compareTo(comparableWrapper2) > 0) {
						Object tempObject = comparableWrapper2.getObject();
						unsortedNumsAndStrings[i + 1] = unsortedNumsAndStrings[i];
						unsortedNumsAndStrings[i] = tempObject;
						isSorted = false;
					}
				}
			}
		}

        return unsortedNumsAndStrings;
    }


    /**
     * Сортируем в натуральном порядке оба типа данных, отфильтрованных по разным стримам, конкатенируя их
     * (сначала цифры, затем строки).
     * @param unsortedNumsAndStrings массив неотсортированных данных.
     *
     * @return отсортированный массив данных.
     */
	private static Object[] sortAsTwoConcatenatedStreams(Object[] unsortedNumsAndStrings) {
		return Stream.concat(Arrays.stream(unsortedNumsAndStrings).filter(o -> o instanceof Integer).sorted(),
							 Arrays.stream(unsortedNumsAndStrings).filter(o -> o instanceof String).sorted()).toArray();
	}

    /**
     * Сортировка с использованием класса касомного компаратора.
     * @param unsortedNumsAndStrings неотсортированный массив данных.
     *
     * @return отсортированный массив данных.
     */
	private static Object[] sortWithCustomComparator(Object[] unsortedNumsAndStrings) {
		List<Object> unsortedList = Arrays.asList(unsortedNumsAndStrings);
        Collections.sort(unsortedList, new IntegerAndStringComparator());
        return unsortedList.toArray();
    }

    /**
     * Компаратор для определения порядка сортировки объектов типа чисел (представленных Integer -
     * в соответствии с заданными условиями задачи,  хотя в более общем случае уместнее было бы представлять
     * числа типом Number) и строк (представленных String).
     */
    private static class IntegerAndStringComparator implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
        	// TODO: здесь можно условия if сделать, как в ObjectComparableWrapper в методе compareTo,
			// TODO: либо переписать все на тернарные операторы
            if (o1 instanceof Integer && !(o2 instanceof Integer)) {
                return -1;
            } else if (o1 instanceof Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2);
            } else if (o1 instanceof String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2);
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
    }
}
