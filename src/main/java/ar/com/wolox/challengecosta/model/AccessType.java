package ar.com.wolox.challengecosta.model;

/**
 * Enum para especificar los tipos de permisos que se pueden tener en los álbumes. 
 * Existe UNKNOWN para evitar utilizar null cuando no se encuentran resultados.
 * @author naipes
 */
public enum AccessType {
	READ(1L),
	WRITE(2L),
	READ_WRITE(3L),
	UNKNOWN(0L);
	
    private Long id;

    AccessType(Long id) {
        this.id = id;
    }
	
	public static AccessType getById(Long id) {
	    for(AccessType accessType : values()) {
	        if(accessType.id.equals(id)) {
	        	return accessType;
	        }
	    }
	    return UNKNOWN;
	}
}
