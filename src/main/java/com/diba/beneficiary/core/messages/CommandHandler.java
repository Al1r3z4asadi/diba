package com.diba.beneficiary.core.messages;

import com.diba.beneficiary.core.messages.command.BeneficiaryCommands;
import com.diba.beneficiary.core.messages.command.ICommand;
import com.diba.beneficiary.core.messages.command.Ihandlers.ICoreCommandHandler;
import com.diba.beneficiary.core.utils.ServiceResult;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Service
@Scope("singleton")
public class CommandHandler<R> implements ICommandHandler{

    private final Map<Class<? extends ICommand>, ICoreCommandHandler<? extends ICommand>> commandHandlerMap;
    private final Map<Class<? extends ICommand>, List<Consumer<ICommand>>> routes = new HashMap<>();

    private final ListableBeanFactory beanFactory;


    public CommandHandler(ListableBeanFactory beanFactory ) {
        this.beanFactory = beanFactory;
        this.commandHandlerMap = new HashMap<>();

    }


    public <T extends ICommand> void registerHandler(Class<T> messageType, Consumer<T> handler) {
        List<Consumer<ICommand>> handlers = routes.computeIfAbsent(messageType, k -> new ArrayList<>());
        handlers.add(x -> handler.accept((T) x));
    }

    @Override
    public CompletableFuture<ServiceResult<?>> handle(ICommand command) {
        Map<String, ICoreCommandHandler> handlers = beanFactory.getBeansOfType(ICoreCommandHandler.class);
        for (ICoreCommandHandler<?> handler : handlers.values()) {
            // Check if the handler can handle the given command type
            try {
                var clazz = handler.getClass() ;
                var instane = clazz.newInstance().getClass().getMethod("handle" , command.getClass());
                CompletableFuture<ServiceResult<?>> result = (CompletableFuture<ServiceResult<?>>) instane.invoke(handler, command);
                return result;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); // Handle the exception appropriately
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }

        }
        return null ;
    }





    private static void invokeHandleMethods(List<Class<? extends ICoreCommandHandler>> handlerClasses, ICommand command) {
        for (Class<? extends ICoreCommandHandler> handlerClass : handlerClasses) {
            try {
                ICoreCommandHandler handlerInstance = handlerClass.getDeclaredConstructor().newInstance();
                Method handleMethod = handlerClass.getMethod("handle", command.getClass());
                handleMethod.invoke(handlerInstance, command);
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        }
    }


}
