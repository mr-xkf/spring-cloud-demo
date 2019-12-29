/**
 * FileName: BST
 * Author:   13235
 * Date:     2019/11/1 20:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.domain;

/**
 * 二叉查找树
 *
 * @author 13235
 * @create 2019/11/1
 * @since 1.0.0
 */
public class BST {
    private Node root;
    private class Node {
        private String key;
        private String val;
        private Node left, right;
        private int size;
    }
        public int size() {
            return size(root);
        }
        private int size(Node root) {
            if (root == null) {
                return 0;
            }
            return root.size;
        }
}
