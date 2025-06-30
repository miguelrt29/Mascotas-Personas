package vo;

public class PersonaVO extends Usuario {
    private String telefono;
    private String correo;

    public PersonaVO(String documento, String nombre, String telefono, String correo) {
        super(documento, nombre);
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String mostrarInformacion() {
        return "Documento: " + documento + "\nNombre: " + nombre + "\nTeléfono: " + telefono + "\nCorreo: " + correo;
    }
}
