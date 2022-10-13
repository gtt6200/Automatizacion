package gob.jmas.modelo.servicios.entidad;

import gob.jmas.modelo.entidades.Entidad;
import gob.jmas.modelo.repositorios.EntidadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class EntidadService implements IEntidadService {

    @Autowired
    private EntidadDAO entidadDAO;

    @Override
    public Entidad obtener(Integer id) {
        return entidadDAO.findById(id).orElse(null);
    }

    @Override
    public Entidad guardar(Entidad entidad) {return entidadDAO.save(entidad);}

    @Override
    public Boolean eliminar(Integer id) throws Exception {

        Entidad entidadABorrar= entidadDAO.findById(id).orElse(null);
        if(entidadABorrar==null)
            throw new Exception("No ni existe");
        else
            entidadDAO.delete(entidadABorrar);
        return true;
    }
}
