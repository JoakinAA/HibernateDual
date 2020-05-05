package es.jaa.hibernate.repositorio;

import java.util.*;
import java.util.stream.*;

import org.hibernate.*;

import es.jaa.hibernate.modelo.*;
import es.jaa.hibernate.util.*;

public class RepositorioAficiones {

    public static Set<Aficion> consultarAficiones() {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            return (Set<Aficion>)sesion.createQuery("from Aficion").setCacheable(true).list().stream()
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando las aficiones: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }
}