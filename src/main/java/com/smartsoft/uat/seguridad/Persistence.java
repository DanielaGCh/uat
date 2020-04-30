package com.smartsoft.uat.seguridad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Persistence {

    @PersistenceContext(unitName = "com.smartsoft_uat_war_1.0-SNAPSHOTPU")
    private EntityManager mysql;

    public EntityManager getMysql() {
        return mysql;
    }

}
