package mycode.masabiliardspring.exceptions;

import mycode.masabiliardspring.constants.MasinaConstants;

public class MasinaDoesntExistException extends RuntimeException {
    public MasinaDoesntExistException() {
        super(MasinaConstants.MASINA_DOESNT_EXIST);

    }
}
