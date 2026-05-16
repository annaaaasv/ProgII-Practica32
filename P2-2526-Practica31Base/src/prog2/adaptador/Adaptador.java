package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    /**
     * Guarda les dades
     * @param dstFile
     * @throws BiblioException
     */
    public void guardaDades(String dstFile) throws BiblioException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dstFile));
            oos.writeObject(dades);
            oos.close();
        } catch (IOException e) {
            throw new BiblioException("Error guardant dades: " + e.getMessage());
        }
    }

    /**
     * Carrega les dades
     * @param srcFile
     * @throws BiblioException
     */
    public void carregaDades(String srcFile) throws BiblioException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(srcFile));
            dades = (Dades) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error carregant dades: " + e.getMessage());
        }
    }

    /**
     * Recupera la llista de tots els usuaris en format text
     * @return ArrayList de Strings amb tots els usuaris
     */
    public ArrayList<String> recuperarUsuaris() {
        ArrayList<Usuari> usuaris = dades.recuperaUsuaris();
        ArrayList<String> llista = new ArrayList<>();
        Iterator<Usuari> it = usuaris.iterator();
        while (it.hasNext()) {
            Usuari u = it.next();
            llista.add(u.toString());
        }
        return llista;
    }

    /**
     * Recupera la llista de tots els exemplars en format text
     * @return ArrayList de Strings amb tots els exemplars
     */
    public ArrayList<String> recuperarExemplars() {
        ArrayList<Exemplar> exemplars = dades.recuperaExemplars();
        ArrayList<String> llista = new ArrayList<>();
        Iterator<Exemplar> it = exemplars.iterator();
        while (it.hasNext()) {
            Exemplar e = it.next();
            llista.add(e.toString());
        }
        return llista;
    }

    /**
     * Recupera la llista de tots els préstecs en format text
     * @return ArrayList de Strings amb tots els présctecs
     */
    public ArrayList<String> recuperarPrestecs() {
        ArrayList<Prestec> prestecs = dades.recuperaPrestecs();
        ArrayList<String> llista = new ArrayList<>();
        Iterator<Prestec> it = prestecs.iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            llista.add(p.toString());
        }
        return llista;
    }

    /**
     * Recupera la llista de tots els préstecs no retornats en format text
     * @return ArrayList de Strings amb tots els préstecs no retornats
     */
    public ArrayList<String> recuperarPrestecsNoRetornats() {
        ArrayList<Prestec> prestecs = dades.recuperaPrestecsNoRetornats();
        ArrayList<String> llista = new ArrayList<>();
        Iterator<Prestec> it = prestecs.iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            llista.add(p.toString());
        }
        return llista;
    }

    /**
     * Afegeix un nou exemplar
     * @param id
     * @param titol
     * @param autor
     * @param admetLlarg
     * @throws BiblioException
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetLlarg);
    }

    /**
     * Afegeix un nou usuari
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     * @throws BiblioException
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Afegeix un nou préstec
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     * @throws BiblioException
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    /**
     * Retorna un préstec
     * @param prestecPos
     * @throws BiblioException
     */
    public void retornarPrestec(int prestecPos, boolean filtrat) throws BiblioException {
        if(filtrat){
            dades.recuperaPrestecsNoRetornats().get(prestecPos).retorna();
        }else{
            dades.recuperaPrestecs().get(prestecPos).retorna();
        }
    }

}
