package com.jdvelez.LiterAlura_AluraChallenge.model;

public enum Lenguaje {
    CHINO("zh","Chino"),
    DANES("da","Danés"),
    HOLANDES("nl","Holandés"),
    INGLES("en","Inglés"),
    ESPERANTO("eo","Esperanto"),
    FINES("fi","Finés"),
    FRANCES("fr","Francés"),
    ALEMAN("de","Alemán"),
    GRIEGO("el","Griego"),
    HUNGARO("hu","Húngaro"),
    ITALIANO("it","italiano"),
    LATIN("la","Latín"),
    PORTUGUES("pt","Portugués"),
    ESPAÑOL("es","Español"),
    SUECO("sv","Sueco"),
    TAGALO("tl","Tagalo");

    private String lengGutendex;
    private String lengEspanol;

    Lenguaje(String lengGutendex, String lengEspanol) {
        this.lengGutendex = lengGutendex;
        this.lengEspanol = lengEspanol;
    }

    public static Lenguaje fromString(String leng){
        for(Lenguaje lenguaje : Lenguaje.values()){
            if (lenguaje.lengGutendex.equalsIgnoreCase(leng)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje encontrado: " + leng);
    }

    public static Lenguaje fromEspanol(String leng){
        for(Lenguaje lenguaje : Lenguaje.values()){
            if (lenguaje.lengEspanol.equalsIgnoreCase(leng)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje encontrado: " + leng);
    }

}
