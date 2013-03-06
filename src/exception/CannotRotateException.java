package exception;

public class CannotRotateException extends Exception {
	  public CannotRotateException() { super(); }
	  public CannotRotateException(String message) { super(message); }
	  public CannotRotateException(String message, Throwable cause) { super(message, cause); }
	  public CannotRotateException(Throwable cause) { super(cause); }

}
