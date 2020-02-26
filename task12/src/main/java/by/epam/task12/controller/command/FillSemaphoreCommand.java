package by.epam.task12.controller.command;

import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.entity.factory.impl.MatrixElementsFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.filler.DiagonalFillerPoolSemaphore;
import by.epam.task12.service.reader.ArrayIntMaker;
import by.epam.task12.service.reader.MarixFromFileMaker;
import by.epam.task12.service.reader.exception.IllegalFileNameException;

public class FillSemaphoreCommand implements ExecCommand {

    private MarixFromFileMaker marixFromFileMaker = new MarixFromFileMaker();
    private DiagonalFillerPoolSemaphore diagonalFillerPoolSemaphore = new DiagonalFillerPoolSemaphore();
    private ArrayIntMaker arrayIntMaker = new ArrayIntMaker();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getFileNameMatrix() == null || request.getFileNameArr() == null) {
            response.setDisplayInformation("Wrong file name");
            return response;
        }

        MatrixElements matrixElements = null;
        matrixElements = marixFromFileMaker.make(request.getFileNameMatrix());
        int[] arr = null;
        try {
            arr = arrayIntMaker.make(request.getFileNameArr());
        } catch (IllegalFileNameException e) {
            response.setDisplayInformation("Illegal file name for array.");
            return response;
        }

        // заглушка
        MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();
        try {
            matrixElements = matrixElementsFactory.create(20, 20);
            arr = new int[]{1, 2, 3, 4};
        } catch (IllegalArgsMatrixException e) {
            e.printStackTrace();
        }

        //заглушка

        diagonalFillerPoolSemaphore.fill(matrixElements, arr);

        response.setDisplayInformation("Matrix now is:\n" +
                matrixElements);

        return response;
    }

    @Override
    public String definition() {
        return "Fill diagonal using service with semaphore";
    }
}
