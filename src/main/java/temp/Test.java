package temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuhaiyan
 * @date 2019-12-05 17:06
 */
public class Test {

    private static final Object[] EMPTY = {};
    private static final Object[] DEFAULT = {};


    public static void main(String[] args) {
        System.out.println(EMPTY == null);
        System.out.println(EMPTY);
        System.out.println(EMPTY.length);

        String[] o = {"aa", "bb", "cc"};
        System.out.println(o.getClass().getComponentType());

        System.out.println(Object.class.getComponentType());
        int[] hello = new int[4];
        System.out.println(hello.length);
        ArrayList<Integer> in = new ArrayList<>(10);
        in.add(20);
        in.add(20);
        System.out.println(in.size());
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("huge");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            if(next.equals("world")) {
                list.remove(next);
            }

        }
        System.out.println(list);


    }
}
