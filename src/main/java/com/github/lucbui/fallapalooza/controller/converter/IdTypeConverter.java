package com.github.lucbui.fallapalooza.controller.converter;

import com.github.lucbui.fallapalooza.model.team.IdType;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdTypeConverter implements Converter<String, IdType> {
    @Override
    public IdType convert(String source) {
        return EnumUtils.getEnumIgnoreCase(IdType.class, source);
    }
}
