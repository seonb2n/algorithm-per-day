package StreamTest;

import org.w3c.dom.ls.LSOutput;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCollectorTest {

    public static void main(String[] args) {
        String[] strArr = {"aaa", "bbb", "ccc"};
        Stream<String> stringStream = Stream.of(strArr);
        String result = stringStream.collect(new StringConcatCollector());
        System.out.println(result);
    }
}


class StringConcatCollector implements Collector<String, StringBuilder, String> {
    @Override
    public Supplier<StringBuilder> supplier() {
        return () -> new StringBuilder();
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, s) -> sb.append(s);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (sb, sb2) -> sb.append(sb2);
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return sb -> sb.toString();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
