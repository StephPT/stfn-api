package com.steph.api.rest.reference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steph.api.data.ReferenceDao;
import com.steph.api.entity.ReferenceEntity;
import com.steph.api.entity.ReferenceOptions;
import com.steph.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReferenceController {

    @Autowired
    ReferenceDao referenceDao;

    @Autowired
    JsonUtil<ReferenceOptions> jsonOptions;

    @Autowired
    JsonUtil<ReferenceEntity> jsonEntity;

    @RequestMapping(value = "/request/reference/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<String> request(@PathVariable("uuid") String uuid) throws JsonProcessingException {
        ResponseEntity<String> responseEntity;
        ReferenceEntity entity = referenceDao.getEntityByIdentifier(uuid);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(entity == null) {
            responseEntity = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(jsonEntity.jsonConverter(entity), headers, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/request/reference", method = RequestMethod.GET)
    public ResponseEntity<String> requestAllOptions() throws JsonProcessingException {
        ReferenceOptions options = new ReferenceOptions();
        referenceDao.getAllTypes().forEach(m -> {
            options.setOptions(m.getUuid(), m.getName());
        });
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(jsonOptions.jsonConverter(options), headers, HttpStatus.OK);
    }
}
