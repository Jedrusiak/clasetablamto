package es.tiernoparla.dam.instituto.model;

public class DAOFactory {

    public static final int MODO_TEST = 0;
    public static final int MODO_SQLITE = 1;

    public static InstitutoDAO getDAO(int modo){
        switch(modo){
            case MODO_SQLITE: return new SqlLiteInstutoDAO();
            default: return new TestInstitutoDAO();
        }

    }
    
}
