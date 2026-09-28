package exception;

public class ArchiveProviderException extends RuntimeException {
    public ArchiveProviderException(String message) {
        super(message);
    }

    public ArchiveProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}
