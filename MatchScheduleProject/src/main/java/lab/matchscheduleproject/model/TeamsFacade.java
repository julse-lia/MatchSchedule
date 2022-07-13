/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.matchscheduleproject.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lab.matchscheduleproject.entities.Teams;

/**
 *
 * @author Admin
 */
@Stateless
public class TeamsFacade extends AbstractFacade<Teams> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamsFacade() {
        super(Teams.class);
    }
    
}
