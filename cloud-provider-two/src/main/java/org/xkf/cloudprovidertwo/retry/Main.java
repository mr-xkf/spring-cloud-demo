/**
 * FileName: Main
 * Author:   13235
 * Date:     2019/9/26 23:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.retry;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.CompositeRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/26
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.retry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void retry() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();
        //重试策略
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        //最多尝试三次
        retryPolicy.setMaxAttempts(3);
        //退避策略
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(500);
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        //重试操作
        final RetryCallback<Boolean, Exception> retryCallback =
                (retryContext) -> {
                    System.out.println(String.format("retry count %s", retryContext.getRetryCount() + 1));
                    throwError(retryContext.getRetryCount() + 1);
                    return true;
                };
        //重试失败后执行兜底回调
        final RecoveryCallback recoveryCallback = retryContext ->
        {
            System.out.println(String.format("after retry: %s, recovery method called!", retryContext.getRetryCount()
                    + 1));
            return false;
        };
        Boolean result = retryTemplate.execute(retryCallback, recoveryCallback);
        System.out.println(String.format("execute  result: %s", result));
    }

    private void throwError(int i) throws Exception {
        if (i < 20) {
            throw new Exception("");
        }
    }


    public void call() {
        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();

    }
}
