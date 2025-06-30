package vo;

public class MascotaVO {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String documentoDueno;

    public MascotaVO(int id, String nombre, String especie, String raza, int edad, String documentoDueno) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.documentoDueno = documentoDueno;
    }

    public MascotaVO(String nombre, String especie, String raza, int edad, String documentoDueno) {
        this(0, nombre, especie, raza, edad, documentoDueno);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getDocumentoDueno() {
        return documentoDueno;
    }

    public String mostrarInfo(String nombreDueno) {
        return "ID: " + id + "\nNombre: " + nombre + "\nEspecie: " + especie +
               "\nRaza: " + raza + "\nEdad: " + edad + "\nDueño: " + nombreDueno;
    }
}
