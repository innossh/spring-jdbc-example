package innossh.springjdbc.example.errors;

public class TransactionRollbackTestRuntimeException extends RuntimeException {

    public TransactionRollbackTestRuntimeException() {
    }

    public TransactionRollbackTestRuntimeException(String message) {
        super(message);
    }

}
