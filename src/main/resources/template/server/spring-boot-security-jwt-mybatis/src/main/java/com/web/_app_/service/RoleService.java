package com.web.system.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    public Map<String, Set<String>> getUriRoleCollection() {
        Map<String,Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("role");
        map.put("/system/role/page",set);
        map.put("/system/user/page",set);
        map.put("/system/central/page",set);
        map.put("/system/hospital/page",set);
        map.put("/system/organization/page",set);
        return map;//todo
    }

}
