import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(6);
        list.add(8);
        list.add(1);
        System.out.println(evenCount(list));
        findMinMax(
                list.stream(),
                Integer::compareTo,
                (integer, integer2) -> System.out.println(integer +" "+ integer)
        );
        System.out.println(list.toString());
    }
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer
    ){
        Supplier<Stream<? extends T>> streamSupplier = () -> stream;
        Optional<? extends T> min = streamSupplier.get().min(order);
        Optional<? extends T> max = streamSupplier.get().max(order);
        if (min.isPresent() && max.isPresent())
            minMaxConsumer.accept(min.get(),max.get());
        else {
            minMaxConsumer.accept(null,null);
        }
    }
    public static int evenCount(List<Integer> list){
        return  (int)list.stream()
                .filter(i-> i%2==0)
                .count();
    }
}
