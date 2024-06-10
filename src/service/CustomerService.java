package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService {

    private  static CustomerService customerService = null;

     private CustomerService()
     {

     }
     public static CustomerService getCustomerServiceInstance()
     {

         if(customerService == null)
         {
             customerService = new CustomerService();
             return customerService;
         }

         return customerService;
     }


        static Set<Customer> customerList = new HashSet<>();

        public void addCustomer(String email,String firstName,String lastName)
        {
            //Creates new customer object and adds to the customerList
            Customer customerObj = new Customer(email,firstName,lastName);
            if(false==customerList.add(customerObj))
            {
                System.out.println("Duplicate customer skipped");
            }
            else
            {
                System.out.println("customer added successfully");
            }

        }
        public Customer getCustomer(String customerEmail)
        {
            // searches for matching customer email in customerList
            for(Customer customer : customerList)
            {
                if(customer.getEmail().equals(customerEmail))
                {
                    //System.out.println(customer.getEmail()+"  email is registered");
                    return customer;
                }

            }
            return null;

        }
        public List<Customer> getAllCustomer()
        {

            return customerList.stream().toList();
        }
            //provide a static reference//
}
