package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Dades implements InDades, Serializable {
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeixi
     *
     * @param id
     * @param titol
     * @param autor
     * @param admetPrestecLlarg
     */
    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        llistaExemplars.afegir(new Exemplar(id, titol, autor, admetPrestecLlarg));
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     *
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        if(esEstudiant) llistaUsuaris.afegir(new Estudiant(email, nom, adreca));
        else llistaUsuaris.afegir(new Professor(email, nom, adreca));
    }

    /**
     * Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     *
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     */
    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);
        if(esLlarg && !exemplar.getAdmetPrestecLlarg()) throw new BiblioException("Aquest exemplar no admet préstecs llargs");
        if(!exemplar.isDisponible()) throw new BiblioException("Aquest exemplar no està disponible");

        boolean totsRetornats = true;
        Iterator<Prestec> it = llistaPrestecs.getArrayList().iterator();
        while(it.hasNext()) {
            Prestec p = it.next();
            if(usuari.equals(p.getUsuari()) && p.prestecEndarrerit()){
                totsRetornats = false;
                break;
            }
        }
        if(!totsRetornats) throw new BiblioException("Aquest usuari no pot fer préstecs, ja que en té d'endarrerits");

        if(!esLlarg && usuari.getNumPrestecsNormals() == usuari.getMaxPrestecsNormals())
            throw new BiblioException("Aquest usuari excedeix el seu límit de préstecs normals");
        else if(esLlarg && usuari.getNumPrestecsLlargs() == usuari.getMaxPrestecsLlargs())
            throw new BiblioException("Aquest usuari excedeix el seu límit de préstecs llargs");

        if(esLlarg){
            llistaPrestecs.afegir(new PrestecLlarg(exemplar, usuari, new Date()));
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() + 1);
        }
        else{
            llistaPrestecs.afegir(new PrestecNormal(exemplar, usuari, new Date()));
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() + 1);
        }
        exemplar.setDisponible(false);
    }

    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     *
     * @param position
     */
    @Override
    public void retornarPrestec(int position) throws BiblioException {
        if(position > llistaPrestecs.getSize()) throw new BiblioException("No hi ha cap préstec en aquesta posició");
        Prestec prestecARetornar = llistaPrestecs.getAt(position);
        prestecARetornar.retorna();
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        Iterator<Prestec> it = llistaPrestecs.getArrayList().iterator();
        ArrayList<Prestec> llistaNoRetornats = new ArrayList<>();
        while(it.hasNext()) {
            Prestec p = it.next();
            if(!p.getRetornat()){
                llistaNoRetornats.add(p);

            }
        }

        return llistaNoRetornats;
    }
}
