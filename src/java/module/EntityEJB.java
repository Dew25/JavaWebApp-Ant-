/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import entity.Duke;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jvm
 */
@Stateless
public class EntityEJB {

    @PersistenceContext(unitName = "jsfDemoPU")
    private EntityManager em;
    
    public List <Duke> findDukes(){
        TypedQuery<Duke> query = em.createNamedQuery(Duke.FIND_ALL, Duke.class);
        return query.getResultList();
    }
    
    public void setDukeNumber(Integer number){
        Duke duke=new Duke();
        duke.setNumber(number);
        em.persist(duke);
    }
    public List<Duke> getDuke(Long id){
        Query query=em.createQuery("SELECT d FROM Duke d WHERE d.id=?1");
        query.setParameter(1, id);
        List<Duke> dukes = query.getResultList();
      
        return  dukes;
    }
    public void setUserNumber(Integer number){
        User user = new User();
        user.setNumber(number);
        em.persist(user);
    }
    public List<User> getUser(Long id){
        Query query=em.createQuery("SELECT d FROM Duke d WHERE d.id=?1");
        query.setParameter(1, id);
        List<User> users = query.getResultList();
      
        return  users;
    }


}
