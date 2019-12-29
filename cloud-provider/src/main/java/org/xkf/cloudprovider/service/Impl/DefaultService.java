/**
 * FileName: DefaultService
 * Author:   13235
 * Date:     2019/9/14 14:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.xkf.cloudprovider.annotation.XService;
import org.xkf.cloudprovider.service.BaseService;

/**
 * 〈一句话功能简述〉<br> 
 *  默认的服务
 *
 * @author 13235
 * @create 2019/9/14
 * @since 1.0.0
 */
@Slf4j
@XService
public class DefaultService  implements BaseService{
    @Override
    public void ok() {
        log.info("ok!");
    }
}
