package com.steph.api.endpoints.resource.contollers;

import com.steph.api.endpoints.resource.data.DirectoriesRepository;
import com.steph.api.endpoints.resource.entity.DirectoriesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DirectoriesController implements BasicRestController<DirectoriesEntity> {

    @Autowired
    private DirectoriesRepository repository;

    @RequestMapping(value = "/stfn/diretory/{uuid}", method = RequestMethod.GET)
    public DirectoriesEntity get(@PathVariable("uuid") String uuid) {
        return repository.getOne(uuid);
    }

    @RequestMapping(value = "/stfn/diretories", method = RequestMethod.GET)
    public List<DirectoriesEntity> getAll() {
        return repository.findAll();
    }

}
