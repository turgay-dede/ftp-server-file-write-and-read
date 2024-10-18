package com.turgaydede.strategy;

import com.turgaydede.model.xml.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;

public class XmlFileReader implements FileReaderStrategy {
    @Override
    public void readFile(InputStream inputStream) {
        System.out.println("Reading XML file: ");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Person person = (Person) unmarshaller.unmarshal(inputStream);

            System.out.println(person);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
