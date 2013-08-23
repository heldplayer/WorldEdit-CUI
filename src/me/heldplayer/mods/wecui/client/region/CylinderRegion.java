
package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.mods.wecui.ModWECUI;
import me.heldplayer.mods.wecui.client.Color;
import me.heldplayer.util.HeldCore.MathHelper;
import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class CylinderRegion extends Region {

    private Point center;
    private double radiusX;
    private double radiusZ;
    protected int min;
    protected int max;

    public Color outline;
    public Color grid;

    public CylinderRegion() {
        this.center = new Point();
        this.center.color = ModWECUI.colorCylinderCenter.getValue();

        this.outline = ModWECUI.colorCylinderOutline.getValue();
        this.grid = ModWECUI.colorCylinderGrid.getValue();
    }

    @Override
    public void render(float opacity, double offsetX, double offsetY, double offsetZ) {
        this.center.render(opacity, offsetX, offsetY, offsetZ);

        if (this.center.isValid()) {
            GL11.glColor4f(this.outline.red, this.outline.green, this.outline.blue, opacity);
            this.renderOutline(offsetX - 0.5D, offsetY - 0.5D, offsetZ - 0.5D);

            GL11.glColor4f(this.grid.red, this.grid.green, this.grid.blue, opacity);
            this.renderGrid(offsetX - 0.5D, offsetY - 0.5D, offsetZ - 0.5D);
        }
    }

    public void renderOutline(double offsetX, double offsetY, double offsetZ) {
        for (int y = this.min; y <= this.max + 1; y++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (int i = 0; i <= 90; i++) {
                float angle = (float) i * 4.0F / 90.0F;
                double x = this.radiusX * MathHelper.cos(angle);
                double z = this.radiusZ * MathHelper.sin(angle);

                GL11.glVertex3d(this.center.coord.posX + x - offsetX, y - offsetY, this.center.coord.posZ + z - offsetZ);
            }
            GL11.glEnd();
        }
    }

    public void renderGrid(double offsetX, double offsetY, double offsetZ) {
        int radiusX = net.minecraft.util.MathHelper.ceiling_double_int(this.radiusX);
        int radiusZ = net.minecraft.util.MathHelper.ceiling_double_int(this.radiusZ);

        for (double x = -radiusX; x <= radiusX; x += 1.0D) {
            double z = this.radiusZ * Math.cos((float) Math.asin(x / this.radiusX));
            GL11.glBegin(GL11.GL_LINE_LOOP);

            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.max + 1 - offsetY, this.center.coord.posZ + z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.max + 1 - offsetY, this.center.coord.posZ - z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.min - offsetY, this.center.coord.posZ - z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.min - offsetY, this.center.coord.posZ + z - offsetZ);

            GL11.glEnd();
        }

        for (double z = -radiusZ; z <= radiusZ; z += 1.0D) {
            double x = this.radiusX * Math.sin((float) Math.acos(z / this.radiusZ));
            GL11.glBegin(GL11.GL_LINE_LOOP);

            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.max + 1 - offsetY, this.center.coord.posZ + z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX - x - offsetX, this.max + 1 - offsetY, this.center.coord.posZ + z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX - x - offsetX, this.min - offsetY, this.center.coord.posZ + z - offsetZ);
            GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.min - offsetY, this.center.coord.posZ + z - offsetZ);

            GL11.glEnd();
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        if (id == 0) {
            this.center.coord = new ChunkCoordinates(x, y, z);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {
        this.radiusX = radiusX;
        this.radiusZ = radiusZ;
        if (radiusX == 0.0D && radiusZ == 0.0D) {
            this.center.coord = null;
        }
    }

    @Override
    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
