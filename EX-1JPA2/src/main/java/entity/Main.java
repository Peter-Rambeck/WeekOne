/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.Instant;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author peter
 */
public class Main {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OPG2");        
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Hans", "Jørgen", Date.from(Instant.now()));
        Customer c2 = new Customer("Peter", "Parker", Date.from(Instant.now()));
        
        try {
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
        System.out.println(c1.created);
    } finally {
    em.close();
        }
    }
}
