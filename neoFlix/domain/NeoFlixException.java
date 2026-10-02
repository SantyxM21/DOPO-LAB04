package domain;

/**
 * Write a description of class NeoFlixException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NeoFlixException extends Exception{
    public static final String TO_BE_IMPLEMENTED = "imple";
    public static final String VALUE_UNKNOWN = "The value is unknown HOL.A";
    public static final String DATA_ERROR = "data";
    public static final String CONTENT_EMPTY = "The serie has no episodes";
    
    public NeoFlixException(String message){
        super(message);
    }
    
    
}
