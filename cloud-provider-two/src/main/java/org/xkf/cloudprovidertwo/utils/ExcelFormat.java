/**
 * FileName: ExcelFormat
 * Author:   13235
 * Date:     2019/10/2 3:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
public enum ExcelFormat {

    FORMAT_INTEGER("INTEGER"),

    FORMAT_DOUBLE("DOUBLE"),

    FORMAT_PERCENT("PERCENT"),

    FORMAT_DATE("DATE");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
