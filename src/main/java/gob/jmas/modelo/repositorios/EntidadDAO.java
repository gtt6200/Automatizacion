package gob.jmas.modelo.repositorios;

import gob.jmas.modelo.entidades.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntidadDAO extends JpaRepository<Entidad,Integer> {

    @Query(nativeQuery = true,value ="Selec * from Entidad where campo1 like%:apodo% or campo2 like%:apodo%")
    List<Entidad> entidadesQueContienenElTexto(@Param("apodo") String texto);

    List<Entidad> findByCampo1(String campo1);

    List<Entidad> findByCampo2(String campo2);
}
