package org.nevermined.effectdebugger.core.parser;

import lombok.SneakyThrows;
import org.bukkit.Color;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

public class ColorParser implements StringDataParser<Color> {

    private final static Collection<String> suggestions = Arrays.stream(Color.class.getDeclaredFields())
            .filter(field -> Modifier.isStatic(field.getModifiers()))
            .map(Field::getName)
            .filter(name -> !name.equals("BIT_MASK")).toList();

    @Override
    public Collection<String> getSuggestions() {
        return suggestions;
    }

    @SneakyThrows
    @Override
    public Color getData(String string) {
        if (suggestions.contains(string))
            return (Color) Color.class.getField(string).get(null);
        else
            return Color.fromRGB(Integer.parseInt(string));
    }

}
