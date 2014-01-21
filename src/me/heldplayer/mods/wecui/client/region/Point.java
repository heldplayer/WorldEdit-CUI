
package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.mods.wecui.client.Color;
import me.heldplayer.util.HeldCore.client.RenderHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class Point {

    public ChunkCoordinates coord;
    public Color color;

    public void render(float opacity, double offsetX, double offsetY, double offsetZ) {
        if (!this.isValid()) {
            return;
        }
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double) this.coord.posX, (double) this.coord.posY, (double) this.coord.posZ, (double) this.coord.posX + 1.0D, (double) this.coord.posY + 1.0D, (double) this.coord.posZ + 1.0D).expand(0.02D, 0.02D, 0.02D).offset(-offsetX, -offsetY, -offsetZ);

        GL11.glColor4f(this.color.red, this.color.green, this.color.blue, opacity);
        RenderHelper.drawBox(aabb);
    }

    public boolean isValid() {
        return this.coord != null;
    }

}
