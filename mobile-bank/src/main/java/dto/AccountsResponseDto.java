package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AccountsResponseDto implements Serializable {

    private List<String> accountList;
    private String error;

    public AccountsResponseDto(){}

    public AccountsResponseDto(List<String> accountList, String error) {
        this.accountList = accountList;
        this.error = error;
    }

    public List<String> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<String> accountList) {
        this.accountList = accountList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsResponseDto that = (AccountsResponseDto) o;
        return Objects.equals(accountList, that.accountList) &&
                Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountList, error);
    }
}
