package com.snake.system.security;

import com.base.Constants;
import com.snake.resource.dao.StaticResource;
import com.snake.system.dao.RoleDao;
import com.snake.system.model.Function;
import com.snake.system.service.IFunctionService;
import com.snake.system.model.Role;
import com.snake.system.model.RoleUrl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;


//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    protected static Log log = LogFactory.getLog(MySecurityMetadataSource.class);

    private IFunctionService functionService;
    private StaticResource resource;

    private static boolean loadedResource = false;

    private static List<Function> functionList = new ArrayList<Function>();

    // 由spring调用
    public MySecurityMetadataSource(IFunctionService functionService, StaticResource resource) {
        this.functionService = functionService;
        this.resource = resource;
        loadResourceDefine();
    }

    // 返回请求资源所需要的权限
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if (StringUtils.isNotBlank(requestUrl)) {
            int index = requestUrl.indexOf("?");
            if (index != -1) {
                requestUrl = requestUrl.substring(0, index);//截取url中？前面的部分内容
            }
        }
        if (urlSecuritiesMap == null) {
            loadResourceDefine();
        }
        Collection<ConfigAttribute> attributes = urlSecuritiesMap.get(requestUrl);

        //管理员拥有全部访问权限，其他用户职能访问配置许可的权限
        /*
        if (urlSecuritiesMap.get(requestUrl) == null) {
            attributes = new ArrayList<ConfigAttribute>();
        }
        attributes.add(new SecurityConfig(Constants.ROLE_ADMIN));
        */

        //管理员访问所有，登录的所有用户可以访问未配置的权限
        if (urlSecuritiesMap.get(requestUrl) != null) {
            attributes.add(new SecurityConfig(Constants.ROLE_ADMIN));
        }else{//not config url
            attributes = new ArrayList<ConfigAttribute>();
            attributes.add(new SecurityConfig(Constants.ROLE_LOGIN_USER));
        }
        return attributes;
    }

    public static boolean checkUrl(String url, String roleCode) {
        Collection<ConfigAttribute> configAttributes = urlSecuritiesMap.get(url);
        boolean result = false;
        if (null != configAttributes) {
            Iterator<ConfigAttribute> configAttributeIterator = configAttributes.iterator();
            while (configAttributeIterator.hasNext()) {
                ConfigAttribute configAttribute = configAttributeIterator.next();
                if (configAttribute.getAttribute().equals(roleCode)) {
                    result = true;
                    break;
                }
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    public static boolean checkId(Long functionId, Long roleId) {
        Set<Long> set = roleFunctionMap.get(roleId);
        boolean result = false;
        if (null != set) {
            for (Long id : set) {
                if (functionId == id) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> configAttributes = null;
        Collection<Role> roles = RoleDao.getStaticList();
        if (null != roles) {
            configAttributes = new ArrayList<ConfigAttribute>();
            for (Role role : roles) {
                configAttributes.add(new SecurityConfig(role.getAuthority()));
            }
        }
        return configAttributes;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    // 加载所有角色与权限的关系
    private static Map<String, Collection<ConfigAttribute>> urlSecuritiesMap = null;
    private static Map<Long, Set<Long>> roleFunctionMap = null;

    private void loadResourceDefine() {
        try {
            if (null == functionList || functionList.size() < 1) {
                functionList = functionService.getFunctionTree();
            }//todo
            if (!loadedResource) {
                resource.initStaticObjects();
                loadedResource = true;
            }
            if (urlSecuritiesMap == null) {
                urlSecuritiesMap = new HashMap<String, Collection<ConfigAttribute>>();
                roleFunctionMap = new HashMap<Long, Set<Long>>();
                List<RoleUrl> roleUrls = this.functionService.getRoleFunction();//获得所有角色与权限关联列表
                if (roleUrls != null) {
                    for (RoleUrl roleUrl : roleUrls) {
                        //todo init urlSecuritiesMap
                        String url = roleUrl.getUrl();
                        String roleCode = roleUrl.getRoleCode();
                        Collection<ConfigAttribute> securities = urlSecuritiesMap.get(url);
                        if (securities == null) {
                            securities = new ArrayList<ConfigAttribute>();
                            urlSecuritiesMap.put(url, securities);
                        }
                        securities.add(new SecurityConfig(roleCode));

                        //todo roleFunctionMap
                        Long roleId = roleUrl.getRoleId();
                        Long functionId = roleUrl.getFunctionId();
                        Set<Long> functionSet = roleFunctionMap.get(roleId);
                        if (null == functionSet) {
                            functionSet = new HashSet<Long>();
                            roleFunctionMap.put(roleId, functionSet);
                        }
                        functionSet.add(functionId);
                    }
                }
            }
        } catch (Exception e) {
            log.error("加载所有角色与权限的关系失败", e);
        }
    }

    public static void addSecurityConfig(String url, String roleCode) {
        ConfigAttribute configAttribute = new SecurityConfig(roleCode);
        Collection<ConfigAttribute> configAttributes = urlSecuritiesMap.get(url);
        if (null == configAttributes) {
            configAttributes = new ArrayList<ConfigAttribute>();
            urlSecuritiesMap.put(url, configAttributes);
        }
        configAttributes.add(configAttribute);
    }

    public static void removeSecurityConfig(String url, String roleCode) {
        Collection<ConfigAttribute> configAttributes = urlSecuritiesMap.get(url);
        if (null != configAttributes) {
            Iterator<ConfigAttribute> iterator = configAttributes.iterator();
            while (iterator.hasNext()) {
                ConfigAttribute configAttribute = iterator.next();
                if (roleCode.equals(configAttribute.getAttribute())) {
                    iterator.remove();
                }
            }
        }
    }

    public static void addSecurityUrl(String url) {
        if (null != urlSecuritiesMap) {
            urlSecuritiesMap.put(url, urlSecuritiesMap.get(url));
        }
    }

    public static void updateSecurityUrl(String url, String newUrl) {
        if (null != urlSecuritiesMap) {
            urlSecuritiesMap.put(newUrl, urlSecuritiesMap.get(url));
            urlSecuritiesMap.remove(url);
        }
    }

    public static void removeSecurityUrl(String url) {
        if (null != urlSecuritiesMap) {
            urlSecuritiesMap.remove(url);
        }
    }

    private static Function findFunctionInList(Long id, List<Function> list) {
        Function function = null;
        for (Function f : list) {
            if (f.getId().longValue() == id.longValue()) {
                function = f;
            }
            if (null == function && null != f.getChildren()) {
                function = findFunctionInList(id, f.getChildren());
            }
            if (null != function) {
                break;
            }
        }
        return function;
    }

    public static void addFunction(Function function) {
        if (null != functionList) {
            synchronized (functionList) {
                if (function.getParentId().longValue() == 0) {
                    functionList.add(function);
                } else {
                    Function parent = findFunctionInList(function.getParentId(), functionList);
                    if (null != parent) {
                        List<Function> list = parent.getChildren();
                        if (null == list) {
                            list = new ArrayList<Function>();
                            parent.setChildren(list);
                        }
                        list.add(function);
                    }
                }
            }
            addSecurityUrl(function.getUrl());
        }
    }

    private static boolean updateFunctionInList(Function function, List<Function> list) {
        boolean flag = false;
        for (Function f : list) {
            if (f.getId().longValue() == function.getId().longValue()) {
                if (!function.getUrl().equals(f.getUrl())) {
                    updateSecurityUrl(f.getUrl(), function.getUrl());
                }
                function.setChildren(f.getChildren());
                list.remove(f);
                list.add(function);
            }
            if (!flag && null != f.getChildren()) {
                flag = updateFunctionInList(function, f.getChildren());
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    public static void updateFunction(Function function) {
        if (null != functionList) {
            synchronized (functionList) {
                //todo 权限管理应该改为整体刷新方案
//                updateFunctionInList(function, functionList);
            }
        }
    }

    private static boolean removeFunctionByIdFromList(Long id, List<Function> list) {
        boolean flag = false;
        for (Function function : list) {
            if (id.longValue() == function.getId().longValue()) {
                removeSecurityUrl(function.getUrl());
                list.remove(function);
                flag = true;
            }
            if (!flag && null != function.getChildren()) {
                flag = removeFunctionByIdFromList(id, function.getChildren());
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    public static void removeFunctionById(Long id) {
        if (null != functionList) {
            synchronized (functionList) {
                removeFunctionByIdFromList(id, functionList);
            }
        }
    }

    public static List<Function> getFunctionList() {
        synchronized (functionList) {
            return functionList;
        }
    }
}
