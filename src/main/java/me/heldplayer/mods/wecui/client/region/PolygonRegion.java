package me.heldplayer.mods.wecui.client.region;

import java.util.ArrayList;
import me.heldplayer.mods.wecui.ModWECUI;
import me.heldplayer.mods.wecui.client.Color;
import net.minecraft.util.ChunkCoordinates;
import org.lwjgl.opengl.GL11;

public class PolygonRegion extends Region {

    public Color outline;
    public Color grid;
    protected int min;
    protected int max;
    private ArrayList<PointPoly> points;

    public PolygonRegion() {
        this.points = new ArrayList<PointPoly>();

        this.outline = ModWECUI.colorPolygonOutline.getValue();
        this.grid = ModWECUI.colorPolygonGrid.getValue();
    }

    @Override
    public void render(float opacity, double offsetX, double offsetY, double offsetZ) {

        for (PointPoly point : this.points) {
            if (point != null) {
                point.render(opacity, offsetX, offsetY, offsetZ);
            }
        }

        GL11.glColor4f(this.outline.red, this.outline.green, this.outline.blue, opacity);
        this.renderOutline(offsetX, offsetY, offsetZ);

        GL11.glColor4f(this.grid.red, this.grid.green, this.grid.blue, opacity);
        this.renderGrid(offsetX, offsetY, offsetZ);
    }

    private void renderOutline(double offsetX, double offsetY, double offsetZ) {
        double offset = 0.03D;
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d(point.coord.posX + 0.5D - offsetX, this.min - offset - offsetY, point.coord.posZ + 0.5D - offsetZ);
            }
        }
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (PointPoly point : this.points) {
            if (point != null) {
                GL11.glVertex3d(point.coord.posX + 0.5D - offsetX, this.max + 1.0D + offset - offsetY, point.coord.posZ + 0.5D - offsetZ);
            }
        }
        GL11.glEnd();
    }

    private void renderGrid(double offsetX, double offsetY, double offsetZ) {
        for (int y = this.min; y < this.max; y++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (PointPoly point : this.points) {
                if (point != null) {
                    GL11.glVertex3d(point.coord.posX + 0.5D - offsetX, y + 1.0D - offsetY, point.coord.posZ + 0.5D - offsetZ);
                }
            }
            GL11.glEnd();
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        PointPoly point = new PointPoly(this);
        point.coord = new ChunkCoordinates(x, y, z);

        point.color = ModWECUI.colorPolygonPoint.getValue();

        if (id < this.points.size()) {
            this.points.set(id, point);
        } else {
            for (int i = 0; i < id - this.points.size(); i++) {
                this.points.add(null);
            }
            this.points.add(point);
        }
    }

    @Override
    public void setRadius(double radiusX, double radiusY, double radiusZ) {
    }

    @Override
    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
