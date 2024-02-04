package com.diba.beneficiary.core.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class MessageTypeMapper {
    private static final MessageTypeMapper Instance = new MessageTypeMapper();

    private final Map<String, Optional<Class>> typeMap = new HashMap<>();
    private final Map<Class, String> typeNameMap = new HashMap<>();

    public static String toName(Class eventType) {
        return Instance.typeNameMap.computeIfAbsent(
                eventType,
                c -> c.getTypeName().replace("$", "__").replace(".", "_")
        );
    }

    public static Optional<Class> toClass(String eventTypeName) {
        return Instance.typeMap.computeIfAbsent(
                eventTypeName,
                c -> {
                    try {
                        var className = Class.forName(eventTypeName.replace("__", "$").replace("_", ".").replace("class", "").trim());
                        return Optional.of(className);
                    } catch (ClassNotFoundException e) {
                        return Optional.empty();
                    }
                }
        );
    }
}