package org.nevermined.effectdebugger.core.parser;

import java.util.Collection;

public interface StringDataParser<T> {

     Collection<String> getSuggestions();
     T getData(String string);

}
