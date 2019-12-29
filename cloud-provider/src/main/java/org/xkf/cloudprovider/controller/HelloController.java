/**
 * FileName: HelloController
 * Author:   13235
 * Date:     2019/9/12 1:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/12
 * @since 1.0.0
 */
@RestController
@Slf4j
@Validated
public class HelloController {

    @GetMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType
            .APPLICATION_JSON_UTF8_VALUE)
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping(value = "/validator")
    public String validator(@RequestParam("group") @Pattern(regexp = "[\\d]+", message = "无效地址") String group) {
        return "成功！" + group;
    }

}
