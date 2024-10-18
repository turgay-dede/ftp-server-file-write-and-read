package com.turgaydede.model.xml;


import jakarta.xml.bind.annotation.XmlElement;

public class Contact {
    private String type;
    private String number;

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
