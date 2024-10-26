package org.nevermined.effectdebugger.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils {

    private static final Vector zeroVector = new Vector();

    public static Location applyOffset(Location origin, Vector offset)
    {
        if (offset.equals(zeroVector))
            return origin;
        double yaw = Math.toRadians(origin.getYaw());
        double xOffset = offset.getX() * Math.cos(yaw) - offset.getZ() * Math.sin(yaw);
        double zOffset = offset.getX() * Math.sin(yaw) + offset.getZ() * Math.cos(yaw);
        return origin.add(xOffset, offset.getY(), zOffset);
    }

}
