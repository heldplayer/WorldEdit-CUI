package me.heldplayer.mods.wecui.client;

import net.specialattack.forge.core.config.IConfigurable;

public class Color implements IConfigurable {

    public final float red;
    public final float green;
    public final float blue;
    public final int full;

    public Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        int full = ((int) (red * 255.0F) & 0xFF) << 16;
        full |= ((int) (green * 255.0F) & 0xFF) << 8;
        full |= (int) (blue * 255.0F) & 0xFF;
        this.full = full;
    }

    public Color(int full) {
        this.full = full;
        this.red = ((full >> 16) & 0xFF) / 255.0F;
        this.green = ((full >> 8) & 0xFF) / 255.0F;
        this.blue = (full & 0xFF) / 255.0F;
    }

    @Override
    public String serialize() {
        String result = Integer.toHexString(this.full);
        while (result.length() < 6) {
            result = "0" + result;
        }
        return result;
    }

    @Override
    public IConfigurable load(String serialized) {
        try {
            int value = Integer.parseInt(serialized, 16);
            return new Color(value & 0xFFFFFF);
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getValidValues() {
        return null;
    }

}
