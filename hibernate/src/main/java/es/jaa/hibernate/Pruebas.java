package es.jaa.hibernate;

import java.util.*;

import es.jaa.hibernate.modelo.*;
import es.jaa.hibernate.repositorio.*;

public class Pruebas {

    public static void main(String[] args) {
        // final Integer idPersona = crearPersona();
        // modificarPersona(4);
        // consultarPersona(idPersona);
        // consultarPersonas();
        // eliminarPersona(1);
        System.out.println(RepositorioAficiones.consultarAficiones());
    }

    public static void consultarPersonas() {
        final List<Persona> personas = RepositorioPersona.consultarPersonas();
        if (personas.isEmpty()) {
            System.out.println("No existen resultados");
            return;
        }

        personas.stream().forEach(p -> System.out.println("Persona: " + p.getNombre() + " " + p.getApellidos()));
    }

    public static Persona consultarPersona(final Integer idPersona) {
        final Persona persona = RepositorioPersona.getPersona(idPersona);
        if (persona == null) {
            System.out.println("No existen resultados");
            return persona;
        }

        System.out.println(persona.getNombre());
        System.out.println(persona.getApellidos());
        System.out.println(persona.getDni());
        System.out.println(persona.getEdad());
        System.out.println(persona.getEstadoCivil());
        System.out.println(persona.getUsuario().getLogin());
        System.out.println(persona.getUsuario().getFechaAlta());
        System.out.println(persona.getUsuario().getSituacion());

        // TODO: Probar con lazy y sin lazy fetch
        System.out.println(persona.getDirecciones());
        System.out.println(persona.getAficiones());

        return persona;
    }

    public static Integer crearPersona() {
        final Persona persona = new Persona();
        persona.setNombre("Joaquín");
        persona.setApellidos("Álvarez Álvarez");
        persona.setDni("16578945L");
        persona.setEdad(31);
        persona.setEstadoCivil(EstadoCivil.CASADO);

        final Usuario usuario = new Usuario();
        usuario.setLogin("jaa");
        usuario.setPassword(".");
        usuario.setSituacion(Situacion.ACTIVO);
        usuario.setFechaAlta(new Date());
        usuario.setPersona(persona);
        persona.setUsuario(usuario);

        // TODO: Probar con cascade y sin cascade

        final Direccion direccion = new Direccion();
        direccion.setProvincia("Sevilla");
        direccion.setCiudad("Écija");
        direccion.setCodigoPostal("41420");
        direccion.setCalle("Calle metalurgia");
        direccion.setNumero(2);
        direccion.setPersona(persona);
        persona.setDirecciones(Arrays.asList(direccion));

        persona.setAficiones(RepositorioAficiones.consultarAficiones());

        final Integer idPersona = RepositorioPersona.crearPersona(persona);
        System.out.println("Identificador de la persona creada: " + idPersona);
        return idPersona;

    }

    public static void eliminarPersona(final Integer idPersona) {
        RepositorioPersona.eliminarPersona(idPersona);
    }

    public static void modificarPersona(final Integer idPersona) {
        System.out.println("-------------> Vamos a modificar persona");

        final Persona persona = consultarPersona(idPersona);

        persona.setNombre("Cambio de nombre");
        persona.setEstadoCivil(EstadoCivil.SOLTERO);

        final Direccion direccion = new Direccion();
        direccion.setProvincia("Sevilla");
        direccion.setCiudad("Écija");
        direccion.setCodigoPostal("41400");
        direccion.setCalle("Calle fray perico");
        direccion.setNumero(2);
        direccion.setPersona(persona);
        persona.setDirecciones(Arrays.asList(direccion));
        persona.getAficiones().removeIf(aficion -> aficion.getIdAficion() > 3);

        RepositorioPersona.modificarPersona(persona);

        consultarPersona(idPersona);
    }
}