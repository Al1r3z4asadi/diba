//package com.diba.beneficiary.shared.messages.utils;
//
//import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
//
//import com.eventstore.dbclient.EventData;
//import com.eventstore.dbclient.ResolvedEvent;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//class MessageSerializerTest {
//
//    @Test
//    void serialize_shouldSerializeBeneficiaryWasCreatedEventToEventData() {
//        BeneficiaryEvents.BeneficiaryWasCreated event = new BeneficiaryEvents.BeneficiaryWasCreated(
//                UUID.randomUUID(),
//                "businessCode",
//                "beneficiaryNameEn",
//                "beneficiaryName",
//                List.of(1, 2, 3),
//                1,
//                new UserMetadata("userId", "userName")
//        );
//
//        EventData eventData = MessageSerializer.serialize(event);
//        assertNotNull(eventData);
//    }
//
//    @Test
//    void deserialize_shouldDeserializeEventDataToOptionalBeneficiaryWasCreatedEvent() {
//        // Arrange
//        ResolvedEvent resolvedEvent = Mockito.mock(ResolvedEvent.class);
////        when(resolvedEvent.getEvent()).thenReturn(/* Provide a RecordedEvent mock or actual instance */);
//
//        // Mock MessageTypeMapper.toClass to return BeneficiaryEvents.BeneficiaryWasCreated class
//        when(MessageTypeMapper.toClass(any())).thenReturn(Optional.of(BeneficiaryEvents.BeneficiaryWasCreated.class));
//
//        // Mock ObjectMapper.readValue to return an instance of BeneficiaryEvents.BeneficiaryWasCreated
////        MessageSerializer.mapper = Mockito.mock(MessageSerializer.mapper.getClass());
//        BeneficiaryEvents.BeneficiaryWasCreated expectedEvent = new BeneficiaryEvents.BeneficiaryWasCreated(
//                UUID.randomUUID(),
//                "businessCode",
//                "beneficiaryNameEn",
//                "beneficiaryName",
//                List.of(1, 2, 3),
//                1,
//                new UserMetadata(UUID.randomUUID().toString(), UUID.randomUUID().toString())
//        );
//        try {
//            when(MessageSerializer.mapper.readValue(any(byte[].class), any(Class.class))).thenReturn(expectedEvent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Act
//        Optional<BeneficiaryEvents.BeneficiaryWasCreated> deserializedEvent = MessageSerializer.deserialize(
//                BeneficiaryEvents.BeneficiaryWasCreated.class,
//                resolvedEvent
//        );
//
//        // Assert
//        assertTrue(deserializedEvent.isPresent());
//        assertEquals(expectedEvent, deserializedEvent.get());
//        // Add more assertions based on your specific requirements
//    }
//}
