package exception;

public class MobileBankException extends Exception {

    public MobileBankException(String message) {
        super(message);
    }

    public MobileBankException(MobileBankErrorCode errorCode) {
        super(errorCode.getMsg());
    }

    public MobileBankException(DbErrorCode errorCode) {
        super(errorCode.getMsg());
    }

}
