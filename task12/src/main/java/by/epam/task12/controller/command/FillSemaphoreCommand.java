package by.epam.task12.controller.command;

import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;
import by.epam.task12.entity.factory.impl.MatrixElementsFactory;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.filler.DiagonalFiller;
import by.epam.task12.service.filler.impl.DiagonalFillerPoolSemaphore;
import by.epam.task12.service.reader.ArrayIntMaker;
import by.epam.task12.service.reader.MatrixFromFileMaker;
import by.epam.task12.service.reader.exception.IllegalFileNameException;

public class FillSemaphoreCommand implements ExecCommand {

    private MatrixFromFileMaker<MatrixElementsFactory, MatrixElements> matrixFromFileMaker
            = new MatrixFromFileMaker<>(new MatrixElementsFactory());

    private DiagonalFiller<MatrixElements> diagonalFillerPoolSemaphore = new DiagonalFillerPoolSemaphore();
    private ArrayIntMaker arrayIntMaker = new ArrayIntMaker();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (check(request, response)) return response;

        try {
            MatrixElements matrixElements = matrixFromFileMaker.make(request.getFileNameMatrix());
            int[] arr = arrayIntMaker.make(request.getFileNameArr());
            diagonalFillerPoolSemaphore.fill(matrixElements, arr);
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
        return "Fill diagonal using service with semaphore";
    }
}
