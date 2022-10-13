package gob.jmas.modelo.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Acceso implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Size(min = 2, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=true)
    String usuario;

    @Size(min = 2, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=true)
    String password;

    @Email( message = "'${validatedValue}' no es un correo valido" )
    @Column(nullable=false , unique=true)
    String email;

    @NotNull(message = "es un dato obligatorio")
    @Column(nullable = false, unique=false )
    @ColumnDefault("1")
    Boolean activo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date fechaCreacion;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0)")
    Date fechaActualizacion;

}
