package org.example.exception;

public class ExceptionMaker {

    public void MakeException() throws CustomException {
        throw new CustomException("Exception 던지기");
    }

}
