/**
 * FileName: Solution
 * Author:   13235
 * Date:     2019/11/16 14:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/16
 * @since 1.0.0
 */
public class Solution {



    public static void main(String[] args) {
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(4);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(9);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(8);
        a1.next = a2;
        a2.next = a3;
        ListNode listNode = addTwoNumbers(p1, a1);
        System.out.println(listNode.toString());


    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        // 头结点
        ListNode root = new ListNode(0);
        ListNode r = root;
        root.next = l1;
        // 初始进位
        int carry = 0;
        int sum;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            // 本位的结果
            p1.val = sum % 10;
            // 本次进位
            carry = sum / 10;

            r.next = p1;
            // 指向最后一个相加的结点
            r = p1;
            p1 = p1.next;
            p2 = p2.next;

        }
        if (p1 == null) {
            r.next = p2;
        } else {
            r.next = p1;
        }
        // 最后一次相加还有进位
        if (carry == 1) {
            // 开始时r.next是第一个要相加的结点
            while (r.next != null && carry != 0) {
                sum = r.next.val + carry;
                r.next.val = sum % 10;
                carry = sum / 10;
                r = r.next;
            }

            // 都加完了还有进位，就要创建一个新的结点
            if (carry == 1) {
                r.next = new ListNode(1);
            }
        }
        return root.next;
    }
}




