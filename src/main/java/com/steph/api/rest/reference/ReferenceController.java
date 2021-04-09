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
@RequestMapping
public class ReferenceController {

    @Autowired
    ReferenceDao referenceDao;

    @RequestMapping(value = "/request/reference/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> request(@PathVariable("name") String name) throws JsonProcessingException {
        ResponseEntity<String> responseEntity;
        JsonUtil<ReferenceEntity> jsonUtil = new JsonUtil<>();
        ReferenceEntity entity = referenceDao.getEntityByName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(entity == null) {
            responseEntity = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(jsonUtil.jsonConverter(entity), headers, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/request/reference")
    public ResponseEntity<String> requestAllOptions() throws JsonProcessingException {
        ReferenceOptions options = new ReferenceOptions();
        referenceDao.getAllTypes().forEach(m -> {
            options.setOptions(m.getUuid(), m.getName());
        });
        JsonUtil<ReferenceOptions> jsonUtil = new JsonUtil<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(jsonUtil.jsonConverter(options), headers, HttpStatus.OK);
    }
}
