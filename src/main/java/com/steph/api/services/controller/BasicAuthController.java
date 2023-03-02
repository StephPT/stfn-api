package com.steph.api.services.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BasicAuthController<T> {

    ResponseEntity authenticate(@RequestBody final T authRequest);

    ResponseEntity logout();

}
