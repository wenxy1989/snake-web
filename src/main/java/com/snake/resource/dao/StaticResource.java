package com.snake.resource.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by wen on 2015/8/11.
 */
@Service
@Repository(value = "resource")
public class StaticResource {
    protected transient final Logger logger = LoggerFactory.getLogger(StaticResource.class);

    private Set<IResourceDao> resourceDaoSet;

    public Set<IResourceDao> getResourceDaoSet() {
        return resourceDaoSet;
    }

    public void setResourceDaoSet(Set<IResourceDao> resourceDaoSet) {
        this.resourceDaoSet = resourceDaoSet;
    }

    public void initStaticObjects() throws Exception {
        if (null != resourceDaoSet && resourceDaoSet.size() > 0) {
            for (IResourceDao resourceDao : resourceDaoSet) {
                resourceDao.initStaticObjects();
            }
        }
    }

}
