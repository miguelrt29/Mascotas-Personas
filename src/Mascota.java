
public class Mascota extends Animal {
    private String nombre;
    private String propietario;

    public Mascota(String nombre, String propietario, String raza, String sexo) {
        super(raza, sexo);
        this.nombre = nombre;
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}

