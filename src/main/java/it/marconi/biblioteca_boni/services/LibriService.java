package it.marconi.biblioteca_boni.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.biblioteca_boni.domains.LibriForm;

@Service
public class LibriService {

    private ArrayList<LibriForm> libri = new ArrayList<LibriForm>();

    public void aggiungiLibro(LibriForm libro){
        if(libro.getIsbn() != "")
            libri.add(libro);
    }

    public ArrayList<LibriForm> getLibri(){
        return libri;
    }

    public Optional<LibriForm> getLibro(String isbn){
        for(LibriForm l : libri){
            if(l.getIsbn().equals(isbn)){
                return Optional.of(l);
            }
        }
        return Optional.empty();
    }

    public void svuota(){
        libri.clear();
    }

    public void eliminaLibro(String isbn){
        for(int i = 0; i < libri.size(); i++){
            if(libri.get(i).getIsbn().equals(isbn)){
                libri.remove(i);
            }
        }
    }
    
    public void ordinaLibri(String tipo){
        Comparator<LibriForm> comp = (LibriForm libro1, LibriForm libro2) -> {
            switch (tipo) {
                case "titolo":
                    return libro1.getTitolo().compareTo(libro2.getTitolo());
                case "autore":
                    return libro1.getAutore().compareTo(libro2.getAutore());
                case "anno":
                    return libro1.getAnno().compareTo(libro2.getAnno());
                default:
                    // Messo il default case perché altrimenti darebbe errore ma tipo non sarà mai diverso dai 3 sopraelencati (modifiche del codice sorgente a parte)
                    return -1;
            }
        };
        libri.sort(comp);
    }
}
