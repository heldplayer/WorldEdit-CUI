
package me.heldplayer.mods.wecui.client.region;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class Region {

    public abstract void render(float opacity, double offsetX, double offsetY, double offsetZ);

    public abstract void setPoint(int id, int x, int y, int z);

    public abstract void setRadius(double radiusX, double radiusY, double radiusZ);

    public abstract void setMinMax(int min, int max);

}
