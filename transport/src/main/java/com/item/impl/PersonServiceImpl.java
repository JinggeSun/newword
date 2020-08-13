package com.item.impl;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("get client : " + username);
        Person person = new Person();
        person.setUsername("张三");
        person.setAge(23);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("get client : " + person.toString());

    }
}
