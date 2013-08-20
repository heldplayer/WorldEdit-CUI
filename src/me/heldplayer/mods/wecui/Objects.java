
package me.heldplayer.mods.wecui;

import java.util.logging.Logger;

import me.heldplayer.util.HeldCore.ModInfo;

/**
 * ImRecording mod Objects
 * 
 */
public final class Objects {

    public static final String MOD_ID = "WorldEdit-CUI";
    public static final String MOD_NAME = "WorldEdit Client User Interface";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String CLIENT_PROXY = "me.heldplayer.mods.wecui.client.ClientProxy";
    public static final String SERVER_PROXY = "me.heldplayer.mods.wecui.CommonProxy";

    public static final ModInfo MOD_INFO = new ModInfo(MOD_ID, MOD_NAME, MOD_VERSION);

    public static Logger log;

}
