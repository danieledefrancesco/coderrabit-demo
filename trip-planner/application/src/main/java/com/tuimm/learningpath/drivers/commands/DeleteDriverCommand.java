package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class DeleteDriverCommand extends RequestHandler<DeleteDriverRequest, Void> {
    private final DriversRepository repository;
    public DeleteDriverCommand(DriversRepository repository) {
        super(DeleteDriverRequest.class);
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteDriverRequest request) {
        repository.findById(request.getDriverId()).delete();
        return null;
    }
}
