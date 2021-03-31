package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "packClients")
public class PackClient {

    @Id
    private String id;

    private int nbrSmallMachine;
    private int nbrMediumMachine;
    private int nbrLargeMachine;

    private EOperatingSystem operatingSystem;

    private double price;

    private int pipelines;

    @DBRef
    private Pack pack;

    @DBRef
    private User client;

    @DBRef
    private List<Machine> machines;

    public PackClient() {
    }

    public PackClient(Pack pack) {
        this.pack = pack;
    }

    public PackClient(int nbrSmallMachine, int nbrMediumMachine, int nbrLargeMachine, EOperatingSystem operatingSystem, double price, int pipelines, Pack pack, User client, List<Machine> machines) {
        this.nbrSmallMachine = nbrSmallMachine;
        this.nbrMediumMachine = nbrMediumMachine;
        this.nbrLargeMachine = nbrLargeMachine;
        this.operatingSystem = operatingSystem;
        this.price = price;
        this.pipelines = pipelines;
        this.pack = pack;
        this.client = client;
        this.machines = machines;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNbrSmallMachine() {
        return nbrSmallMachine;
    }

    public void setNbrSmallMachine(int nbrSmallMachine) {
        this.nbrSmallMachine = nbrSmallMachine;
    }

    public int getNbrMediumMachine() {
        return nbrMediumMachine;
    }

    public void setNbrMediumMachine(int nbrMediumMachine) {
        this.nbrMediumMachine = nbrMediumMachine;
    }

    public int getNbrLargeMachine() {
        return nbrLargeMachine;
    }

    public void setNbrLargeMachine(int nbrLargeMachine) {
        this.nbrLargeMachine = nbrLargeMachine;
    }

    public EOperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(EOperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPipelines() {
        return pipelines;
    }

    public void setPipelines(int pipelines) {
        this.pipelines = pipelines;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public String toString() {
        return "PackClient{" +
                "id='" + id + '\'' +
                ", nbrSmallMachine=" + nbrSmallMachine +
                ", nbrMediumMachine=" + nbrMediumMachine +
                ", nbrLargeMachine=" + nbrLargeMachine +
                ", operatingSystem=" + operatingSystem +
                ", price=" + price +
                ", pipelines=" + pipelines +
                ", pack=" + pack +
                ", client=" + client +
                ", machines=" + machines +
                '}';
    }
}
