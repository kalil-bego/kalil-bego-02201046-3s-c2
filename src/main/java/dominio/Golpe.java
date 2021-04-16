package dominio;

public class Golpe {
    private Integer idLutadorBate;
    private Integer idLutadorApanha;

    public Golpe(Integer idLutadorBate, Integer idLutadorApanha) {
        this.idLutadorBate = idLutadorBate;
        this.idLutadorApanha = idLutadorApanha;
    }

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }
}
