package br.com.sandes.cinequote.model;

public enum Genero {
    ACAO("Action", "acao"),
    COMEDIA("Comedy", "comedia"),
    ROMANCE("Romance", "romance"),
    SUSPENSE("Suspense", "suspense"),
    TERROR("Horror", "terror"),
    FICCAO("Sci-Fi", "ficcao"),
    CRIME("Crime", "crime"),
    DRAMA("Drama", "drama"),
    AVENTURA("Adventure", "aventura"),
    FANTASIA("Fantasy", "fantasia"),
    MISTERIO("Mystery", "misterio"),
    BIOGRAFIA("Biography", "biografia"),
    ANIMACAO("Animation", "animacao"),
    OUTRO("Other", "outro");

    private String generoOmdb;
    private String generoUsuario;

    Genero(String generoOmdb, String generoUsuario) {
        this.generoOmdb = generoOmdb;
        this.generoUsuario = generoUsuario;
    }

    public static Genero fromOmdb(String texto) {
        for (Genero genero : Genero.values()) {
            if (genero.generoOmdb.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        return OUTRO;
    }

    public static Genero fromUsuario(String texto) {
        for (Genero genero : Genero.values()) {
            if (genero.generoUsuario.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        return OUTRO;
    }

}

