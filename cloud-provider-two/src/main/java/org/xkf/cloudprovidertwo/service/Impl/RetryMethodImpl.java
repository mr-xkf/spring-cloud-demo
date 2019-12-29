/**
 * FileName: RetryMethodImpl
 * Author:   13235
 * Date:     2019/10/14 21:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.xkf.cloudprovidertwo.service.RetryService;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/14
 * @since 1.0.0
 */
@Service
@Slf4j
public class RetryMethodImpl implements RetryService {


    @Override
    @Retryable(value = RuntimeException.class, backoff = @Backoff(delay = 100, multiplier = 1.1))
    public void retryMethod()throws RuntimeException {
        throw new RuntimeException("测试重试！");
    }
}
