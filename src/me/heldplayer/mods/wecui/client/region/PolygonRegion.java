
package me.heldplayer.mods.wecui.client.region;

import java.util.ArrayList;

import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class PolygonRegion extends Region {

    private ArrayList<PointPoly> points;
    protected int min;
    protected int max;

    public float red;
    public float green;
    public float blue;

    public float gridRed;
    public float gridGreen;
    public float gridBlue;

    public PolygonRegion() {
        this.points = new ArrayList<PointPoly>();

        this.red = 0.8F;
        this.green = 0.3F;
        this.blue = 0.3F;

        this.gridRed = 0.8F;
        this.gridGreen = 0.2F;
        this.gridBlue = 0.2F;
    }

    @Override
    public void render() {
        double offset = 0.03D;

        for (PointPoly point : this.points) {
            if (point != null) {
                point.render();
            }
        }

        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d((double) point.coord.posX + 0.5D, this.min - offset, (double) point.coord.posZ + 0.5D);
            }
        }
        GL11.glEnd();

        GL11.glDepthFunc(GL11.GL_LESS);
        GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d((double) point.coord.posX + 0.5D, this.min - offset, (double) point.coord.posZ + 0.5D);
            }
        }
        GL11.glEnd();

        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d((double) point.coord.posX + 0.5D, this.max + 1.0D + offset, (double) point.coord.posZ + 0.5D);
            }
        }
        GL11.glEnd();

        GL11.glDepthFunc(GL11.GL_LESS);
        GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d((double) point.coord.posX + 0.5D, this.max + 1.0D + offset, (double) point.coord.posZ + 0.5D);
            }
        }
        GL11.glEnd();

        for (int y = this.min; y < this.max; y++) {
            GL11.glDepthFunc(GL11.GL_GEQUAL);
            GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.2F);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (PointPoly point : this.points) {
                if (point != null) {
                    GL11.glVertex3d((double) point.coord.posX + 0.5D, (double) y + 1.0D, (double) point.coord.posZ + 0.5D);
                }
            }
            GL11.glEnd();

            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.8F);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (PointPoly point : this.points) {
                if (point != null) {
                    GL11.glVertex3d((double) point.coord.posX + 0.5D, (double) y + 1.0D, (double) point.coord.posZ + 0.5D);
                }
            }
            GL11.glEnd();
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        PointPoly point = new PointPoly(this);
        point.coord = new ChunkCoordinates(x, y, z);

        point.red = 0.2F;
        point.green = 0.8F;
        point.blue = 0.8F;

        if (id < this.points.size()) {
            this.points.set(id, point);
        }
        else {
            for (int i = 0; i < id - this.points.size(); i++) {
                this.points.add(null);
            }
            this.points.add(point);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {}

    @Override
    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
