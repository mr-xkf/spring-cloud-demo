/**
 * FileName: FileUtils
 * Author:   13235
 * Date:     2019/10/24 22:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import java.io.File;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/24
 * @since 1.0.0
 */
public class FileUtils {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static String getPath() {
        return FileUtils.class.getResource("/").getPath();
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }


}
