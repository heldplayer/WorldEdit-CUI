
package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.util.HeldCore.client.RenderHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class PointPoly {

    public ChunkCoordinates coord;
    public float red;
    public float green;
    public float blue;
    private PolygonRegion region;

    public PointPoly(PolygonRegion region) {
        this.region = region;
    }

    public void render() {
        if (!this.isValid()) {
            return;
        }
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double) this.coord.posX, (double) this.region.min, (double) this.coord.posZ, (double) this.coord.posX + 1.0D, (double) this.region.max + 1.0D, (double) this.coord.posZ + 1.0D).expand(0.02D, 0.02D, 0.02D);

        GL11.glLineWidth(4.0F);
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
        RenderHelper.drawBox(aabb);
        GL11.glDepthFunc(GL11.GL_LESS);
        GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
        RenderHelper.drawBox(aabb);
    }

    public boolean isValid() {
        return this.coord != null;
    }

}
