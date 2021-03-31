package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "machines")
public class Machine {

    @Id
    private String id;

    private EType type;
    private int RAM;
    private int CPU;

    public Machine() {
    }

    public Machine(EType type, int RAM, int CPU) {
        this.type = type;
        this.RAM = RAM;
        this.CPU = CPU;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getCPU() {
        return CPU;
    }

    public void setCPU(int CPU) {
        this.CPU = CPU;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", RAM=" + RAM +
                ", CPU=" + CPU +
                '}';
    }
}
