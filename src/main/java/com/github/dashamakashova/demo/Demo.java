package com.github.dashamakashova.demo;

import com.github.dashamakashova.tableutils.Table;
import com.github.dashamakashova.tableutils.TableSelector;
import com.github.dashamakashova.tableutils.TableGrouper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ БИБЛИОТЕКИ TABLE-UTILS\n");

        demonstrateProducts();

        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrateNumbers();

        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrateStudents();
    }

    private static void demonstrateProducts() {
        System.out.println("ПРИМЕР 1: Таблица товаров");

        Table<String> products = new Table<>();
        products.addRow(Arrays.asList("Яблоко", "Фрукт", "100", "Красный"));
        products.addRow(Arrays.asList("Банан", "Фрукт", "80", "Желтый"));
        products.addRow(Arrays.asList("Морковь", "Овощ", "50", "Оранжевый"));
        products.addRow(Arrays.asList("Картофель", "Овощ", "30", "Коричневый"));
        products.addRow(Arrays.asList("Апельсин", "Фрукт", "120", "Оранжевый"));

        System.out.println("Исходная таблица:");
        System.out.println(products);

        TableSelector<String> selector = new TableSelector<>(products);
        TableGrouper<String> grouper = new TableGrouper<>(products);

        System.out.println("Первая строка: " + selector.getRow(0));

        System.out.println("Столбец категорий: " + selector.getColumn(1));

        System.out.println("Ячейка (2,0): " + selector.getCell(2, 0));

        Table<String> selectedRows = selector.selectRows(Arrays.asList(0, 2, 4));
        System.out.println("Выборка строк [0, 2, 4]:");
        System.out.println(selectedRows);

        Table<String> selectedColumns = selector.selectColumns(Arrays.asList(0, 2));
        System.out.println("Выборка столбцов [0, 2] (Название, Цена):");
        System.out.println(selectedColumns);

        Map<String, Table<String>> byCategory = grouper.groupByColumn(1);
        System.out.println("Группировка по категориям:");
        for (Map.Entry<String, Table<String>> entry : byCategory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getRowCount() + " товаров");
        }
    }

    private static void demonstrateNumbers() {
        System.out.println("ПРИМЕР 2: Таблица чисел");

        Table<Integer> numbers = new Table<>();
        numbers.addRow(Arrays.asList(1, 10, 100));
        numbers.addRow(Arrays.asList(2, 20, 200));
        numbers.addRow(Arrays.asList(3, 30, 300));
        numbers.addRow(Arrays.asList(4, 40, 400));
        numbers.addRow(Arrays.asList(5, 50, 500));

        System.out.println("Числовая таблица:");
        System.out.println(numbers);

        TableSelector<Integer> selector = new TableSelector<>(numbers);
        TableGrouper<Integer> grouper = new TableGrouper<>(numbers);

        Table<Integer> selected = selector.selectColumns(Arrays.asList(0, 2));
        System.out.println("Первый и последний столбец:");
        System.out.println(selected);

        Map<Integer, Table<Integer>> grouped = grouper.groupByColumn(0);
        System.out.println("Группировка по первому числу:");
        for (Map.Entry<Integer, Table<Integer>> entry : grouped.entrySet()) {
            System.out.println("Число " + entry.getKey() + ": " +
                    entry.getValue().getRowCount() + " строк");
        }
    }

    private static void demonstrateStudents() {
        System.out.println("ПРИМЕР 3: Таблица студентов");

        Table<String> students = new Table<>();
        students.addRow(Arrays.asList("Иван", "Иванов", "Группа А", "4.5"));
        students.addRow(Arrays.asList("Петр", "Петров", "Группа Б", "3.8"));
        students.addRow(Arrays.asList("Мария", "Сидорова", "Группа А", "4.2"));
        students.addRow(Arrays.asList("Анна", "Кузнецова", "Группа Б", "3.9"));
        students.addRow(Arrays.asList("Сергей", "Смирнов", "Группа А", "4.8"));

        System.out.println("Таблица студентов:");
        System.out.println(students);

        TableSelector<String> selector = new TableSelector<>(students);
        TableGrouper<String> grouper = new TableGrouper<>(students);

        Table<String> excellentStudents = selector.selectRows(Arrays.asList(0, 4));
        System.out.println("Отличники (строки 0 и 4):");
        System.out.println(excellentStudents);

        Map<String, Table<String>> byGroup = grouper.groupByColumn(2);
        System.out.println("Группировка по учебным группам:");
        for (Map.Entry<String, Table<String>> entry : byGroup.entrySet()) {
            System.out.println(entry.getKey() + ": " +
                    entry.getValue().getRowCount() + " студентов");
        }

        Table<String> namesAndGrades = selector.selectColumns(Arrays.asList(0, 1, 3));
        System.out.println("Имена и оценки студентов:");
        System.out.println(namesAndGrades);
    }
}