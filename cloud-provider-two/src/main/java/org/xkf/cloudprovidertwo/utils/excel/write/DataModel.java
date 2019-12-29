/**
 * FileName: DataModel
 * Author:   13235
 * Date:     2019/10/31 23:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.excel.write;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/31
 * @since 1.0.0
 */
@Data
public class DataModel {
    private String content;
    private String oldContent;
    private int rowIndex;
    private int colIndex;
}
