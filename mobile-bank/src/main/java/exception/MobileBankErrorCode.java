package exception;

public enum MobileBankErrorCode {

    ACCOUNT_IS_REQUIRED("Account is required!"),
    ACCOUNT_ID_IS_REQUIRED("Account id is required!"),
    PHONE_IS_REQUIRED("Phone is required!"),
    AMOUNT_IS_INVALID("Amount is required!"),
    AMOUNT_NOT_ENOUGH("Account has not required sum"),
    INVALID_CURRENCY("Currency is invalid!");

    private String msg;

    MobileBankErrorCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {return msg;}

}
