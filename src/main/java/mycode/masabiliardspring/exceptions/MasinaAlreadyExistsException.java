package mycode.masabiliardspring.exceptions;

import mycode.masabiliardspring.constants.MasinaConstants;

public class MasinaAlreadyExistsException extends RuntimeException {
    public MasinaAlreadyExistsException() {
        super(MasinaConstants.MASINA_ALREADY_EXISTS);

    }
}
