package com.steph.api.endpoints.reference.controllers;

import com.steph.api.endpoints.controller.BasicRestController;
import com.steph.api.endpoints.reference.data.FieldsRepository;
import com.steph.api.endpoints.reference.entity.FieldsEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FieldsController implements BasicRestController<FieldsEntity> {

    @Autowired
    private FieldsRepository fieldsRepository;

    @Override
    @RequestMapping(value = "/usw/reference/fields/{uuid}", method = RequestMethod.GET)
    public FieldsEntity get(@PathVariable("uuid") String uuid) {
        return fieldsRepository.getOne(uuid);
    }

    @Override
    @RequestMapping(value = "/usw/reference/fields/save", method = RequestMethod.POST)
    public FieldsEntity save(FieldsEntity entity) {
        if(entity.getUuid() == null || entity.getUuid().isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            entity.setUuid(uuid);
        }
        return fieldsRepository.saveAndFlush(entity);
    }

    @RequestMapping(value = "/usw/reference/fields/update", method = RequestMethod.PUT)
    public FieldsEntity update(@RequestBody final FieldsEntity field) {
        FieldsEntity currentField = fieldsRepository.getOne(field.getUuid());
        BeanUtils.copyProperties(field, currentField, "uuid");
        return fieldsRepository.saveAndFlush(currentField);
    }

    @Override
    @RequestMapping(value = "/usw/reference/fields/delete", method = RequestMethod.DELETE)
    public void delete(FieldsEntity entity) {
        fieldsRepository.delete(entity);
    }

    @Override
    @RequestMapping(value = "/usw/reference/all", method = RequestMethod.GET)
    public List<FieldsEntity> getAll() {
        return fieldsRepository.findAll();
    }

    @RequestMapping(value = "/usw/reference/fields", method = RequestMethod.GET)
    public Map<String, String> getOptions() {
        return fieldsRepository.findAll().stream().collect(Collectors.toMap(FieldsEntity::getUuid, FieldsEntity::getLabel));
    }
}
