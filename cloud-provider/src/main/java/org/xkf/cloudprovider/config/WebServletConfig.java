/**
 * FileName: WebServletConfig
 * Author:   13235
 * Date:     2019/9/14 17:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*


package org.xkf.cloudprovider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.xkf.cloudprovider.annotation.XAutowired;
import org.xkf.cloudprovider.annotation.XController;
import org.xkf.cloudprovider.annotation.XRequestMapping;
import org.xkf.cloudprovider.annotation.XService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

*/
/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/14
 * @since 1.0.0
 *//*

@Slf4j
@WebServlet(urlPatterns = "/*",
        loadOnStartup = 1, initParams = {@WebInitParam(name = "contextLocation", value = "myApplication.properties")})
public class WebServletConfig extends HttpServlet {

    //读取配置文件
    public static final Properties CONFIG_PROP = new Properties();

    //所有class文件
    public static List<String> classLists = new ArrayList<>();

    //IOCmap
    public static Map<String, Object> iocMap = new ConcurrentHashMap<>();

    //handMapping
    public static Map<String, Method> urlMap = new ConcurrentHashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        log.info("[info-7] requestUri:{}", url);

        if (!urlMap.containsKey(url)) {
            resp.getWriter().write("404 not found url");
            return;
        }
        Method method = urlMap.get(url);
        log.info("[info-7] method -->" + method);
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        log.info("[info-7] iocMap.get(beanName)->" + iocMap.get(beanName));
        try {
            method.invoke(iocMap.get(beanName));
            log.info("[info-7] method invoke put {" + iocMap.get(beanName) + "}.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String contextLocation = config.getInitParameter("contextLocation");
        //1.init配置文件
        loadLocationConfig(contextLocation);
        //2.扫描包,获取class文件列表
        String packageName = CONFIG_PROP.getProperty("my.scan.packages");
        scanPackages(packageName);
        //3.初始化IOC容器，将所有相关的类实例保存到IOC容器中
        doInstance();
        //4.依赖注入
        doAutowired();
        //5.初始化HandlerMapping
        initHandlerMapping();
        log.info("framework is init.");
        //6.打印数据
        doTestPrintData();

    }


    */
/**
     * 打印数据
     *//*

    private void doTestPrintData() {
        log.info("[info-6] ----data----");
        log.info("init properties");
        log.info("[ClassNameList]-->");
        classLists.forEach(e -> System.out.println(e));
        log.info("[iocMap]-->");
        iocMap.entrySet().forEach(e -> System.out.println(e));
        log.info("[handlerMapping-->]");
        urlMap.entrySet().forEach(e -> System.out.println(e));
        log.info("-----done----");
        log.info("====启动成功=====");
        log.info("测试开始");
    }

    */
/**
     * 初始化 HandlerMapping
     *//*

    private void initHandlerMapping() {
        if (iocMap.isEmpty()) {
            return;
        }
        for (Entry<String, Object> entry : iocMap.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(XController.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(XRequestMapping.class)) {
                XRequestMapping annotation = clazz.getAnnotation(XRequestMapping.class);
                baseUrl = annotation.value();
            }
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(XRequestMapping.class)) {
                    continue;
                }
                XRequestMapping xRequestMapping = method.getAnnotation(XRequestMapping.class);
                String url = ("/" + baseUrl + "/" + xRequestMapping.value()).replaceAll("/+", "/");
                urlMap.put(url, method);
                log.info("[info-5] handlerMapping put {" + url + "}" + "{" + method + "}.");
            }
        }
    }

    private void doAutowired() {
        if (iocMap.isEmpty()) {
            return;
        }
        Set<Entry<String, Object>> entries = iocMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(XAutowired.class)) {
                    continue;
                }
                XAutowired annotation = field.getAnnotation(XAutowired.class);
                String beanName = annotation.value().trim();
                if ("".equals(beanName)) {
                    log.info("[info ]xAutowired.value() is null");
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        if (classLists.isEmpty()) {
            return;
        }
        try {
            for (String className : classLists) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(XController.class)) {
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    iocMap.put(beanName, instance);
                    log.info("[info-3]{" + beanName + "} has been saved in iocMap.");

                } else if (clazz.isAnnotationPresent(XService.class)) {
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    XService annotation = clazz.getAnnotation(XService.class);
                    String value = annotation.value();
                    if (!"".equals(value)) {
                        beanName = value;
                    }
                    Object o = clazz.newInstance();
                    iocMap.put(beanName, o);
                    log.info("[info-3]{" + beanName + "} has been saved in iocMap.");
                    //找类的接口
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                        if (iocMap.containsKey(anInterface.getName())) {
                            throw new Exception("The Bean Name Is Exist.");
                        }
                        iocMap.put(anInterface.getName(), o);
                        log.info("[info-3]{" + beanName + "} has been saved in iocMap.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    */
/**
     * 获取类的首字母小写的名称
     *
     * @param className
     * @return
     *//*

    private String toLowerFirstCase(String className) {
        char[] chars = className.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    */
/**
     * 2扫描相关的类
     *
     * @param packageName
     *//*

    private void scanPackages(String packageName) {
        if (StringUtils.isBlank(packageName)) {
            return;
        }
        //包名分隔符 .=> /
        URL resource = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        if (resource == null) {
            return;
        }
        File file = new File(resource.getFile());
        if (file == null) {
            return;
        }
        for (File f : file.listFiles()) {
            //是目录
            if (f.isDirectory()) {
                log.info("[info-2]{" + f.getName() + "} is a directory.");
                //子目录递归
                scanPackages(packageName + "." + f.getName());
            } else {
                if (!f.getName().endsWith(".class")) {
                    log.info("[info-2]{" + f.getName() + "} is not a class file.");
                    continue;
                }
                String className = packageName + "." + f.getName().replace(".class", "");
                classLists.add(className);
                log.info("[info-2]:{" + className + "} has been saved in classNameList.");
            }
        }
    }


    */
/**
     * 加载配置文件
     *
     * @param contextConfigLocation
     *//*


    private void loadLocationConfig(String contextConfigLocation) {
        log.info("init properties:{}", contextConfigLocation);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            //保存在内存
            CONFIG_PROP.load(resourceAsStream);
            log.info("[info-1] property file has been save in contextConfig.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
*/
