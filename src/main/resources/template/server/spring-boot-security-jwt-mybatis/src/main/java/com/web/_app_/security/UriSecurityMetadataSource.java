package com.web.system.security;

import com.web.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UriSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleService roleService;

    private static Map<String, Collection<ConfigAttribute>> metadataSource = null;

    public void init() {
        metadataSource = this.buildMetadataSource();
    }

    public Map<String, Collection<ConfigAttribute>> buildMetadataSource() {
        Map<String, Set<String>> roleSetMap = this.roleService.getUriRoleCollection();
        if (null != roleSetMap) {
            Map<String, Collection<ConfigAttribute>> metadataSource = new HashMap<>();
            Iterator<Map.Entry<String, Set<String>>> iterator = roleSetMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Set<String>> entry = iterator.next();
                Set<String> roleSet = entry.getValue();
                if (null != roleSet) {
                    Collection<ConfigAttribute> configs = new HashSet<>();
                    for (String role : roleSet) {
                        configs.add(new SecurityConfig(role));
                    }
                    metadataSource.put(entry.getKey(), configs);
                }
            }
            return metadataSource;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (null == metadataSource) {
            this.init();
        }
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        if (null != requestUrl) {
            int index = requestUrl.indexOf("?");
            if (index != -1) {
                requestUrl = requestUrl.substring(0, index);//截取url中?前面的部分内容
            }
        }
        return metadataSource.get(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
