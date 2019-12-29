/**
 * FileName: PageResult
 * Author:   13235
 * Date:     2019/10/14 21:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 分页实体对象
 *
 * @author 13235
 * @create 2019/10/14
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class PageResult<T> {

    private List<T> list;
    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int records;
    private int previousPage;
    private int nextPage;

    public int getTotalPage() {
        if (this.records % this.pageSize == 0) {
            return this.records / this.pageSize;
        }
        return this.records / this.pageSize + 1;
    }

    public int getPreviousPage() {
        if (this.currentPage == 1) {
            this.previousPage = 0;
        } else {
            this.previousPage = this.currentPage - 1;
        }
        return this.previousPage;
    }


    public int getNextPage() {
        if (nextPage >= totalPage) {
            return totalPage;
        } else {
            nextPage = this.currentPage + 1;
        }
        return nextPage;
    }
}
