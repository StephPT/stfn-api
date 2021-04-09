package com.steph.api.data;

import com.steph.api.entity.ReferenceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class ReferenceDaoImpl implements ReferenceDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger log = LoggerFactory.getLogger(ReferenceDaoImpl.class);

    @Override
    public ReferenceEntity getEntityByIdentifier(String uuid) {
        ReferenceEntity entity = null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM ReferenceEntity WHERE uuid = :uuid");
            query.setParameter("uuid", uuid);
            entity = (ReferenceEntity) query.getSingleResult();
            session.close();
        } catch (Exception ex) {
            log.warn(ex.getLocalizedMessage());
            session.close();
        }
        return entity;
    }

    @Override
    public ReferenceEntity getEntityByName(String name) {
        ReferenceEntity entity = null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM ReferenceEntity WHERE name = :name");
            query.setParameter("name", name);
            entity = (ReferenceEntity) query.getSingleResult();
            session.close();
        } catch (Exception ex) {
            log.warn(ex.getLocalizedMessage());
            session.close();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReferenceEntity> getAllTypes() {
        Session session = sessionFactory.openSession();
        List<ReferenceEntity> result = session.createQuery("FROM ReferenceEntity").list();
        session.close();
        return result;
    }

    @Override
    public void save(ReferenceEntity entity) {

    }

    @Override
    public void delete(ReferenceEntity entity) {

    }
}
