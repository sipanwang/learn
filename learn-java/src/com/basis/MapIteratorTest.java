package com.basis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有关于 map.entrySet() 和 keySet():
 *
 * 1、如果遍历 hashMap() 时 entrySet() 方法是将 key 和 value 全部取出来,所以性能开销是可以预计的, 而 keySet() 方法进行遍历的时候是根据取出的 key 值去查询对应的 value 值, 所以如果 key 值是比较简单的结构(如 1,2,3...)的话性能消耗上是比 entrySet() 方法低, 但随着 key 值得复杂度提高 entrySet() 的优势就会显露出来。
 *2、综合比较在只遍历 key 的时候使用 keySet(), 在只遍历 value 的是使用 values() 方法, 在遍历 key-value 的时候使用 entrySet() 是比较合理的选择。
 *3、如果遍历 TreeMap 的时候, 不同于 HashMap 在遍历 ThreeMap 的 key-value 时候务必使用 entrySet() 它要远远高于其他两个的性能, 同样只遍历 key 的时候使用 keySet(), 在只遍历 value 的是使用 values() 方法对于 TreeMap 也同样适用。
 */
public class MapIteratorTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","value1");
        map.put("2","value2");
        map.put("3","value3");

        //查看map里存的一些东西
        System.out.println(map.keySet());//结果[1, 2, 3]
        System.out.println(map.entrySet());//结果[1=value1, 2=value2, 3=value3]
        System.out.println(map.get("1"));//结果value1
        System.out.println(map.hashCode());//结果1823528565

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.kyeSet遍历key和value: ");
        for(String key : map.keySet()){
            System.out.println("key="+key+"and value="+map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value:");
        Iterator<Map.Entry<String,String>>it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry =it.next();
            System.out.println("key="+entry.getKey()+"and value="+entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value:");
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+"and value="+entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
}
