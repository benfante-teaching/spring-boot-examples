package com.benfante.teaching.springbase.service;

public interface NameService {

    String getName();

    default String getName(String key) {
        return key;
    }

}
