package com.hzcf.platform.api.annotation.biz;

import org.apache.commons.lang.text.ExtendedMessageFormat;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by leijiaming on 2017/2/18
 */
public class MessageReader extends ResourceBundleMessageSource {

    private final Map<ResourceBundle, Map<String, Map<Locale, MessageFormat>>> cachedBundleMessageFormats = new HashMap<>();

    public String getMessage(String key, Object... args) {
        return getMessage(key, args, Locale.getDefault());
    }

    @Override
    protected MessageFormat getMessageFormat(ResourceBundle bundle, String code, Locale locale)
            throws MissingResourceException {

        synchronized (this.cachedBundleMessageFormats) {
            Map<String, Map<Locale, MessageFormat>> codeMap = this.cachedBundleMessageFormats.get(bundle);
            Map<Locale, MessageFormat> localeMap = null;
            if (codeMap != null) {
                localeMap = codeMap.get(code);
                if (localeMap != null) {
                    MessageFormat result = localeMap.get(locale);
                    if (result != null) {
                        return result;
                    }
                }
            }

            String msg = getStringOrNull(bundle, code);
            if (msg != null) {
                if (codeMap == null) {
                    codeMap = new HashMap<>();
                    this.cachedBundleMessageFormats.put(bundle, codeMap);
                }
                if (localeMap == null) {
                    localeMap = new HashMap<>();
                    codeMap.put(code, localeMap);
                }
                msg = changeMsg(msg);
                MessageFormat result = new ExtendedMessageFormat(msg, locale);
                localeMap.put(locale, result);
                return result;
            }

            return null;
        }
    }

    private String changeMsg(String msg) {
        StringBuilder str = new StringBuilder();
        char[] chars = msg.toCharArray();
        boolean in = false;
        int index = 0;
        boolean set = false;
        for (char c : chars) {
            if (!in)
                str.append(c);
            if (c == '{') {
                in = true;
                set = true;
            }
            if (in && set) {
                str.append(index);
                set = false;
            }
            if (c == '}') {
                in = false;
                index++;
                str.append(c);
            }
        }
        return str.toString();
    }

    private String getStringOrNull(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException ex) {
            return null;
        }
    }


}
