
package me.heldplayer.mods.wecui.client.region;

import me.heldplayer.util.HeldCore.client.RenderHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class CuboidRegion extends Region {

    private Point first;
    private Point second;

    public float red;
    public float green;
    public float blue;

    public float gridRed;
    public float gridGreen;
    public float gridBlue;

    public CuboidRegion() {
        this.first = new Point();
        this.first.red = 0.2F;
        this.first.green = 0.8F;
        this.first.blue = 0.2F;

        this.second = new Point();
        this.second.red = 0.2F;
        this.second.green = 0.2F;
        this.second.blue = 0.8F;

        this.red = 0.8F;
        this.green = 0.3F;
        this.blue = 0.3F;

        this.gridRed = 0.8F;
        this.gridGreen = 0.2F;
        this.gridBlue = 0.2F;
    }

    @Override
    public void render() {
        this.first.render();
        this.second.render();

        if (this.first.isValid() && this.second.isValid()) {
            double offset = 0.03D;

            int minX = this.first.coord.posX < this.second.coord.posX ? this.first.coord.posX : this.second.coord.posX;
            int minY = this.first.coord.posY < this.second.coord.posY ? this.first.coord.posY : this.second.coord.posY;
            int minZ = this.first.coord.posZ < this.second.coord.posZ ? this.first.coord.posZ : this.second.coord.posZ;
            int maxX = this.first.coord.posX > this.second.coord.posX ? this.first.coord.posX + 1 : this.second.coord.posX + 1;
            int maxY = this.first.coord.posY > this.second.coord.posY ? this.first.coord.posY + 1 : this.second.coord.posY + 1;
            int maxZ = this.first.coord.posZ > this.second.coord.posZ ? this.first.coord.posZ + 1 : this.second.coord.posZ + 1;
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double) minX, (double) minY, (double) minZ, (double) maxX, (double) maxY, (double) maxZ).expand(offset, offset, offset);

            GL11.glLineWidth(3.0F);
            GL11.glDepthFunc(GL11.GL_GEQUAL);
            GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
            RenderHelper.drawBox(aabb);
            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
            RenderHelper.drawBox(aabb);

            GL11.glLineWidth(3.0F);
            for (int x = minX + 1; x < maxX; x++) {
                GL11.glDepthFunc(GL11.GL_GEQUAL);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.2F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) x, (double) minY - offset, (double) minZ - offset);
                GL11.glVertex3d((double) x, (double) minY - offset, (double) maxZ + offset);
                GL11.glVertex3d((double) x, (double) maxY + offset, (double) maxZ + offset);
                GL11.glVertex3d((double) x, (double) maxY + offset, (double) minZ - offset);
                GL11.glEnd();

                GL11.glDepthFunc(GL11.GL_LESS);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.8F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) x, (double) minY - offset, (double) minZ - offset);
                GL11.glVertex3d((double) x, (double) minY - offset, (double) maxZ + offset);
                GL11.glVertex3d((double) x, (double) maxY + offset, (double) maxZ + offset);
                GL11.glVertex3d((double) x, (double) maxY + offset, (double) minZ - offset);
                GL11.glEnd();
            }
            for (int y = minY + 1; y < maxY; y++) {
                GL11.glDepthFunc(GL11.GL_GEQUAL);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.2F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) minX - offset, y, (double) minZ - offset);
                GL11.glVertex3d((double) minX - offset, y, (double) maxZ + offset);
                GL11.glVertex3d((double) maxX + offset, y, (double) maxZ + offset);
                GL11.glVertex3d((double) maxX + offset, y, (double) minZ - offset);
                GL11.glEnd();

                GL11.glDepthFunc(GL11.GL_LESS);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.8F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) minX - offset, y, (double) minZ - offset);
                GL11.glVertex3d((double) minX - offset, y, (double) maxZ + offset);
                GL11.glVertex3d((double) maxX + offset, y, (double) maxZ + offset);
                GL11.glVertex3d((double) maxX + offset, y, (double) minZ - offset);
                GL11.glEnd();
            }
            for (int z = minZ + 1; z < maxZ; z++) {
                GL11.glDepthFunc(GL11.GL_GEQUAL);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.2F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) minX - offset, (double) minY - offset, z);
                GL11.glVertex3d((double) maxX + offset, (double) minY - offset, z);
                GL11.glVertex3d((double) maxX + offset, (double) maxY + offset, z);
                GL11.glVertex3d((double) minX - offset, (double) maxY + offset, z);
                GL11.glEnd();

                GL11.glDepthFunc(GL11.GL_LESS);
                GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.8F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d((double) minX - offset, (double) minY - offset, z);
                GL11.glVertex3d((double) maxX + offset, (double) minY - offset, z);
                GL11.glVertex3d((double) maxX + offset, (double) maxY + offset, z);
                GL11.glVertex3d((double) minX - offset, (double) maxY + offset, z);
                GL11.glEnd();
            }
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        if (id == 0) {
            this.first.coord = new ChunkCoordinates(x, y, z);
        }
        else if (id == 1) {
            this.second.coord = new ChunkCoordinates(x, y, z);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {}

    @Override
    public void setMinMax(int min, int max) {}

}
