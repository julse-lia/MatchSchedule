/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.matchscheduleproject.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "sc.matches")
@NamedQueries({
    @NamedQuery(name = "Matches.findAll", query = "SELECT m FROM Matches m"),
    @NamedQuery(name = "Matches.findById", query = "SELECT m FROM Matches m WHERE m.id = :id"),
    @NamedQuery(name = "Matches.findByMatchdate", query = "SELECT m FROM Matches m WHERE m.matchdate = :matchdate"),
    @NamedQuery(name = "Matches.findByVenue", query = "SELECT m FROM Matches m WHERE m.venue = :venue")})
public class Matches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "matchdate")
    private String matchdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "venue")
    private String venue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchid", fetch = FetchType.LAZY)
    private List<Matchresults> matchresultsList;

    public Matches() {
    }

    public Matches(Integer id) {
        this.id = id;
    }

    public Matches(Integer id, String matchdate, String venue) {
        this.id = id;
        this.matchdate = matchdate;
        this.venue = venue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchdate() {
        return matchdate;
    }

    public void setMatchdate(String matchdate) {
        this.matchdate = matchdate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<Matchresults> getMatchresultsList() {
        return matchresultsList;
    }

    public void setMatchresultsList(List<Matchresults> matchresultsList) {
        this.matchresultsList = matchresultsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matches)) {
            return false;
        }
        Matches other = (Matches) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lab.matchscheduleproject.entities.Matches[ id=" + id + " ]";
    }
    
}
