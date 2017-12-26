package com.snake.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.base.common.dao.Dao;
import com.snake.system.model.Access;
import com.snake.system.model.Role;

/**
 * 
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    
    private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public MyInvocationSecurityMetadataSource() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    }
	public MyInvocationSecurityMetadataSource(boolean flag) {
		if(flag){
	        if(null == resourceMap || resourceMap.isEmpty()){
	        	loadResourceDefine();
			}
		}
	}
	
    @SuppressWarnings("unchecked")
    private void loadResourceDefine() {
    	if(null == resourceMap){
    		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		}
        List<Access> accesses = (List<Access>)this.dao.find("select new Access(r,m) from Access a,Menu m,Role r where a.roleId=r.id and a.menuId=m.id");
	    if(accesses!=null){
	        for(Access access:accesses){
	        	String key = access.getMenu().getUrl().trim();
	        	Collection<ConfigAttribute> attributes = resourceMap.get(key);
	        	if(null == attributes){
	        		attributes = new ArrayList<ConfigAttribute>();
	        		resourceMap.put(key, attributes);
	        	}
	        	attributes.add(new SecurityConfig(access.getRole().getCode().trim()));
	        }
        }
    }

    // According to a URL, Find out permission configuration of this URL.
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    	Collection<ConfigAttribute> attributes = null;
        String url = ((FilterInvocation)object).getRequestUrl();
        if(url.indexOf("?") > 0){
        	url = url.substring(0, url.indexOf("?"));
        }
        if(null == resourceMap || resourceMap.isEmpty()){
        	loadResourceDefine();
        }
        attributes = resourceMap.get(url.trim());
    	
        return attributes;
    }

	@SuppressWarnings("unchecked")
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Collection<ConfigAttribute> attributes = null;
		List<Role> list = dao.loadAll(Role.class);
		if(null != list && list.size() > 0){
	        attributes = new ArrayList<ConfigAttribute>(list.size());
	        for (Role role : list) {
	            attributes.add(new SecurityConfig(role.getCode()));
	        }
		}
		return attributes;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	/**
	 * 添加访问权限
	 * @param url
	 * @param roleCode
	 */
	public static void addAccess(String url,String roleCode){
		if(null != url && null != roleCode){
			Collection<ConfigAttribute> attributes = resourceMap.get(url.trim());
			if(null == attributes){
				attributes = new ArrayList<ConfigAttribute>();
				resourceMap.put(url.trim(), attributes);
			}
	        attributes.add(new SecurityConfig(roleCode.trim()));
		}
	}
	
	/**
	 * 删除访问权限
	 * @param url
	 * @param roleCode
	 */
	public static void removeAccess(String url,String roleCode){
		if(null != url && null != roleCode){
			Collection<ConfigAttribute> attributes = resourceMap.get(url.trim());
			if(null != attributes){
				for(ConfigAttribute c:attributes){
					if(c.getAttribute().equals(roleCode.trim())){
						attributes.remove(c);
					}
				}
			}
		}
	}

}