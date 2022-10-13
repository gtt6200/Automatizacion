package gob.jmas.modelo.servicios.entidad;

import gob.jmas.modelo.entidades.Entidad;

public interface IEntidadService {

    public Entidad obtener(Integer id);
    public Entidad guardar(Entidad entidad);
    public Boolean eliminar(Integer id) throws Exception;
}
