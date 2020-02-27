package by.epam.task12.controller.command;

import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;
import by.epam.task12.entity.factory.impl.MatrixAtomicImplFactory;
import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.filler.DiagonalFiller;
import by.epam.task12.service.filler.impl.DiagonalFillerCountDown;
import by.epam.task12.service.reader.ArrayIntMaker;
import by.epam.task12.service.reader.MatrixFromFileMaker;
import by.epam.task12.service.reader.exception.IllegalFileNameException;
import by.epam.task12.service.validator.InputValidator;

public class CountDownCommand implements ExecCommand {

    private MatrixFromFileMaker<MatrixAtomicImplFactory, MatrixAtomicImpl> matrixFromFileMaker
            = new MatrixFromFileMaker<>(new MatrixAtomicImplFactory());

    private DiagonalFiller<MatrixAtomicImpl> diagonalFiller = new DiagonalFillerCountDown();

    private ArrayIntMaker arrayIntMaker = new ArrayIntMaker();
    private InputValidator inputValidator = new InputValidator();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (check(request, response)) return response;

        try {
            MatrixAtomicImpl matrixElements = matrixFromFileMaker.make(request.getFileNameMatrix());
            int[] arr = arrayIntMaker.make(request.getFileNameArr());

            if (!inputValidator.isValid(matrixElements, arr)) {
                response.setDisplayInformation("Input is invalid, 4<=M<=6, 8<=N<=12");
            }

            diagonalFiller.fill(matrixElements, arr);
            response.setDisplayInformation("Matrix now is:\n" + matrixElements);
        } catch (IllegalFileNameException e) {
            response.setDisplayInformation("Invalid file Name.");
            return response;
        }

        return response;
    }

    private boolean check(Request request, Response response) {
        if (request.getFileNameMatrix() == null || request.getFileNameArr() == null) {
            response.setDisplayInformation("Wrong file name");
            return true;
        }
        return false;
    }

    @Override
    public String definition() {
        return "Fill diagonal using service with count down";
    }
}
