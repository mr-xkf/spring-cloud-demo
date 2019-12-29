/**
 * FileName: MyController
 * Author:   13235
 * Date:     2019/9/15 2:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xkf.cloudprovider.annotation.XAutowired;
import org.xkf.cloudprovider.annotation.XController;
import org.xkf.cloudprovider.annotation.XRequestMapping;
import org.xkf.cloudprovider.service.BaseService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/15
 * @since 1.0.0
 */
@XController
@XRequestMapping("/customer")
@Slf4j
@ResponseBody
public class MyController {

    @XAutowired
    private BaseService baseService;


    @XRequestMapping("/say")
    public String say() {
        baseService.ok();
        return "成功！";
    }
}
