package Model.Database;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Hitler
 */
@Data
public class PersistenciaLogin implements Serializable {

    private String host;
    private String base;
    private String puerto;

}
