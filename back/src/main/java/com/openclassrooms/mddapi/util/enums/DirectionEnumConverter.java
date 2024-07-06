package com.openclassrooms.mddapi.util.enums;

import jakarta.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DirectionEnumConverter implements Converter<String, Direction> {

    private final List<String> directions;

    public DirectionEnumConverter(){
        directions = Stream.of(Direction.values()).map(Enum::name).collect(Collectors.toList());
    }
    @Override
    public Direction convert(@Nullable String value) {
        if(value != null){
            String requestedDirection = value;
            if(directions.stream().noneMatch(d -> d.equals(requestedDirection.toUpperCase()))){
                value = Direction.ASC.name();
            }
            return Direction.valueOf(value.toUpperCase());
        }
        return Direction.ASC;
    }
}