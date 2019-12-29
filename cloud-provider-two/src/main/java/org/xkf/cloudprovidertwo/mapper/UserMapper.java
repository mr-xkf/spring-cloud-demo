/**
 * FileName: UserMapper
 * Author:   13235
 * Date:     2019/10/15 0:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.mapper;

import org.apache.ibatis.annotations.Select;
import org.xkf.cloudprovidertwo.entity.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/15
 * @since 1.0.0
 */

public interface UserMapper {


    @Select(value = "")
    User getId();
}
