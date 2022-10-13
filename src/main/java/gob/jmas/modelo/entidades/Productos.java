package gob.jmas.modelo.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table()
public class Productos implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    Acceso tipodeprenda;

    @Size(min = 4, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=true)
    String talla;

    @Size(min = 4, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=true)
    String temporada;

    @Size(min = 4, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=true)
    String marca;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date fechaCreacion;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0)")
    Date fechaActualizacion;

}
