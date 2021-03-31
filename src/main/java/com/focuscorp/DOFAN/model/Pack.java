package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packs")
public class Pack {

    @Id
    private String id;

    private EOffer offer;

    private int users;

    private String builds;

    private String support;

    private int projects;

    private String deployment;

    private String dataRetention;

    public Pack() {
    }

    public Pack(EOffer offer, int users, String builds, String support, int projects, String deployment, String dataRetention) {
        this.offer = offer;
        this.users = users;
        this.builds = builds;
        this.support = support;
        this.projects = projects;
        this.deployment = deployment;
        this.dataRetention = dataRetention;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EOffer getOffer() {
        return offer;
    }

    public void setOffer(EOffer offer) {
        this.offer = offer;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public String getBuilds() {
        return builds;
    }

    public void setBuilds(String builds) {
        this.builds = builds;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public String getDeployment() {
        return deployment;
    }

    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }

    public String getDataRetention() {
        return dataRetention;
    }

    public void setDataRetention(String dataRetention) {
        this.dataRetention = dataRetention;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id='" + id + '\'' +
                ", offer=" + offer +
                ", users=" + users +
                ", builds='" + builds + '\'' +
                ", support='" + support + '\'' +
                ", projects=" + projects +
                ", deployment='" + deployment + '\'' +
                ", dataRetention='" + dataRetention + '\'' +
                '}';
    }
}
