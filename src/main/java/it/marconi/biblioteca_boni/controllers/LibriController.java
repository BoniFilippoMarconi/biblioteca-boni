package it.marconi.biblioteca_boni.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.biblioteca_boni.domains.LibriForm;
import it.marconi.biblioteca_boni.services.LibriService;

@Controller
@RequestMapping("/")
public class LibriController {

    @Autowired
    LibriService libriService;

    @GetMapping("/libri/nuovo")
    public ModelAndView aggiungiLibro() {

        return new ModelAndView("nuovolibro").addObject("libriForm", new LibriForm());
    }

    @PostMapping("/libri/nuovo")
    public ModelAndView aggiungiLibro(@ModelAttribute LibriForm libro) {

        libriService.aggiungiLibro(libro);
        return new ModelAndView("redirect:/libri/" + libro.getIsbn()); // da cambiare!
    }

    // get generale
    @GetMapping("/libri")
    public ModelAndView mostraLibri(){
        return new ModelAndView("biblioteca").addObject("libri", libriService.getLibri());
    }

    @GetMapping("/libri/{isbn}")
    public ModelAndView mostraLibro(@PathVariable("isbn") String isbn){

        Optional<LibriForm> libro = libriService.getLibro(isbn);

        if(libro.isPresent())
            return new ModelAndView("dettaglilibro").addObject("libro", libro.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/svuotaBiblioteca")
    public ModelAndView svuotaBiblioteca(){
        
        libriService.svuota();
        return new ModelAndView("redirect:/libri");
    }

    @GetMapping("/libri/elimina/{isbn}")
    public ModelAndView eliminaLibro(@PathVariable("isbn") String isbn){

        libriService.eliminaLibro(isbn);
        return new ModelAndView("redirect:/libri");
    }

    @GetMapping("/libri/ordina/{tipo}")
    public ModelAndView ordinaLibri(@PathVariable("tipo") String tipo){

        libriService.ordinaLibri(tipo);
        return new ModelAndView("redirect:/libri");
    }
}
