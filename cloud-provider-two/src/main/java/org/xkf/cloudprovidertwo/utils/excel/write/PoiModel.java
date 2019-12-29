/**
 * FileName: PoiModel
 * Author:   13235
 * Date:     2019/11/1 1:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.excel.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/1
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoiModel {

    private String content;

    private String oldContent;

    private int rowIndex;

    private int cellIndex;

}
