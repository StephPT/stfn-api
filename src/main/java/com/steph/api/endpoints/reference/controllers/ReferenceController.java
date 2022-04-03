package com.steph.api.endpoints.reference.controllers;

import com.steph.api.endpoints.controller.BasicRestController;
import com.steph.api.endpoints.reference.data.ReferenceRepository;
import com.steph.api.endpoints.reference.entity.ReferenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ReferenceController implements BasicRestController<ReferenceEntity> {

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    @RequestMapping(value = "/usw/reference/reference/{uuid}", method = RequestMethod.GET)
    public ReferenceEntity get(@PathVariable("uuid") String uuid) {
        return referenceRepository.getOne(uuid);
    }

    @Override
    @RequestMapping(value = "/usw/reference/reference/save", method = RequestMethod.POST)
    public ReferenceEntity save(@RequestBody final ReferenceEntity entity) {
        if(entity.uuid == null || entity.uuid.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            entity.setUuid(uuid);
            entity.fields.forEach(f -> {
                f.setReferenceUuid(uuid);
            });
        }
        return referenceRepository.saveAndFlush(entity);
    }

    @RequestMapping(value = "/usw/reference/reference/update", method = RequestMethod.PUT)
    public ReferenceEntity update(@RequestBody final ReferenceEntity reference) {
        referenceRepository.deleteById(reference.getUuid());
        return referenceRepository.saveAndFlush(reference);
    }

    @Override
    @RequestMapping(value = "/usw/reference/reference/delete", method = RequestMethod.DELETE)
    public void delete(ReferenceEntity entity) {
        referenceRepository.delete(entity);
    }

    @Override
    @RequestMapping(value = "/usw/reference/reference", method = RequestMethod.GET)
    public Map<String, String> getOptions() {
        return referenceRepository.findAll().stream().collect(Collectors.toMap(ReferenceEntity::getUuid, ReferenceEntity::getName));
    }
}
