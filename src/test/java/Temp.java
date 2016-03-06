import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ino on 2016/02/15.
 */
public class Temp {

    public static void main(String[] args) {
        Hoge hoge = new Hoge(1, "hoge");
        Hoge fuga = new Hoge(2, "fuga");
        List<Hoge> hoges1 = Arrays.asList(hoge, fuga);

        Hoge hoge2 = new Hoge(10, "hoge2");
        Hoge fuga2 = new Hoge(20, "fuga2");
        List<Hoge> hoges2 = Arrays.asList(hoge2, fuga2);

        hoges2.forEach(h -> h.setAmount(h.getAmount() * (-1)));
//        hoges2.addAll(hoges1);

        Stream<Hoge> hogeStream = Stream.concat(hoges1.stream(), hoges2.stream());
        System.out.println(hoges1);
        System.out.println(hoges2);
        hogeStream.forEach(v -> System.out.println(v));

        Map<String, Hoge> hogeMap1 = new HashMap<>();
        hogeMap1.put(hoge.getName(), hoge);
        hogeMap1.put(fuga.getName(), fuga);

        Map<String, Hoge> hogeMap2 = new HashMap<>();
        hogeMap2.put(hoge2.getName(), hoge2);
        hogeMap2.put(fuga2.getName(), fuga2);

        List<Hoge> result = Stream.concat(hogeMap1.entrySet().stream(), hogeMap2.entrySet().stream()).distinct().collect(Collectors.toList()).stream()
                .map( key -> new Hoge((hogeMap1.get(key).getAmount() - hogeMap2.get(key).getAmount()), hogeMap1.get(key).getName())).collect(Collectors.toList());
        System.out.println(result);
    }
}

class Hoge {
    private int amount;
    private String name;

    public Hoge(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hoge{" +
                "amount=" + amount +
                ", name='" + name + '\'' +
                '}';
    }
}