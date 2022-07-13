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
@Table(name = "sc.teams")
@NamedQueries({
    @NamedQuery(name = "Teams.findAll", query = "SELECT t FROM Teams t"),
    @NamedQuery(name = "Teams.findById", query = "SELECT t FROM Teams t WHERE t.id = :id"),
    @NamedQuery(name = "Teams.findByTeamname", query = "SELECT t FROM Teams t WHERE t.teamname = :teamname"),
    @NamedQuery(name = "Teams.findByHomecountry", query = "SELECT t FROM Teams t WHERE t.homecountry = :homecountry"),
    @NamedQuery(name = "Teams.findByNickname", query = "SELECT t FROM Teams t WHERE t.nickname = :nickname"),
    @NamedQuery(name = "Teams.findByLeague", query = "SELECT t FROM Teams t WHERE t.league = :league"),
    @NamedQuery(name = "Teams.findByManager", query = "SELECT t FROM Teams t WHERE t.manager = :manager"),
    @NamedQuery(name = "Teams.findByCochairman", query = "SELECT t FROM Teams t WHERE t.cochairman = :cochairman"),
    @NamedQuery(name = "Teams.findByFounded", query = "SELECT t FROM Teams t WHERE t.founded = :founded")})
public class Teams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "teamname")
    private String teamname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "homecountry")
    private String homecountry;
    @Size(max = 30)
    @Column(name = "nickname")
    private String nickname;
    @Size(max = 50)
    @Column(name = "league")
    private String league;
    @Size(max = 30)
    @Column(name = "manager")
    private String manager;
    @Size(max = 30)
    @Column(name = "cochairman")
    private String cochairman;
    @Column(name = "founded")
    private Integer founded;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamid", fetch = FetchType.LAZY)
    private List<Matchresults> matchresultsList;

    public Teams() {
    }

    public Teams(Integer id) {
        this.id = id;
    }

    public Teams(String teamname, String homecountry, String nickname, String league, String manager, String cochairman, Integer founded) {
        this.teamname = teamname;
        this.homecountry = homecountry;
        this.nickname = nickname;
        this.league = league;
        this.manager = manager;
        this.cochairman = cochairman;
        this.founded = founded;
    }

    public Teams(Integer id, String teamname, String homecountry, String nickname, String league, String manager, String cochairman, Integer founded) {
        this.id = id;
        this.teamname = teamname;
        this.homecountry = homecountry;
        this.nickname = nickname;
        this.league = league;
        this.manager = manager;
        this.cochairman = cochairman;
        this.founded = founded;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getHomecountry() {
        return homecountry;
    }

    public void setHomecountry(String homecountry) {
        this.homecountry = homecountry;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCochairman() {
        return cochairman;
    }

    public void setCochairman(String cochairman) {
        this.cochairman = cochairman;
    }

    public Integer getFounded() {
        return founded;
    }

    public void setFounded(Integer founded) {
        this.founded = founded;
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
        if (!(object instanceof Teams)) {
            return false;
        }
        Teams other = (Teams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lab.matchscheduleproject.entities.Teams[ id=" + id + " ]";
    }
    
}
