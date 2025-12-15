package mycode.masabiliardspring.exceptions;

import mycode.masabiliardspring.constants.MasinaConstants;

public class NoMasinaFoundException extends RuntimeException {
    public NoMasinaFoundException() {
        super(MasinaConstants.NO_MASINA_FOUND_EXCEPTION);
    }
}
