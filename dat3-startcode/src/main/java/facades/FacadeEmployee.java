package facades;

import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeEmployee {

    private static FacadeEmployee instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeEmployee() {}
    
    // Singleton
    public static FacadeEmployee getFacadeEmployee(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeEmployee();
        }
        return instance;
    }
    
    // TODO
    private EntityManager getEntityManager() {
    return emf.createEntityManager();
    }

    
    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }
    
     public List<Employee> getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
        TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e WHERE e.name=:name", Employee.class);
           query.setParameter("name", name);
           return query.getResultList();  
       } finally {
           em.close();
       }
   }
    
     
    public RenameMeDTO create(RenameMeDTO rm){
        RenameMe rme = new RenameMe(rm.getDummyStr1(), rm.getDummyStr2());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RenameMeDTO(rme);
    }
    public RenameMeDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new RenameMeDTO(em.find(RenameMe.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<RenameMeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
        List<RenameMe> rms = query.getResultList();
        return RenameMeDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        // FacadeEmployee fe = getFacadeExample(emf);
        //fe.getAll().forEach(dto->System.out.println(dto));
    }

}
