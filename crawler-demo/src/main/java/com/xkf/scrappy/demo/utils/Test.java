/**
 * FileName: Test
 * Author:   13235
 * Date:     2019/12/15 16:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.scrappy.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/12/15
 * @since 1.0.0
 */
public class Test {
    public static final Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");


    static class CountInputStream extends FilterInputStream {
        private int count = 0;

        protected CountInputStream(InputStream in) {
            super(in);
        }

        public int getBytesRead() {
            return this.count;
        }

        @Override
        public int read() throws IOException {
            int read = in.read();
            if (read != -1) {
                this.count++;
            }
            return read;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int n = in.read(b, off, len);
            this.count += n;
            return n;
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] data = "hello, world!".getBytes("utf-8");
        CountInputStream input = new CountInputStream(new ByteArrayInputStream(data));
        int n;
        while ((n = input.read()) != -1) {
            System.out.println((char) n);
        }
        System.out.println("Total read " + input.getBytesRead() + " bytes!");
        String str = "Hello, ${name}! You are learning ${lang} !;";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xxx");
        map.put("lang", "zh_en");
        String render = render(str, map);
        System.out.println(render);
    }

    public static String render(String input, Map<String, Object> map) {
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
            matcher.appendReplacement(sb, map.get(group).toString());
        }
        matcher.appendTail(sb);
        return sb.toString();


    }


    private static String buildStringSql(String tableNames, String[] fileds) {
        StringJoiner stringJoiner = new StringJoiner(",", "select ", " from " + tableNames);
        for (int i = 0; i < fileds.length; i++) {
            stringJoiner.add(fileds[i]);
        }
        return stringJoiner.toString();
    }


}
