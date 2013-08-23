
package me.heldplayer.mods.wecui;

import me.heldplayer.util.HeldCore.HeldCoreMod;
import me.heldplayer.util.HeldCore.HeldCoreProxy;
import me.heldplayer.util.HeldCore.ModInfo;
import me.heldplayer.util.HeldCore.config.Config;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = Objects.MOD_ID, name = Objects.MOD_NAME, version = Objects.MOD_VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { Objects.MOD_CHANNEL }, packetHandler = PacketHandler.class))
public class ModWECUI extends HeldCoreMod {

    @Instance(value = Objects.MOD_ID)
    public static ModWECUI instance;

    @SidedProxy(clientSide = Objects.CLIENT_PROXY, serverSide = Objects.SERVER_PROXY)
    public static CommonProxy proxy;

    // HeldCore Objects
    //public static ConfigValue<Boolean> option;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Objects.log = event.getModLog();

        // Config
        //option = new ConfigValue<Boolean>("option", Configuration.CATEGORY_GENERAL, null, Boolean.TRUE, "");
        this.config = new Config(event.getSuggestedConfigurationFile());
        //this.config.addConfigKey(option);
    }

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {}

    @Override
    public ModInfo getModInfo() {
        return Objects.MOD_INFO;
    }

    @Override
    public HeldCoreProxy getProxy() {
        return proxy;
    }

    // Silly FML

    @Override
    @EventHandler
    public void basePreInit(FMLPreInitializationEvent event) {
        super.basePreInit(event);
    }

    @Override
    @EventHandler
    public void baseInit(FMLInitializationEvent event) {
        super.baseInit(event);
    }

    @Override
    @EventHandler
    public void basePostInit(FMLPostInitializationEvent event) {
        super.basePostInit(event);
    }

}
