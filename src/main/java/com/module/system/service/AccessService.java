package com.module.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.base.security.MyInvocationSecurityMetadataSource;
import com.module.system.model.Access;
import com.module.system.model.Menu;
import com.module.system.model.Role;

@Service("accessService")
public class AccessService extends AbstractService<Access> implements IAccessService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}
	
	private Menu getMenuByMenuId(Long menuId){
		return (Menu) dao.get(Menu.class, menuId);
	}
	
	private Role getRoleByRoleId(Long roleId){
		return (Role) dao.get(Role.class, roleId);
	}
	
	public void create(Access access){
		super.create(access);
		String url = this.getMenuByMenuId(access.getMenuId()).getUrl();
		String code = this.getRoleByRoleId(access.getRoleId()).getCode();
		MyInvocationSecurityMetadataSource.addAccess(url,code);
	}

	public List<Access> getListByRoleId(Long roleId) {
		return dao.find("from Access a where a.roleId=?", roleId);
	}

	public void deleteByRoleIdAndMenuId(Long roleId, Long menuId) {
		dao.bulkUpdate("delete from Access a where a.roleId=? and a.menuId=?", new Object[]{roleId,menuId});
		String url = this.getMenuByMenuId(menuId).getUrl();
		String code = this.getRoleByRoleId(roleId).getCode();
		MyInvocationSecurityMetadataSource.removeAccess(url,code);
	}
	
}
