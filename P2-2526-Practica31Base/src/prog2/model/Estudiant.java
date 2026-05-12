package prog2.model;

public class Estudiant extends Usuari{

    /**
     * Crea un nou estudiant
     * @param email
     * @param nom
     * @param adreca
     */
    public Estudiant(String email, String nom, String adreca) {
        super(email, nom, adreca);
    }

    /**
     * @return Retorna el nombre màxim de préstecs llargs permesos
     */
    @Override
    public int getMaxPrestecsLlargs() {
        return 1;
    }

    /**
     * @return Retorna el tipus d'usuari
     */
    @Override
    public String tipusUsuari() {
        return "Estudiant";
    }

    /**
     * @return Retorna el nombre màxim de préstecs normals permesos
     */
    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }


}
