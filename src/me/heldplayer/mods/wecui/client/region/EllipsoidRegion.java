
package me.heldplayer.mods.wecui.client.region;

import net.minecraft.util.ChunkCoordinates;

import org.lwjgl.opengl.GL11;

public class EllipsoidRegion extends Region {

    private Point center;
    private double radiusX;
    private double radiusY;
    private double radiusZ;

    public float red;
    public float green;
    public float blue;

    public float gridRed;
    public float gridGreen;
    public float gridBlue;

    public EllipsoidRegion() {
        this.center = new Point();
        this.center.red = 0.8F;
        this.center.green = 0.8F;
        this.center.blue = 0.2F;

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
            GL11.glTranslated(0.5D, 0.5D, 0.5D);

            GL11.glLineWidth(3.0F);
            GL11.glDepthFunc(GL11.GL_GEQUAL);
            GL11.glColor4f(this.red, this.green, this.blue, 0.2F);
            this.drawXZPlane();
            this.drawYZPlane();
            this.drawXYPlane();

            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glColor4f(this.red, this.green, this.blue, 0.8F);
            this.drawXZPlane();
            this.drawYZPlane();
            this.drawXYPlane();
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z) {
        if (id == 0) {
            this.center.coord = new ChunkCoordinates(x, y, z);
        }
        else if (id == 1) {
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
    public void setMinMax(int min, int max) {}

    private void drawXZPlane() {
        int yRad = (int) Math.floor(this.radiusY);
        for (int yBlock = -yRad; yBlock < yRad; yBlock++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * 6.283185307179586D / 40.0D;
                double tempX = this.radiusX * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / this.radiusY));
                double tempZ = this.radiusZ * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / this.radiusY));

                GL11.glVertex3d(this.center.coord.posX + tempX, this.center.coord.posY + yBlock, this.center.coord.posZ + tempZ);
            }

            GL11.glEnd();
        }

        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * 6.283185307179586D / 40.0D;
            double tempX = this.radiusX * Math.cos(tempTheta);
            double tempZ = this.radiusZ * Math.sin(tempTheta);

            GL11.glVertex3d(this.center.coord.posX + tempX, this.center.coord.posY, this.center.coord.posZ + tempZ);
        }

        GL11.glEnd();
    }

    private void drawYZPlane() {
        int xRad = (int) Math.floor(this.radiusX);
        for (int xBlock = -xRad; xBlock < xRad; xBlock++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * 6.283185307179586D / 40.0D;
                double tempY = this.radiusY * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / this.radiusX));
                double tempZ = this.radiusZ * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / this.radiusX));

                GL11.glVertex3d(this.center.coord.posX + xBlock, this.center.coord.posY + tempY, this.center.coord.posZ + tempZ);
            }

            GL11.glEnd();
        }

        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * 6.283185307179586D / 40.0D;
            double tempY = this.radiusY * Math.cos(tempTheta);
            double tempZ = this.radiusZ * Math.sin(tempTheta);

            GL11.glVertex3d(this.center.coord.posX, this.center.coord.posY + tempY, this.center.coord.posZ + tempZ);
        }

        GL11.glEnd();
    }

    private void drawXYPlane() {
        int zRad = (int) Math.floor(this.radiusZ);
        for (int zBlock = -zRad; zBlock < zRad; zBlock++) {
            GL11.glBegin(GL11.GL_LINE_LOOP);

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * 6.283185307179586D / 40.0D;
                double tempX = this.radiusX * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / this.radiusZ));
                double tempY = this.radiusY * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / this.radiusZ));

                GL11.glVertex3d(this.center.coord.posX + tempX, this.center.coord.posY + tempY, this.center.coord.posZ + zBlock);
            }

            GL11.glEnd();
        }

        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * 6.283185307179586D / 40.0D;
            double tempX = this.radiusX * Math.cos(tempTheta);
            double tempY = this.radiusY * Math.sin(tempTheta);

            GL11.glVertex3d(this.center.coord.posX + tempX, this.center.coord.posY + tempY, this.center.coord.posZ);
        }

        GL11.glEnd();
    }

}
