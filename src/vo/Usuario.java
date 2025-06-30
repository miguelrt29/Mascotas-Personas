package vo;

public abstract class Usuario {
    protected String documento;
    protected String nombre;

    public Usuario(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String mostrarInformacion();
}
