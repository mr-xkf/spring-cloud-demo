/**
 * FileName: TestController
 * Author:   13235
 * Date:     2019/11/8 23:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xkf.cloudprovidertwo.domain.PageInfo;
import org.xkf.cloudprovidertwo.entity.Order;
import org.xkf.cloudprovidertwo.mq.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/8
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/page")
    public PageInfo<User> pageInfo(@RequestParam("pageIndex") int pageIndex,
                                   @RequestParam("pageSize") int pageSize) {

        int update = jdbcTemplate.update("update  t_order t set t.name=? where t.id=?", new Object[]{"a","111111-0"});
        System.out.println(update);
       /* String sql = "select * from t_order  limit " + (pageIndex - 1) * pageSize + "," + pageSize;
        String coutSql = "select count(*) from t_order  ";
        List<Order> query = jdbcTemplate.query(sql, (resultSet, i) -> {
            Order order = new Order();
            order.setName(resultSet.getString(2));
            order.setMessage(resultSet.getString(3));
            return order;
        });*/
        Order order = jdbcTemplate.queryForObject("select id,`name`,message_id as messageId from t_order limit 0,1", new BeanPropertyRowMapper<>(Order.class));
        List<Order> orders = new ArrayList<>(1);
        orders.add(order);
        PageInfo pageInfo = new PageInfo<Order>(pageIndex, pageSize, 1, orders);
        return pageInfo;
    }
}
