/**
 * FileName: DemoData
 * Author:   13235
 * Date:     2019/10/24 21:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.excel.read;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/24
 * @since 1.0.0
 */
@Data
public class DemoData  extends BaseRowModel{

    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 3,format = "yyyy-MM-dd")
    private Date birtDate;
    @ExcelProperty(index = 4)
    private String message;


}
