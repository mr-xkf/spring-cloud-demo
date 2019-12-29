/**
 * FileName: Solution2
 * Author:   13235
 * Date:     2019/11/16 20:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 给定一个字符串,按字符中的最大
 * 非重复子串
 * <p>
 * 用start记录当前处理开始位置
 * 遍历字符串,当前字符从开始位置
 * start开始已经出现过的时候,
 * 字串开始位置+1，否则更新map中的
 * hash值为当前位置
 *
 * @author 13235
 * @create 2019/11/16
 * @since 1.0.0
 */
public class Solution2 {
    public static void main(String[] args) {
        int length = lengthOfLongestSubstring("34gffffff");

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        int start = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c) && map.get(c) >= start) {
                start = i + 1;
            } else {
                result = Math.max(result, i - start + 1);
            }
            map.put(c, i);
        }
        return result;
    }
}
