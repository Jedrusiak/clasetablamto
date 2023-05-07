package es.tiernoparla.dam.instituto.model;

import java.util.ArrayList;
import java.util.List;

class TestInstitutoDAO implements InstitutoDAO{

    private List<Alumno> lista = new ArrayList<>();

    @Override
    public List<Alumno> obtenerAlumnos() throws Exception {
        if(lista.size()==0){
            for(int i=0; i<5; i++){
                lista.add(new Alumno("Nombre"+i, "Apellidos"+i, i));
            }
        }
        return lista;
    }

    @Override
    public void add(Alumno a) throws Exception {
        lista.add(a);
    }
    
}
