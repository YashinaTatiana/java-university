package dto;

import exception.MobileBankException;
import model.Operation;

import java.io.Serializable;
import java.util.List;

public class OperationsInfoDto implements Serializable {

    List<Operation> operations;

    OperationsInfoDto(){}

    public OperationsInfoDto(List<Operation> operations)
            throws MobileBankException {
        setOperations(operations);
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) throws MobileBankException {
        if (null == operations) {
            throw new MobileBankException("Operation list is not defined!");
        }
        this.operations = operations;
    }

}
