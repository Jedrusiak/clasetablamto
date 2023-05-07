package es.tiernoparla.dam.instituto.model;

public class Alumno {
    
    public int MAYORIA_EDAD = 18;
    private String nombre;
    private String apellidos;
    private int edad;


    public Alumno(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public boolean esMayorEdad(){
        return edad >= MAYORIA_EDAD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if (obj ==null)
            return false;

        Alumno otro = (Alumno) obj;
        if(otro.edad != this.edad)
            return false;

        if(!otro.nombre.equals(this.nombre))
            return false;

        if(!otro.apellidos.equals(this.apellidos))
            return false;
            
        return true;
    }

    
    
}
