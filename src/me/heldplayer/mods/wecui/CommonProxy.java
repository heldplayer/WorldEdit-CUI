
package me.heldplayer.mods.wecui;

import java.util.logging.Logger;

import me.heldplayer.util.HeldCore.HeldCoreProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy extends HeldCoreProxy {

    public static Logger log;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {}

    public Side getSide() {
        return Side.SERVER;
    }

}
