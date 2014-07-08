package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.mods.wecui.ModWECUI;
import me.heldplayer.mods.wecui.client.Color;
import net.minecraft.util.ChunkCoordinates;
import net.specialattack.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class EllipsoidRegion extends Region {

    public Color grid;
    private Point center;
    private double radiusX;
    private double radiusY;
    private double radiusZ;

    public EllipsoidRegion() {
        this.center = new Point();
        this.center.color = ModWECUI.colorEllipsoidCenter.getValue();

        this.grid = ModWECUI.colorEllipsoidGrid.getValue();
    }

    @Override
    public void render(float opacity, double offsetX, double offsetY, double offsetZ) {
        this.center.render(opacity, offsetX, offsetY, offsetZ);

        if (this.center.isValid()) {
            GL11.glColor4f(this.grid.red, this.grid.green, this.grid.blue, opacity);
            this.drawXZPlane(offsetX - 0.5D, offsetY - 0.5D, offsetZ - 0.5D);
            this.drawYZPlane(offsetX - 0.5D, offsetY - 0.5D, offsetZ - 0.5D);
            this.drawXYPlane(offsetX - 0.5D, offsetY - 0.5D, offsetZ - 0.5D);
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        if (id == 0) {
            this.center.coord = new ChunkCoordinates(x, y, z);
        } else if (id == 1) {
            if (x == 0 && y == 0 && z == 0) {
                this.center.coord = null;
            }

            this.setRadius(x, y, z);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
    }

    @Override
    public void setMinMax(int min, int max) {
    }

    private void drawXZPlane(double offsetX, double offsetY, double offsetZ) {
        int radiusY = net.minecraft.util.MathHelper.ceiling_double_int(this.radiusY);
        for (int y = -radiusY; y < radiusY; y++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (int i = 0; i <= 90; i++) {
                float angle = i * 4.0F / 90.0F;
                double x = this.radiusX * MathHelper.cos(angle) * Math.cos(Math.asin(y / this.radiusY));
                double z = this.radiusZ * MathHelper.sin(angle) * Math.cos(Math.asin(y / this.radiusY));

                GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.center.coord.posY + y - offsetY, this.center.coord.posZ + z - offsetZ);
            }
            GL11.glEnd();
        }
    }

    private void drawYZPlane(double offsetX, double offsetY, double offsetZ) {
        int radiusX = net.minecraft.util.MathHelper.ceiling_double_int(this.radiusX);
        for (int x = -radiusX; x < radiusX; x++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (int i = 0; i <= 90; i++) {
                float angle = i * 4.0F / 90.0F;
                double y = this.radiusY * MathHelper.cos(angle) * Math.sin(Math.acos(x / this.radiusX));
                double z = this.radiusZ * MathHelper.sin(angle) * Math.sin(Math.acos(x / this.radiusX));

                GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.center.coord.posY + y - offsetY, this.center.coord.posZ + z - offsetZ);
            }
            GL11.glEnd();
        }
    }

    private void drawXYPlane(double offsetX, double offsetY, double offsetZ) {
        int radiusZ = net.minecraft.util.MathHelper.ceiling_double_int(this.radiusZ);
        for (int z = -radiusZ; z < radiusZ; z++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (int i = 0; i <= 90; i++) {
                float angle = i * 4.0F / 90.0F;
                double x = this.radiusX * MathHelper.sin(angle) * Math.sin(Math.acos(z / this.radiusZ));
                double y = this.radiusY * MathHelper.cos(angle) * Math.sin(Math.acos(z / this.radiusZ));

                GL11.glVertex3d(this.center.coord.posX + x - offsetX, this.center.coord.posY + y - offsetY, this.center.coord.posZ + z - offsetZ);
            }

            GL11.glEnd();
        }
    }

}
