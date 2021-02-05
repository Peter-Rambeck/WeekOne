/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author peter
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}
    
   
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String fName, String lName) {
     Customer c = new Customer(fName, lName, Date.from(Instant.now()));
     EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally {
            em.close();
        }
    }
    
   public Customer findByID(int id) {
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class, id);
            return customer;
        }finally {
            em.close();
        }
    }
  
      
   public List<Customer> findByLastName(String name) {
       EntityManager em = emf.createEntityManager();
       try {
           TypedQuery<Customer> query = em.createQuery("SELECT customer from Customer customer WHERE customer.lastName=:name", Customer.class);
           query.setParameter("name", name);
           return query.getResultList();  
       } finally {
           em.close();
       }
   }
   
   
   public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = 
            em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
   
    public int getNumberOfCustomers(){
        int sum = 0;
        for (Customer x : allCustomers()) {
            sum += 1;
        }
        return sum;
   }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OPG2");
        CustomerFacade customerFacade = CustomerFacade.getCustomerFacade(emf);
        customerFacade.addCustomer("Ingolf", "Rock");
        customerFacade.addCustomer("Peter", "Parker");
        customerFacade.addCustomer("Clark", "Kent");
        System.out.println(customerFacade.findByID(2));
        System.out.println(customerFacade.findByLastName("Rock"));
        System.out.println(customerFacade.allCustomers());
        System.out.println(customerFacade.getNumberOfCustomers());
    }
    
    
}
