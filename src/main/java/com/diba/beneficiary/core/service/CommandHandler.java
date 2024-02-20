package com.diba.beneficiary.core.service;

import com.diba.beneficiary.shared.messages.command.Command;
import com.diba.beneficiary.core.service.Ihandlers.ICommandHandler;
import com.diba.beneficiary.core.service.Ihandlers.ICoreCommandHandler;
import com.diba.beneficiary.shared.ServiceResult;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Scope("singleton")
public class CommandHandler<R> implements ICommandHandler {

    private Map<String, ICoreCommandHandler> handlers ;

    private final ListableBeanFactory _beanFactory;

    public CommandHandler(ListableBeanFactory beanFactory ) {
        this._beanFactory = beanFactory ;
        this.handlers = this._beanFactory.getBeansOfType(ICoreCommandHandler.class);;
    }

    @Override
    public CompletableFuture<ServiceResult<?>> handle(Command command ) {
        for (ICoreCommandHandler<?> handler : handlers.values()) {
            try {
                var clazz = handler.getClass() ;
                var instane = clazz.newInstance().getClass().getMethod("handle" , command.getClass());
                CompletableFuture<ServiceResult<?>> result = (CompletableFuture<ServiceResult<?>>) instane.invoke(handler, command);
                return result;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException(e) ;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }

        }
        return null ;// handle sth here
    }

}
