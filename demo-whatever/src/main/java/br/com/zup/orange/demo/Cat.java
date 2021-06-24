package br.com.zup.orange.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

class Cat {

    @JsonProperty
    final String name;

    @JsonProperty
    final int age;

    Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
