package es.tiernoparla.dam.instituto.model;

import java.util.List;

public interface InstitutoDAO {
    
    public List<Alumno> obtenerAlumnos() throws Exception;
    public void add(Alumno a) throws Exception;
}
