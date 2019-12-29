/**
 * FileName: User
 * Author:   13235
 * Date:     2019/10/15 0:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/15
 * @since 1.0.0
 */
@Data
public class User  implements Serializable{
    private static final long serialVersionUID = -3092847599829078691L;

    private Long id;
    private String userName;
    private String password;



}
