package com.smartsoft.uat.seguridad;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class EntitiManager<Entity> {

    private static final Logger LOG = Logger.getLogger(EntitiManager.class.getName());

    private Class<Entity> entityClass;

    public EntitiManager(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public Entity saveOrEdit(Entity entity, Object id) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<Entity>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Entity> cv = iterator.next();
                LOG.log(Level.SEVERE, "Error en la entidad: {0}", cv.getMessage());
                LOG.log(Level.SEVERE, "Error en la entidad: {0}", cv.getPropertyPath());
            }
        }
        if (id.equals(0)) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
        getEntityManager().flush();
        return entity;
    }

    public void deleteLogically(Entity entity) {
        getEntityManager().merge(entity);

    }

    public void deletePhysically(Entity entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Entity search(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<Entity> findListComplete() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Root<Entity> pet = cq.from(entityClass);
        cq.orderBy(getEntityManager().getCriteriaBuilder().asc(pet.get("id")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Entity> findListOrderByAsc(String name) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Root<Entity> pet = cq.from(entityClass);
        cq.orderBy(getEntityManager().getCriteriaBuilder().asc(pet.get(name)));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Entity> findListOrderByDesc(String name) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Root<Entity> pet = cq.from(entityClass);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(pet.get(name)));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Entity> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Entity> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Entity> findWithNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    public List<Entity> findWithQuery(String queryName) {
        return getEntityManager().createQuery(queryName).getResultList();
    }

    public List<Entity> findByNativeQuery(String sql) {
        return getEntityManager().createNativeQuery(sql, entityClass).getResultList();
    }

}
