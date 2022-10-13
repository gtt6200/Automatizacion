package gob.jmas.modelo.recursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Respuesta {

    private HttpStatus httpStatus;
    private String mensajeAlUsuario;
    private String mensajeTecnico;
    private Object datos;

    private static final Logger logger = LoggerFactory.getLogger(Respuesta.class);
    //Todo el detalle
    public Respuesta (HttpStatus _httpStatus, String _mensajeAlUsuario, String _mensajeTecnico, Object  _datos )
    {
        httpStatus=_httpStatus;
        mensajeAlUsuario=_mensajeAlUsuario;
        mensajeTecnico=_mensajeTecnico;
        datos=_datos;
    }
    //Respuesta Correcta
    public Respuesta ( String _mensajeAlUsuario, String _mensajeTecnico, Object _datos )
    {
        httpStatus=HttpStatus.OK;
        mensajeAlUsuario=_mensajeAlUsuario;
        mensajeTecnico=_mensajeTecnico;
        datos=_datos;
    }
    //Error
    public Respuesta ( HttpStatus _httpStatus, String _mensajeAlUsuario, String _mensajeTecnico )
    {
        httpStatus=_httpStatus;
        mensajeAlUsuario=_mensajeAlUsuario;
        mensajeTecnico=_mensajeTecnico;
        datos=null;
    }
    //Catch DataAccessException
    public Respuesta (String _mensajeAlUsuario, DataAccessException exception )
    {
        httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        mensajeAlUsuario=_mensajeAlUsuario;
        mensajeTecnico=exception.getMessage() + " -> " + exception.getMostSpecificCause();
        datos=null;
    }
    //Catch DataAccessException
    public Respuesta (String _mensajeAlUsuario, Exception exception )
    {
        httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        mensajeAlUsuario=_mensajeAlUsuario;
        mensajeTecnico="EXCEPCION "+ " -> " + exception.getMessage();
        datos=null;
    }


    public ResponseEntity<?>  getRespuesta()
    {
        Map<String, Object> response = new HashMap<>();
        response.put("mensajeAlUsuario",mensajeAlUsuario );
        response.put("mensajeTecnico",mensajeTecnico );
        if(datos!=null)
            response.put("datos",datos );
        else
            response.put("datos",null);

        switch (httpStatus)
        {
            case OK:
            {
                if(datos==null)
                {
                    logger.warn("------------" + LocalDateTime.now() + "-----------------------");
                    logger.warn("------------RESPUESTA AL USUARIO--------------------------");
                    logger.warn("----Status :" + httpStatus.toString());
                    logger.warn("----Datos  :NO SE PROPORCIONÓ INFORMACIÓN");
                    logger.warn("----Mensaje:" + mensajeAlUsuario);
                    logger.warn("----Detalle:" + mensajeTecnico);
                }
                else
                {
                    logger.info("------------" + LocalDateTime.now() + "-----------------------");
                    logger.info("------------RESPUESTA AL USUARIO--------------------------");
                    logger.info("----Status :" + httpStatus.toString());
                    logger.info("----Datos  :" + datos.toString());
                    logger.info("----Mensaje:" + mensajeAlUsuario);
                    logger.info("----Detalle:" + mensajeTecnico);
                }
                break;
            }
            case INTERNAL_SERVER_ERROR:
            {
                logger.error("------------"+ LocalDateTime.now()+"-----------------------");
                logger.error("------------RESPUESTA AL USUARIO--------------------------");
                logger.error("----Status :" +httpStatus.toString());
                logger.error("----Datos  :NO SE PROPORCIONÓ INFORMACIÓN");
                logger.error("----Mensaje:" +mensajeAlUsuario);
                logger.error("----Detalle:" +mensajeTecnico);
                break;
            }

            default: {

                logger.warn("------------" + LocalDateTime.now() + "-----------------------");
                logger.warn("------------RESPUESTA AL USUARIO--------------------------");
                logger.warn("----Status :" + httpStatus.toString());
                if (datos == null)
                    logger.warn("----Datos  :NO SE PROPORCIONÓ INFORMACIÓN");
                else
                    logger.info("----Datos  :" + datos.toString());
                logger.warn("----Mensaje:" + mensajeAlUsuario);
                logger.warn("----Detalle:" + mensajeTecnico);
                break;
            }
        }
        System.out.println("");
        System.out.println("");
        return new ResponseEntity<Map<String, Object>>(response, httpStatus);
    }

}
