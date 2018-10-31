package com.example.merve.issues.model;

public class Person {
    private String name;
    private String age;
    private int photo_id;

    public String getName()
    {
        return this.name;
    }
    public String getAge()
    {
        return this.age;
    }
    public int getPhoto_id()
    {
        return this.photo_id;
    }

    public Person(String name,String age,int photo_id)
    {
        this.name = name;
        this.age = age;
        this.photo_id = photo_id;
    }
    public Person()
    {

    }
}
