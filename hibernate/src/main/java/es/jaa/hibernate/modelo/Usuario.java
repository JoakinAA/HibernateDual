package es.jaa.hibernate.modelo;

import java.io.*;
import java.util.*;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    private String login;

    private String password;

    @Enumerated
    private Situacion situacion;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaAlta;

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Situacion getSituacion() {
        return situacion;
    }

    public void setSituacion(Situacion situacion) {
        this.situacion = situacion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
