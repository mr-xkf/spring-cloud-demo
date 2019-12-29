/**
 * FileName: UserController
 * Author:   13235
 * Date:     2019/9/17 1:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xkf.cloudprovider.service.UserService;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/17
 * @since 1.0.0
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/test")
    public void test() {
        userService.ok();
        userService.getName();
    }
}
