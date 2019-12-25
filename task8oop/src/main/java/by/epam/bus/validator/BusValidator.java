package by.epam.bus.validator;


import by.epam.bus.entity.Bus;

public class BusValidator implements Validator<Bus> {

    private PersonValidator personValidator;

    public BusValidator() {
        personValidator = new PersonValidator();
    }

    @Override
    public boolean isValid(Bus bus) {
        return personValidator.isValid(bus.getDriver()) &&
                bus.getMillage() >= 0 &&
                bus.getBeginYear() >= 0 &&
                bus.getStamp() != null &&
                !bus.getStamp().isEmpty() &&
                bus.getTrackNumber() >= 0 &&
                bus.getBusNumber() >= 0;
    }
}
