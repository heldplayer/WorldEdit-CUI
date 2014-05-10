
package me.heldplayer.mods.wecui;

import me.heldplayer.mods.wecui.client.Color;
import net.minecraftforge.common.config.Configuration;
import net.specialattack.forge.core.ModInfo;
import net.specialattack.forge.core.SpACoreMod;
import net.specialattack.forge.core.SpACoreProxy;
import net.specialattack.forge.core.config.Config;
import net.specialattack.forge.core.config.ConfigValue;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Objects.MOD_ID, name = Objects.MOD_NAME)
public class ModWECUI extends SpACoreMod {

    @Instance(value = Objects.MOD_ID)
    public static ModWECUI instance;

    @SidedProxy(clientSide = Objects.CLIENT_PROXY, serverSide = Objects.SERVER_PROXY)
    public static CommonProxy proxy;

    public static PacketHandler packetHandler;

    // HeldCore Objects
    public static ConfigValue<Color> colorCuboidPoint1;
    public static ConfigValue<Color> colorCuboidPoint2;
    public static ConfigValue<Color> colorCuboidOutline;
    public static ConfigValue<Color> colorCuboidGrid;
    public static ConfigValue<Color> colorPolygonPoint;
    public static ConfigValue<Color> colorPolygonOutline;
    public static ConfigValue<Color> colorPolygonGrid;
    public static ConfigValue<Color> colorEllipsoidCenter;
    public static ConfigValue<Color> colorEllipsoidGrid;
    public static ConfigValue<Color> colorCylinderCenter;
    public static ConfigValue<Color> colorCylinderOutline;
    public static ConfigValue<Color> colorCylinderGrid;

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Objects.log = event.getModLog();

        ModWECUI.packetHandler = new PacketHandler("WECUI");

        // Config
        ModWECUI.colorCuboidPoint1 = new ConfigValue<Color>("colorCuboidPoint1", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.2F, 0.8F, 0.2F), "");
        ModWECUI.colorCuboidPoint2 = new ConfigValue<Color>("colorCuboidPoint2", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.2F, 0.2F, 0.8F), "");
        ModWECUI.colorCuboidOutline = new ConfigValue<Color>("colorCuboidOutline", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCuboidGrid = new ConfigValue<Color>("colorCuboidGrid", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        ModWECUI.colorPolygonPoint = new ConfigValue<Color>("colorPolygonPoint", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.2F, 0.8F, 0.8F), "");
        ModWECUI.colorPolygonOutline = new ConfigValue<Color>("colorPolygonOutline", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorPolygonGrid = new ConfigValue<Color>("colorPolygonGrid", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        ModWECUI.colorEllipsoidCenter = new ConfigValue<Color>("colorEllipsoidCenter", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.8F, 0.2F), "");
        ModWECUI.colorEllipsoidGrid = new ConfigValue<Color>("colorEllipsoidGrid", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCylinderCenter = new ConfigValue<Color>("colorCylinderCenter", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.2F, 0.8F), "");
        ModWECUI.colorCylinderOutline = new ConfigValue<Color>("colorCylinderOutline", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCylinderGrid = new ConfigValue<Color>("colorCylinderGrid", Configuration.CATEGORY_GENERAL, Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        this.config = new Config(event.getSuggestedConfigurationFile());
        this.config.addConfigKey(ModWECUI.colorCuboidPoint1);
        this.config.addConfigKey(ModWECUI.colorCuboidPoint2);
        this.config.addConfigKey(ModWECUI.colorCuboidOutline);
        this.config.addConfigKey(ModWECUI.colorCuboidGrid);
        this.config.addConfigKey(ModWECUI.colorPolygonPoint);
        this.config.addConfigKey(ModWECUI.colorPolygonOutline);
        this.config.addConfigKey(ModWECUI.colorPolygonGrid);
        this.config.addConfigKey(ModWECUI.colorEllipsoidCenter);
        this.config.addConfigKey(ModWECUI.colorEllipsoidGrid);
        this.config.addConfigKey(ModWECUI.colorCylinderCenter);
        this.config.addConfigKey(ModWECUI.colorCylinderOutline);
        this.config.addConfigKey(ModWECUI.colorCylinderGrid);

        super.preInit(event);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public ModInfo getModInfo() {
        return Objects.MOD_INFO;
    }

    @Override
    public SpACoreProxy getProxy() {
        return ModWECUI.proxy;
    }

}
