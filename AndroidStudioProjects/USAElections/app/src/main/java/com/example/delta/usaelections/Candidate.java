package com.example.delta.usaelections;

/**
 * Created by Delta on 10-11-16.
 */
import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Candidate implements Serializable{
    private String name, party, alias;
    private int age, likes;

    public Candidate(String name, String party, String alias, int age, int likes) {
        this.name = name;
        this.party = party;
        this.alias = alias;
        this.age = age;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
