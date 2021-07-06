package manyPersonsProblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.javafaker.Faker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Faker faker = new Faker();
        fillList(personList, faker);
        personList.sort(Comparator.comparing(Person::getName));
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.valueToTree(personList);
        sendArrayNode(arrayNode);
    }

    private static void sendArrayNode(ArrayNode arrayNode) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(arrayNode);
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillList(List<Person> personList, Faker faker) {
        for (int i = 0; i < 1000; i++) {
            personList.add(new Person(faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().phoneNumber(),
                    faker.internet().emailAddress(),
                    faker.address().streetAddress(),
                    Integer.toString(faker.number().numberBetween(0, 100))));
        }
    }
}
