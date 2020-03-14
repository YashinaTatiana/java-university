package exception;

public enum DbErrorCode {

    USER_NOT_FOUND("User is not found!"),
    USER_IS_AUTHORIZED("User is already authorized!"),
    USER_IS_NOT_AUTHORIZED("User is not authorized!"),
    USER_IS_REQUIRED("User is not defined!"),
    USER_EXISTS("User with such login is already exists!"),
    ACCOUNT_FROM_NOT_FOUND("Account <from> not found!"),
    ACCOUNT_TO_NOT_FOUND("Account <to> not found!"),
    ACCOUNT_NOT_FOUND("Account not found!"),
    AMOUNT_NOT_VALID("Amount is invalid!");

    private String msg;

    DbErrorCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {return msg;}

}
