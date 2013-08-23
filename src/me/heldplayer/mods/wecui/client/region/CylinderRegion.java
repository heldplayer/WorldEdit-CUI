
package me.heldplayer.mods.wecui.client.region;

import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class CylinderRegion extends Region {

    private Point center;
    private double radiusX;
    private double radiusZ;
    protected int min;
    protected int max;

    public float red;
    public float green;
    public float blue;

    public float gridRed;
    public float gridGreen;
    public float gridBlue;

    public CylinderRegion() {
        this.center = new Point();
        this.center.red = 0.8F;
        this.center.green = 0.2F;
        this.center.blue = 0.8F;

        this.red = 0.8F;
        this.green = 0.3F;
        this.blue = 0.3F;

        this.gridRed = 0.8F;
        this.gridGreen = 0.2F;
        this.gridBlue = 0.2F;
    }

    @Override
    public void render() {
        this.center.render();

        if (this.center.isValid()) {
            GL11.glTranslated(0.5D, 0.0D, 0.5D);

            GL11.glLineWidth(3.0F);
            GL11.glDepthFunc(GL11.GL_GEQUAL);
            GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
            this.renderBox();
            this.renderCircles();

            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
            this.renderBox();
            this.renderCircles();

            GL11.glLineWidth(3.0F);
            GL11.glDepthFunc(GL11.GL_GEQUAL);
            GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.2F);
            this.renderGrid();

            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glColor4f(this.gridRed, this.gridGreen, this.gridBlue, 0.8F);
            this.renderGrid();
        }
    }

    public void renderBox() {
        double twoPi = 6.283185307179586D;
        for (int yBlock : new int[] { this.min, this.max + 1 }) {
            GL11.glBegin(GL11.GL_LINE_LOOP);

            for (int i = 0; i <= 75; i++) {
                double tempTheta = i * twoPi / 75.0D;
                double tempX = this.radiusX * Math.cos(tempTheta);
                double tempZ = this.radiusZ * Math.sin(tempTheta);

                GL11.glVertex3d(this.center.coord.posX + tempX, yBlock, this.center.coord.posZ + tempZ);
            }
            GL11.glEnd();
        }
    }

    public void renderCircles() {
        double twoPi = 6.283185307179586D;
        for (int yBlock = this.min + 1; yBlock <= this.max; yBlock++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);

            for (int i = 0; i <= 75; i++) {
                double tempTheta = i * twoPi / 75.0D;
                double tempX = this.radiusX * Math.cos(tempTheta);
                double tempZ = this.radiusZ * Math.sin(tempTheta);

                GL11.glVertex3d(this.center.coord.posX + tempX, yBlock, this.center.coord.posZ + tempZ);
            }
            GL11.glEnd();
        }
    }

    public void renderGrid() {
        int tmaxY = this.max + 1;
        int tminY = this.min;
        int posRadiusX = (int) Math.ceil(this.radiusX);
        int negRadiusX = (int) -Math.ceil(this.radiusX);
        int posRadiusZ = (int) Math.ceil(this.radiusZ);
        int negRadiusZ = (int) -Math.ceil(this.radiusZ);

        for (double tempX = negRadiusX; tempX <= posRadiusX; tempX += 1.0D) {
            double tempZ = this.radiusZ * Math.cos(Math.asin(tempX / this.radiusX));
            GL11.glBegin(GL11.GL_LINE_LOOP);

            GL11.glVertex3d(this.center.coord.posX + tempX, tmaxY, this.center.coord.posZ + tempZ);
            GL11.glVertex3d(this.center.coord.posX + tempX, tmaxY, this.center.coord.posZ - tempZ);
            GL11.glVertex3d(this.center.coord.posX + tempX, tminY, this.center.coord.posZ - tempZ);
            GL11.glVertex3d(this.center.coord.posX + tempX, tminY, this.center.coord.posZ + tempZ);

            GL11.glEnd();
        }

        for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; tempZ += 1.0D) {
            double tempX = this.radiusX * Math.sin(Math.acos(tempZ / this.radiusZ));
            GL11.glBegin(GL11.GL_LINE_LOOP);

            GL11.glVertex3d(this.center.coord.posX + tempX, tmaxY, this.center.coord.posZ + tempZ);
            GL11.glVertex3d(this.center.coord.posX - tempX, tmaxY, this.center.coord.posZ + tempZ);
            GL11.glVertex3d(this.center.coord.posX - tempX, tminY, this.center.coord.posZ + tempZ);
            GL11.glVertex3d(this.center.coord.posX + tempX, tminY, this.center.coord.posZ + tempZ);

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
