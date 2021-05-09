package com.steph.api.reference.controllers;

import com.steph.api.controller.BasicRestController;
import com.steph.api.reference.data.FieldsRepository;
import com.steph.api.reference.entity.FieldsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FieldsController implements BasicRestController<FieldsEntity> {

    @Autowired
    private FieldsRepository fieldsRepository;

    @Override
    @RequestMapping(value = "/request/fields/{name}", method = RequestMethod.GET)
    public FieldsEntity get(@PathVariable("name") String name) {
        return fieldsRepository.getOne(name);
    }

    @Override
    @RequestMapping(value = "/request/fields/save", method = RequestMethod.POST)
    public FieldsEntity save(FieldsEntity entity) {
        return fieldsRepository.saveAndFlush(entity);
    }

    @Override
    @RequestMapping(value = "/request/fields/delete", method = RequestMethod.DELETE)
    public void delete(FieldsEntity entity) {
        fieldsRepository.delete(entity);
    }

    @Override
    @RequestMapping(value = "/request/fields", method = RequestMethod.GET)
    public Map<String, String> getOptions() {
        return fieldsRepository.findAll().stream().collect(Collectors.toMap(FieldsEntity::getName, FieldsEntity::getLabel));
    }
}
