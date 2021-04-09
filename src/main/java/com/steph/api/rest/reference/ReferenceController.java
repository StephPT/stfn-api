package com.steph.api.rest.reference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steph.api.data.ReferenceDao;
import com.steph.api.entity.ReferenceEntity;
import com.steph.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        JsonUtil<ReferenceEntity> jsonUtil = new JsonUtil<>();
        ReferenceEntity entity = referenceDao.getEntityByName(name);
        responseEntity = new ResponseEntity<>(jsonUtil.jsonConverter(entity), new HttpHeaders(), HttpStatus.OK);
        return responseEntity;
    }

}
