package org.energy.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GeneralFunction {

    public static List<BigInteger>[] compareList(List<BigInteger> oldList, List<BigInteger> newList) {
        List<BigInteger> elementsRepeated = new ArrayList<>();
        List<BigInteger> elementsNew = new ArrayList<>();
        List<BigInteger> elementsLeftOver = new ArrayList<>(oldList.stream().toList());

        for (BigInteger num : newList) {
            if (!oldList.contains(num)) {
                elementsNew.add(num);
            } else {
                elementsRepeated.add(num);
                elementsLeftOver.remove(num);
            }
        }
        return new List[]{newList, oldList, elementsRepeated, elementsNew, elementsLeftOver};
    }

    public static String addFilterIN(String query, List<BigInteger> data, String reference) {
        if (data != null && !data.isEmpty()) {
            var filter = reference + " IN(%param%)";
            var first = new AtomicBoolean(true);
            AtomicReference<String> in = new AtomicReference<>("");
            data.forEach(registry -> {
                String actual = in.get();
                if (first.get()) {
                    actual += registry.toString();
                    in.set(actual);
                    first.set(false);
                } else {
                    actual += "," + registry.toString();
                    in.set(actual);
                }
            });
            filter = filter.replace("%param%", in.get());
            query = query.replace("%query%", filter);
        }
        return query;
    }
}
