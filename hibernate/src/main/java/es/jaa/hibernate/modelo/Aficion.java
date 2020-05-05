package es.jaa.hibernate.modelo;

import javax.persistence.*;

@Entity
public class Aficion {

    @Id
    @GeneratedValue
    private Integer idAficion;

    private String aficion;

    public Aficion() {
    }

    public Integer getIdAficion() {
        return idAficion;
    }

    public void setIdAficion(Integer idAficion) {
        this.idAficion = idAficion;
    }

    public String getAficion() {
        return aficion;
    }

    public void setAficion(String aficion) {
        this.aficion = aficion;
    }
}
