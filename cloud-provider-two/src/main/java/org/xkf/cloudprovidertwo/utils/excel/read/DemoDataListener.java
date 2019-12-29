/**
 * FileName: DemoDataListener
 * Author:   13235
 * Date:     2019/10/24 21:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.excel.read;


import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/24
 * @since 1.0.0
 */
//不能被spring管理 要每次读取excel都要new,然后里面用到spring 可以构造方法传进去

@Slf4j
public class DemoDataListener extends AnalysisEventListener<List<String>> {



    //每隔5
    private static final int BATCH_COUNT = 5;

    private List<List<String>> list = new ArrayList<>();


    public DemoDataListener() {
    }



    @Override
    public void invoke(List<String> demoData, AnalysisContext analysisContext) {
        log.info("开始解析数据。。。。");
       // String s = JSON.toJSONString(demoData);
        list.add(demoData);
        if (list.size() > BATCH_COUNT) {
            saveDb();
            list.clear();
        }
    }

    private void saveDb() {
        log.info("{}条数据,开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveDb();
        log.info("所有数据解析完成！");
    }
}
