package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.mods.wecui.ModWECUI;
import me.heldplayer.mods.wecui.client.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.specialattack.forge.core.client.RenderHelper;
import org.lwjgl.opengl.GL11;

public class CuboidRegion extends Region {

    public Color outline;
    public Color grid;
    private Point first;
    private Point second;

    public CuboidRegion() {
        this.first = new Point();
        this.first.color = ModWECUI.colorCuboidPoint1.getValue();

        this.second = new Point();
        this.second.color = ModWECUI.colorCuboidPoint2.getValue();

        this.outline = ModWECUI.colorCuboidOutline.getValue();
        this.grid = ModWECUI.colorCuboidGrid.getValue();
    }

    @Override
    public void render(float opacity, double offsetX, double offsetY, double offsetZ) {
        this.first.render(opacity, offsetX, offsetY, offsetZ);
        this.second.render(opacity, offsetX, offsetY, offsetZ);

        if (this.first.isValid() && this.second.isValid()) {
            GL11.glColor4f(this.outline.red, this.outline.green, this.outline.blue, opacity);
            this.renderOutline(offsetX, offsetY, offsetZ);

            GL11.glColor4f(this.grid.red, this.grid.green, this.grid.blue, opacity);
            this.renderGrid(offsetX, offsetY, offsetZ);
        }
    }

    private void renderOutline(double offsetX, double offsetY, double offsetZ) {
        double offset = 0.03D;

        int minX = this.first.coord.posX < this.second.coord.posX ? this.first.coord.posX : this.second.coord.posX;
        int minY = this.first.coord.posY < this.second.coord.posY ? this.first.coord.posY : this.second.coord.posY;
        int minZ = this.first.coord.posZ < this.second.coord.posZ ? this.first.coord.posZ : this.second.coord.posZ;
        int maxX = this.first.coord.posX > this.second.coord.posX ? this.first.coord.posX + 1 : this.second.coord.posX + 1;
        int maxY = this.first.coord.posY > this.second.coord.posY ? this.first.coord.posY + 1 : this.second.coord.posY + 1;
        int maxZ = this.first.coord.posZ > this.second.coord.posZ ? this.first.coord.posZ + 1 : this.second.coord.posZ + 1;
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ).expand(offset, offset, offset).offset(-offsetX, -offsetY, -offsetZ);

        RenderHelper.drawBox(aabb);
    }

    private void renderGrid(double offsetX, double offsetY, double offsetZ) {
        double offset = 0.03D;

        int minX = this.first.coord.posX < this.second.coord.posX ? this.first.coord.posX : this.second.coord.posX;
        int minY = this.first.coord.posY < this.second.coord.posY ? this.first.coord.posY : this.second.coord.posY;
        int minZ = this.first.coord.posZ < this.second.coord.posZ ? this.first.coord.posZ : this.second.coord.posZ;
        int maxX = this.first.coord.posX > this.second.coord.posX ? this.first.coord.posX + 1 : this.second.coord.posX + 1;
        int maxY = this.first.coord.posY > this.second.coord.posY ? this.first.coord.posY + 1 : this.second.coord.posY + 1;
        int maxZ = this.first.coord.posZ > this.second.coord.posZ ? this.first.coord.posZ + 1 : this.second.coord.posZ + 1;

        for (int x = minX + 1; x < maxX; x++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3d(x - offsetX, minY - offset - offsetY, minZ - offset - offsetZ);
            GL11.glVertex3d(x - offsetX, minY - offset - offsetY, maxZ + offset - offsetZ);
            GL11.glVertex3d(x - offsetX, maxY + offset - offsetY, maxZ + offset - offsetZ);
            GL11.glVertex3d(x - offsetX, maxY + offset - offsetY, minZ - offset - offsetZ);
            GL11.glEnd();
        }
        for (int y = minY + 1; y < maxY; y++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3d(minX - offset - offsetX, y - offsetY, minZ - offset - offsetZ);
            GL11.glVertex3d(minX - offset - offsetX, y - offsetY, maxZ + offset - offsetZ);
            GL11.glVertex3d(maxX + offset - offsetX, y - offsetY, maxZ + offset - offsetZ);
            GL11.glVertex3d(maxX + offset - offsetX, y - offsetY, minZ - offset - offsetZ);
            GL11.glEnd();
        }
        for (int z = minZ + 1; z < maxZ; z++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3d(minX - offset - offsetX, minY - offset - offsetY, z - offsetZ);
            GL11.glVertex3d(maxX + offset - offsetX, minY - offset - offsetY, z - offsetZ);
            GL11.glVertex3d(maxX + offset - offsetX, maxY + offset - offsetY, z - offsetZ);
            GL11.glVertex3d(minX - offset - offsetX, maxY + offset - offsetY, z - offsetZ);
            GL11.glEnd();
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        if (id == 0) {
            this.first.coord = new ChunkCoordinates(x, y, z);
        } else if (id == 1) {
            this.second.coord = new ChunkCoordinates(x, y, z);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {
    }

    @Override
    public void setMinMax(int min, int max) {
    }

}
