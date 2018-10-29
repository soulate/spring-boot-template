package com.template.sample.service;

import org.springframework.http.ResponseEntity;

public interface SimpleService {
    public ResponseEntity getList();
    public ResponseEntity getList2();
    public ResponseEntity setData();
}
