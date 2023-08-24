package com.httpdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/order")
public class Main {
    private static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        setCustomers();
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }

    record NewOrder(
            String givenName,
            String surname,
            String email,
            String address,
            String city,
            String province,
            String postcode,
            String country, 
            String order,
            String size) {
    }

    record OrderResponse(
        String status,
        String error
    ){
    }

    @PostMapping
    public OrderResponse addOrder(@RequestBody NewOrder order) {
        boolean[] validOrder = new boolean[3];
        boolean validateCustomer = true;
        System.out.println(order);

        for (Customer customer : customers) {
            validOrder[0] = validateName(customer, order.givenName, order.surname );
            validOrder[1] = validateEmail(customer, order.email );
            validOrder[2] = validateAddress(customer, order.address );

            for (boolean b : validOrder) {
                if (!b) {
                    validateCustomer = false;
                    continue;
                }
            }

            System.out.println(Arrays.toString(validOrder));
            
            if(validateCustomer){
                break;
            } else {
                return new OrderResponse("error", "undeliverable");
            }
        }
        
        System.out.println(validateStock(order.order, order.size));
        
        return new OrderResponse("success", "Order succesfully booked");
    }

    public static boolean validateName(Customer customer, String givenName, String surame) {
        if (customer.getGivenName().equals(givenName) && customer.getSurame().equals(surame)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateEmail(Customer customer, String email) {
        if (customer.getEmail().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateAddress(Customer customer, String address){
        if(customer.getAddress().equals(address)){
            return true;
        } else {
            return false;
        }
    }

    public static String validateStock(String order, String size){
        Stock stock = new Stock();
        Tshirt tshirt = new Tshirt();
        
        if(order.equals("bottle")  && stock.getBottle() > 0) {
            stock.setBottle(stock.getBottle() - 1);

            return "1 Bottle booked";
        } else if ( order.equals("socks") && stock.getSocks() > 0){
            stock.setSocks(stock.getSocks() - 1);

            return "1 Socks booked";
        } else if (order.equals("tshirt") ){
            switch (size) {
                case "small":
                    int small = tshirt.getSmall();

                    if(small > 0) {
                        tshirt.setSmall( small - 1);

                        return "1 small tshirt booked";
                    }

                    break;
                case "medium":
                    int medium = tshirt.getSmall();

                    if(medium > 0) {
                        tshirt.setMedium( medium - 1);

                        return "1 medium tshirt booked";
                    }
                    
                    break;
                case "large":
                    int large = tshirt.getSmall();

                    if(large > 0) {
                        tshirt.setLarge( large - 1);

                        return "1 large tshirt booked";
                    }
                    
                    break;
                case "xlarge":
                    int xlarge = tshirt.getSmall();

                    if(xlarge > 0) {
                        tshirt.setXlarge( xlarge - 1);

                        return "1 xlarge tshirt booked";
                    }
                    
                    break;
            }
        }

        return "Order not booked";
    }

    public static void setCustomers() {
        Customer customer1 = new Customer();
        customer1.setGivenName("Rick");
        customer1.setSurame("Sanchez");
        customer1.setEmail("rick@sanchez.com");
        customer1.setAddress("123 Main Street");
        customer1.setCity("Montreal");
        customer1.setProvince("Quebec");
        customer1.setPostcode("H8H 1H1");
        customer1.setCountry("Canada");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setGivenName("John");
        customer2.setSurame("Doe");
        customer2.setEmail("some@amnesia.net");
        customer2.setAddress("932 Avenue Unknown");
        customer2.setCity("Vancouver");
        customer2.setProvince("British Columbia");
        customer2.setPostcode("M5C 2E4");
        customer2.setCountry("Canada");
        customers.add(customer2);
    }
}
