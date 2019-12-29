/**
 * FileName: PageInfo
 * Author:   13235
 * Date:     2019/11/8 23:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/8
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class PageInfo<T> {

    private int pageIndex;
    private int pageSize;
    private int totalPage;
    private int totalSize;
    private List<T> data;

    public PageInfo(int pageIndex, int pageSize, int totalSize, List<T> data) {
        if (pageIndex < 0) {
            this.pageIndex = 1;
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPage=(int) Math.ceil(totalSize / pageSize);
        this.data = data;
    }

 }
