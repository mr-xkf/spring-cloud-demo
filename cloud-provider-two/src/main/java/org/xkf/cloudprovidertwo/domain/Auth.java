/**
 * FileName: Auth
 * Author:   13235
 * Date:     2019/10/11 20:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/11
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class Auth {

    private String authName;
    private String authUrl;
    private String authUniqueMark;
    private Date createTime;
    private String methodType;


}
