package me.raider.poto.lang;

import java.util.List;
import java.util.Map;

public interface LangMessage {

    LangMessagesProvider getDefaultLang();

    Map<String, LangMessagesProvider> getLanguages();

    List<LangMessageDecorator> getDecorators();

    LangReplacementProvider getDefaultReplacementProvider();

    String get(String lang, String path, LangReplacementProvider provider);

    String get(String lang, String path);

    String get(String path);

    default String getRaw(String path) {
        return getDefaultLang().get(path);
    }

    <T extends LangReplacementValueCreator<Object>> String get(String message, String[] replaces, T... replacements);

}
