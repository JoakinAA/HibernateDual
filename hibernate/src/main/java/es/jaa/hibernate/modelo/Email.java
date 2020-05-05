package es.jaa.hibernate.modelo;

import javax.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue
    private Integer idEmail;

    private String email;

    @ManyToOne
    @JoinColumn(name = "persona_idPersona")
    private Persona persona;

    public Email() {
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
