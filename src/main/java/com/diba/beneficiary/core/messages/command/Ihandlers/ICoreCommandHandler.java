package com.diba.beneficiary.core.messages.command.Ihandlers;

import com.diba.beneficiary.core.messages.command.ICommand;

public interface ICoreCommandHandler<command extends ICommand>{

    boolean canHandle(command commandType);

}
