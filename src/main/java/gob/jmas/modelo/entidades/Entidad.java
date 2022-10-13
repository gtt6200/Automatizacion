package gob.jmas.modelo.entidades;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Size(min = 2, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=false)
    String campo1;

    @Size(min = 2, max = 30, message = "'${validatedValue}' debe contener entre {min} y {max} caracteres de longitud" )
    @Column(nullable=false , unique=false)
    String campo2;
}