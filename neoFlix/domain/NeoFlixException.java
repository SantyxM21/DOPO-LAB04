package domain;


/**
 * Write a description of class NeoFlixException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NeoFlixException extends Exception{
    public static final String TO_BE_IMPLEMENTED = "Por ser implementada";
    public static final String VALUE_UNKNOWN = "Valor desconocido";
    public static final String DATA_ERROR = "Error de datos";
    public static final String CONTENT_EMPTY = "No hay contenido";
    public static final String UNKNOWN_VALUE = "No fue posible el calculo";
    
    public static final String NAME_ALREADY_EXISTS = "El nombre ya existe";
    public static final String NOT_NUMERIC = "Los valores no son numericos";
    public static final String UNEXPECTED_VALUE = "Los valores no son los esperados";
    public static final String EPISODE_NOT_FOUND = "El episodio no existe";
    
    public NeoFlixException(String message){
        super(message);
    }
}