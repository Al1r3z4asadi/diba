//package com.diba.beneficiary.temp.listener;
//
//import com.diba.beneficiary.shared.messages.events.IEvent;
//import com.diba.beneficiary.core.service.eventbus.IEventBus;
//import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
//import com.diba.beneficiary.shared.messages.utils.MessageTypeMapper;
//import com.eventstore.dbclient.ResolvedEvent;
//import org.springframework.context.ApplicationEventPublisher;
//
//public record EventListener<event extends IEvent>(
//        ApplicationEventPublisher applicationEventPublisher
//) implements IEventBus {
//    @Override
//    public <event extends IEvent> void publish(MessageEnvelope<event> event) {
//        applicationEventPublisher.publishEvent(event);
//    }
//
//    public void handleEvent(ResolvedEvent resolvedEvent) {
//        if (isEventWithEmptyData(resolvedEvent))
//            return;
//        var eventClass = MessageTypeMapper.toClass(resolvedEvent.getEvent().getEventType());
//        var streamEvent = eventClass.flatMap(c -> MessageEnvelope.of(c, resolvedEvent));
//        if (streamEvent.isEmpty()) {
//            return;
//        }
//        this.publish((MessageEnvelope<event>) streamEvent.get());
//    }
//
//    private boolean isEventWithEmptyData(ResolvedEvent resolvedEvent) {
//        if (resolvedEvent.getEvent().getEventData().length != 0) return false;
//            return true;
//    }
//
//
//
//
//}
