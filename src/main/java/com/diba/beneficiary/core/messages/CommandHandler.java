package com.diba.beneficiary.core.messages;

import com.diba.beneficiary.core.messages.command.ICommand;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandHandler<R> implements ICommandHandler{

    private final Map<Class<? extends ICommand>, ICommandHandler<? extends ICommand>> commandHandlerMap;

    public CommandHandler(List<ICommandHandler<? extends ICommand>> commandHandlers) {
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
    public CompletableFuture<ServiceResult<R>> handle(ICommand command) {
        return null ;
    }
}
