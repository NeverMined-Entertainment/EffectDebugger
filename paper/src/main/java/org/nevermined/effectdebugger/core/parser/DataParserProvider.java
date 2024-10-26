package org.nevermined.effectdebugger.core.parser;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.Potion;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class DataParserProvider {

    private final static Map<Class<?>, StringDataParser<?>> parserMap = new HashMap<>()
    {
        { put(Material.class, new MaterialParser()); }
        { put(BlockFace.class, new BlackFaceParser()); }
        { put(Potion.class, new PotionParser()); }
        { put(Integer.class, new IntegerParser()); }
        { put(Color.class, new ColorParser()); }
    };

    public static StringDataParser<?> getDataParser(Class<?> clazz)
    {
        return parserMap.get(clazz);
    }
}