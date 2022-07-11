package play.ground;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SteamForEach {

    public static void main(String[] args) {
        List<Integer> temp = new ArrayList<>();

        for(int i = 0 ; i < 5000000; i++) {
            temp.add(1);
        }
        long startTime = System.currentTimeMillis();

        System.out.println("Stream");
        Stream<Integer> stream = temp.stream();
        stream.forEach(System.out::println);
        long streamEnd = System.currentTimeMillis();
        long result1 = streamEnd - startTime;

        startTime = System.currentTimeMillis();
        System.out.println("ForEach");

        temp.forEach(System.out::println);
        long forEachEnd = System.currentTimeMillis();
        long result2 = forEachEnd - startTime;

        startTime = System.currentTimeMillis();
        System.out.println("Stream ForEach");
        temp.stream().forEach(System.out::println);

        long StreamForEachEnd = System.currentTimeMillis();

        System.out.println("Stream = " + result1 + ("ms"));
        System.out.println("ForEach = " + result2 + ("ms"));
        System.out.println("StreamForEach = " + (StreamForEachEnd - startTime) + ("ms"));

    }
}
