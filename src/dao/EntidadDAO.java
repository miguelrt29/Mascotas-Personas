package dao;

import java.util.List;

public abstract class EntidadDAO<T> {
    public abstract boolean insertar(T obj);
    public abstract T consultar(String id);
    public abstract boolean actualizar(T obj);
    public abstract boolean eliminar(String id);
    public abstract List<T> listar();
}
