package com.diba.beneficiary.service;

import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.command.ICommandHandler;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Service
@Scope("singleton")
public class InMemeoryCommandDispatcher implements ICommandDispatcher {

    private final Map<Class<? extends ICommand>, ICommandHandler<? extends ICommand>> commandHandlerMap;
//    @Autowired
    public InMemeoryCommandDispatcher(List<ICommandHandler<? extends ICommand>> commandHandlers) {
        this.commandHandlerMap = new HashMap<>();
        commandHandlers.forEach(handler -> {
            Class<? extends ICommand> commandType = resolveCommandType(handler);
            commandHandlerMap.put(commandType, handler);
        });
    }

    private <C extends ICommand> Class<C> resolveCommandType(ICommandHandler<C> handler) {
        ResolvableType resolvableType = ResolvableType.forClass(handler.getClass()).as(ICommandHandler.class);
        return (Class<C>) resolvableType.resolveGeneric(0);
    }

    @Override
    public <C extends ICommand> ServiceResult dispatch(C command) {

        ICommandHandler<C> handler = (ICommandHandler<C>) commandHandlerMap.get(command.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for command: " + command.getClass().getName());
        }
        return  handler.handle(command).join();
    }


}
