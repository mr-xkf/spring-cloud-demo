/**
 * FileName: FileRename
 * Author:   13235
 * Date:     2019/11/9 0:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import java.io.File;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/9
 * @since 1.0.0
 */
public class FileRename {

    public static void main(String[] args) {
        String pathName = "";
        File file = new File(pathName);
        renameFile(file.listFiles());
    }

    private static void renameFile(File[] files) {
        for (File file : files) {
            String name = file.getName();
            name = name.replaceFirst("\\[", "[0");
            file.renameTo(new File(file.getParent() + "/" + name));
        }
    }
}
