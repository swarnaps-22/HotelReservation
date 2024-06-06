package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    static List<Customer> customerList = new ArrayList<>();

    public void addCustomer(String email,String firstName,String lastName)
    {
        //Creates new customer object and adds to the customerList
        Customer customerObj = new Customer(email,firstName,lastName);
        customerList.add(customerObj);
    }
    public Customer getCustomer(String customerEmail)
    {
        // searches for matching customer email in customerList
        for(Customer customer : customerList)
        {
            if(customer.getEmail().equals(customerEmail))
            {
                System.out.println(customer);
                return customer;
            }
        }
        return null;

    }
    public List<Customer> getAllCustomer()
    {
        //Iterates through the list prints each customer details and returns the list of customers
        for(Customer customer :customerList )
        {
           // System.out.println(customer);
        }
        return customerList;
    }
    //provide a static reference//
}
