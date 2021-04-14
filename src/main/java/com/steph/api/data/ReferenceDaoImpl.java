package com.steph.api.data;

import com.steph.api.entity.FieldsEntity;
import com.steph.api.entity.LinkedFieldsEntity;
import com.steph.api.entity.ReferenceEntity;
import com.steph.api.entity.ReferenceJson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceDaoImpl implements ReferenceDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger log = LoggerFactory.getLogger(ReferenceDaoImpl.class);

    @Override
    public ReferenceJson getEntityByIdentifier(String uuid) {
        ReferenceJson json = new ReferenceJson();
        ReferenceEntity referenceEntity = null;
        List<LinkedFieldsEntity> linkedFieldsEntity = null;
        ArrayList<FieldsEntity> fieldsEntity = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM ReferenceEntity WHERE uuid = :uuid");
            query.setParameter("uuid", uuid);
            referenceEntity = (ReferenceEntity) query.getSingleResult();
            query = session.createQuery("FROM LinkedFields WHERE :uuid = uuid");
            query.setParameter("uuid", uuid);
            linkedFieldsEntity = (List<LinkedFieldsEntity>) query.getResultList();
            for(LinkedFieldsEntity m : linkedFieldsEntity) {
                Query query1 = session.createQuery("FROM FieldsEntity WHERE name = :name");
                query1.setParameter("name", m.getName());
                fieldsEntity.add((FieldsEntity) query1.getSingleResult());
            }
//            linkedFieldsEntity.forEach(m -> {
//                Query query1 = session.createQuery("FROM FieldsEntity WHERE name = :name");
//                query1.setParameter("name", m);
//                fieldsEntity.add((FieldsEntity) query1.getSingleResult());
//            });
            session.close();
        } catch (Exception ex) {
            log.warn(ex.getLocalizedMessage());
            session.close();
        }
        return referenceJsonBuilder(referenceEntity, fieldsEntity);
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

    private ReferenceJson referenceJsonBuilder(ReferenceEntity entity, ArrayList<FieldsEntity> fieldsEntities) {
        ReferenceJson json = new ReferenceJson();
        json.setUuid(entity.getUuid());
        json.setName(entity.getName());
        json.setFormat(entity.getFormat());
        json.setFields(fieldsEntities);
        return json;
    }
}
