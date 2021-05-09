package com.steph.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface BasicRestController<T> {


    T get(@PathVariable("uuid") String uuid);

    T save(@RequestBody final T entity);

    void delete(@RequestBody final T entity);

    Map<String, String> getOptions();

}
