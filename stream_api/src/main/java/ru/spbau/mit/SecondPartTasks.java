package ru.spbau.mit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    private static Stream<String> getLinesFromFile(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist!");
        }
    }

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().map(Paths::get)
                .flatMap(SecondPartTasks::getLinesFromFile)
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }


    private static class Target {
        private double radius = 0.5;
        private final Random randomGenerator = new Random();


        private class Point {
            private double x = randomGenerator.nextDouble();
            private double y = randomGenerator.nextDouble();

            private boolean isShot() {
                return Math.sqrt(Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= radius;
            }
        }

        private double run() {
            int count = 100000;
            double score = IntStream.range(0, count).mapToObj(s -> new Point())
                    .filter(Point::isShot).count();
            return  score / count;
        }
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Target target = new Target();
        return target.run();
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream()
                .max(Comparator.comparing(e -> String.join("", e.getValue()).length()))
                .map(Map.Entry::getKey).orElse("");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)));
    }
}