/**
 * FileName: LRUCache
 * Author:   13235
 * Date:     2019/12/1 19:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.example.demo.utils;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/12/1
 * @since 1.0.0
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer val = super.get(key);
        if (val == null) {
            return -1;
        } else {
            return val;
        }
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        int i1 = lruCache.get(1);
        lruCache.put(3, 3);
        int i2 = lruCache.get(2);
        lruCache.put(4,4);
        int i3 = lruCache.get(1);
        int i4 = lruCache.get(3);
        int i5 = lruCache.get(4);

    }
}
