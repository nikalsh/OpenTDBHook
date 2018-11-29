package server.OpenTriviaDataBaseAPIHook;

/**
 *
 * @author nikalsh
 */
public enum ResponseCodes {

    SUCCESS(0),
    NO_RESULTS(1),
    INVALID_PARAMETER(2),
    TOKEN_NOT_FOUND(3),
    TOKEN_EMPTY(4);

    private final int code;

    ResponseCodes(int code) {
        this.code = code;
    }

    public int getIntCode() {
        return this.code;
    }

}
