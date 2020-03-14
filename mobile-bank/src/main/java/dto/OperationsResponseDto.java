package dto;

import model.Operation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OperationsResponseDto implements Serializable {

    private List<Operation> operations;
    private String error;

    public OperationsResponseDto() {}

    public OperationsResponseDto(List<Operation> operations, String error) {
        this.operations = operations;
        this.error = error;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
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
        OperationsResponseDto that = (OperationsResponseDto) o;
        return Objects.equals(operations, that.operations) &&
                Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations, error);
    }
}
