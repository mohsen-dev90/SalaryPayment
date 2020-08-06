package core;

public class NotEnoughAmountsException extends Exception{

    public static final long serialVersionUID = Long.MAX_VALUE - 1000 ;
    public NotEnoughAmountsException(String message){ super(message);}
}
