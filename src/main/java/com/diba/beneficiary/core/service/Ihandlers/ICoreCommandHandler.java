package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.messages.command.ICommand;

public interface ICoreCommandHandler<command extends ICommand>{

    boolean canHandle(command commandType);

}
