package com.steph.api.reference.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steph.api.controller.BasicRestController;
import com.steph.api.reference.data.ReferenceRepository;
import com.steph.api.reference.entity.ReferenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ReferenceController implements BasicRestController<ReferenceEntity> {

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    @RequestMapping(value = "/request/reference/{uuid}", method = RequestMethod.GET)
    public ReferenceEntity get(@PathVariable("uuid") String uuid) {
        return referenceRepository.getOne(uuid);
    }

    @Override
    @RequestMapping(value = "/request/reference/save", method = RequestMethod.POST)
    public ReferenceEntity save(@RequestBody final ReferenceEntity entity) {
        if(entity.uuid == null || entity.uuid.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            entity.setUuid(uuid);
            entity.fields.forEach(f -> {
                f.setUuid(uuid);
            });
        }
        return referenceRepository.saveAndFlush(entity);
    }

    @Override
    @RequestMapping(value = "/request/reference/delete", method = RequestMethod.DELETE)
    public void delete(ReferenceEntity entity) {
        referenceRepository.delete(entity);
    }

    @Override
    @RequestMapping(value = "/request/reference", method = RequestMethod.GET)
    public Map<String, String> getOptions() {
        return referenceRepository.findAll().stream().collect(Collectors.toMap(ReferenceEntity::getUuid, ReferenceEntity::getName));
    }
}
