package com.example.demo.global.exceptions.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageSourceService {

    private final MessageSource messageSource;

    private final Locale locale = LocaleContextHolder.getLocale();

    public String getLocalMessage(String messsage, Object[] args) {
        return messageSource.getMessage(messsage, args, locale);
    }

    public String getLocalMessage(String messsage) {
        return messageSource.getMessage(messsage, null, locale);
    }
}
