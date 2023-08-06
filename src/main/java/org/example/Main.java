package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\GOOSE\\IdeaProjects\\Sort-it\\src\\main\\java\\org\\example\\in.txt";
        String filePathw = "C:\\Users\\GOOSE\\IdeaProjects\\Sort-it\\src\\main\\java\\org\\example\\out.txt";
        List<String> columnValues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                columnValues.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> dataList = MergeSort.mergeSort(columnValues);
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePathw)) ) {
            wr.flush();
            for (String value : dataList) {
                wr.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class MergeSort {
    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list; // Основание рекурсии - список уже отсортирован
        }

        int mid = list.size() / 2;
        List<T> left = list.subList(0, mid);
        List<T> right = list.subList(mid, list.size());

        // Рекурсивно сортируем левую и правую части
        left = mergeSort(left);
        right = mergeSort(right);

        // Объединяем отсортированные списки
        return merge(left, right);
    }

    // Метод для слияния двух отсортированных списков
    private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        List<T> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            T leftValue = left.get(leftIndex);
            T rightValue = right.get(rightIndex);

            // Сравниваем элементы и добавляем их в объединенный список
            if (leftValue.compareTo(rightValue) <= 0) {
                mergedList.add(leftValue);
                leftIndex++;
            } else {
                mergedList.add(rightValue);
                rightIndex++;
            }
        }

        // Добавляем оставшиеся элементы из левого и правого списка, если есть
        while (leftIndex < left.size()) {
            mergedList.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            mergedList.add(right.get(rightIndex));
            rightIndex++;
        }

        return mergedList;
    }
}