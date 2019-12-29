/**
 * FileName: ListNode
 * Author:   13235
 * Date:     2019/11/16 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/16
 * @since 1.0.0
 */
@Data
public class ListNode {
     int val;

     ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
