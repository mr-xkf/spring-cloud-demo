/**
 * FileName: WithException
 * Author:   13235
 * Date:     2019/11/9 0:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/9
 * @since 1.0.0
 */
public class WithException {

    public static int withException() {
        int i = 10;
        try {
            System.out.println("i in try block is： " + i);
            i = i / 0;
            return --i;
        } catch (Exception e) {
            System.out.println("i in catch - form try block is: " + i);
            --i;
            System.out.println("i in catch block is: " + i);
            return --i;
        } finally {
            System.out.println("i in finally - from try or catch block is -- " + i);
            --i;
            System.out.println("i in finally block is -- " + i);
            return --i;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(resolve(new int[]{1, 2, 3, 4, 5, 6})));    }

    public static int[] resolve(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] result = {Integer.MIN_VALUE, -1, -1};
        int sum = 0;
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum >= 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
                begin = i;
            }
            if (result[0] <= sum) {
                result[0] = sum;
                result[1] = begin;
                result[2] = i;
            }

        }


        return result;

    }


}
