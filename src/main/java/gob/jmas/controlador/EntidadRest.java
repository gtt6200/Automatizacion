package gob.jmas.controlador;

import gob.jmas.modelo.entidades.Entidad;
import gob.jmas.modelo.recursos.Respuesta;
import gob.jmas.modelo.servicios.entidad.IEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Entidad/")
public class EntidadRest {

    @Autowired
    private IEntidadService iEntidadService;

    @GetMapping("{id}")
    public ResponseEntity<?> buscarID(@PathVariable Integer id)
    {
        try {
            Entidad entidad= iEntidadService.obtener(id);
            return new Respuesta("Mensaje al usuario ", "Mensaje Tecnico",entidad).getRespuesta();


        } catch (DataAccessException excepcion) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", excepcion).getRespuesta();
        } catch (Exception e) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", e).getRespuesta();
        }
    }
    @PostMapping("nueva/")
    public ResponseEntity<?> nueva(@RequestBody Entidad entidad)
    {
        try {

            Entidad entidadnueva = iEntidadService.guardar(entidad);
            return new Respuesta("Mensaje al usuario ", "Mensaje Tecnico",entidad).getRespuesta();


        } catch (DataAccessException excepcion) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", excepcion).getRespuesta();
        } catch (Exception e) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", e).getRespuesta();
        }
    }

    @PutMapping("actualiza/")
    public ResponseEntity<?> modificar(@RequestBody Entidad entidad )
    {
        try {

            Entidad entidadnueva = iEntidadService.guardar(entidad);
            return new Respuesta("Mensaje al usuario ", "Mensaje Tecnico",entidad).getRespuesta();


        } catch (DataAccessException excepcion) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", excepcion).getRespuesta();
        } catch (Exception e) {
            return new Respuesta("No se pudieron obtener los Datos del Usuario de Acceso", e).getRespuesta();
        }
    }

    @PutMapping("borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id)
    {
        try {

            iEntidadService.eliminar(id);

            return new Respuesta("Mensaje al usuario ", "Mensaje Tecnico",null).getRespuesta();


        } catch (DataAccessException excepcion) {
            return new Respuesta("Cayo pedo", excepcion).getRespuesta();
        } catch (Exception e) {
            return new Respuesta("No pude", e).getRespuesta();
        }
    }
}
