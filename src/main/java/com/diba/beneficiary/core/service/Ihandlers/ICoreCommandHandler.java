package com.diba.beneficiary.core.service.Ihandlers;

import com.diba.beneficiary.shared.messages.command.Command;
public interface ICoreCommandHandler<command extends Command>{

    boolean canHandle(command commandType);

}
