package com.diba.beneficiary.service;

import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.command.ICommandHandler;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CommandDispatcher implements ICommandDispatcher {

    private final ApplicationContext _applicationContext;
    public CommandDispatcher(ApplicationContext context){
        _applicationContext = context ;
    }

    public <T extends ICommand> ServiceResult dispatch(T command) {

        ICommandHandler commandHandler = _applicationContext.getBean(ICommandHandler.class);

        return commandHandler.handle(command).join();
    }
}
