
package me.heldplayer.mods.wecui;

import net.specialattack.forge.core.ModInfo;

import org.apache.logging.log4j.Logger;

/**
 * ImRecording mod Objects
 * 
 */
public final class Objects {

    public static final String MOD_ID = "worldedit-cui";
    public static final String MOD_NAME = "WorldEdit Client User Interface";
    public static final String MOD_CHANNEL = "WECUI";
    public static final String CLIENT_PROXY = "me.heldplayer.mods.wecui.client.ClientProxy";
    public static final String SERVER_PROXY = "me.heldplayer.mods.wecui.CommonProxy";

    public static final ModInfo MOD_INFO = new ModInfo(Objects.MOD_ID, Objects.MOD_NAME);

    public static Logger log;

}
