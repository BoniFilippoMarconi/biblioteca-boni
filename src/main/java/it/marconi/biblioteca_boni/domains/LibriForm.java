package it.marconi.biblioteca_boni.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibriForm {
    
    private String isbn;
    private String titolo;
    private String autore;
    private String genere;
    private String anno;
}
