package es.jaa.hibernate.repositorio;

import java.util.*;

import org.hibernate.*;

import es.jaa.hibernate.modelo.*;
import es.jaa.hibernate.util.*;

public class RepositorioPersona {

    public static List<Persona> consultarPersonas() {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            final List<Persona> resultados = sesion.createQuery("from Persona").list();

            return resultados;
        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando las personas: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static Persona getPersona(final Integer idPersona) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            return (Persona)sesion.createQuery("from Persona where idPersona = :idPersona")
                    .setParameter("idPersona", idPersona).uniqueResult();

        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando la persona: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static Integer crearPersona(final Persona persona) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            final Integer personaBBDD = (Integer)sesion.save(persona);

            sesion.getTransaction().commit();

            return personaBBDD;

        } catch (Exception e) {
            System.out.println("Se ha producido un error creando una persona: " + e.getMessage());
            sesion.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static void modificarPersona(final Persona persona) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            sesion.saveOrUpdate(persona);

            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Se ha producido un error creando una persona: " + e.getMessage());
            sesion.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static void eliminarPersona(final Integer idPersona) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            sesion.createQuery("delete from Usuario where idPersona = :idPersona").setParameter("idPersona", idPersona)
                    .executeUpdate();
            sesion.createQuery("delete from Direccion where persona_idPersona = :idPersona")
                    .setParameter("idPersona", idPersona).executeUpdate();
            sesion.createQuery("delete from Persona where idPersona = :idPersona").setParameter("idPersona", idPersona)
                    .executeUpdate();

            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Se ha producido un error eliminando una persona: " + e.getMessage());
            sesion.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }
}
