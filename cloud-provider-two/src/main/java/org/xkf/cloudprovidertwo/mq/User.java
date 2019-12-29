/**
 * FileName: User
 * Author:   13235
 * Date:     2019/10/2 0:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -8535979395302663652L;

    private String username;
    private String password;
}
