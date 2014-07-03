
package me.heldplayer.mods.wecui;

import me.heldplayer.mods.wecui.client.Color;
import net.minecraftforge.common.config.Configuration;
import net.specialattack.forge.core.ModInfo;
import net.specialattack.forge.core.SpACoreMod;
import net.specialattack.forge.core.SpACoreProxy;
import net.specialattack.forge.core.config.Config;
import net.specialattack.forge.core.config.ConfigCategory;
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

    @SuppressWarnings("rawtypes")
    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Objects.log = event.getModLog();

        ModWECUI.packetHandler = new PacketHandler("WECUI");

        // Config
        ConfigCategory<?> category = new ConfigCategory(Configuration.CATEGORY_GENERAL, "config.worldedit-cui.category.general", null, "General mod settings");
        ModWECUI.colorCuboidPoint1 = new ConfigValue<Color>("colorCuboidPoint1", "config.worldedit-cui.key.colorCuboidPoint1", Side.CLIENT, new Color(0.2F, 0.8F, 0.2F), "");
        ModWECUI.colorCuboidPoint2 = new ConfigValue<Color>("colorCuboidPoint2", "config.worldedit-cui.key.colorCuboidPoint2", Side.CLIENT, new Color(0.2F, 0.2F, 0.8F), "");
        ModWECUI.colorCuboidOutline = new ConfigValue<Color>("colorCuboidOutline", "config.worldedit-cui.key.colorCuboidOutline", Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCuboidGrid = new ConfigValue<Color>("colorCuboidGrid", "config.worldedit-cui.key.colorCuboidGrid", Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        ModWECUI.colorPolygonPoint = new ConfigValue<Color>("colorPolygonPoint", "config.worldedit-cui.key.colorPolygonPoint", Side.CLIENT, new Color(0.2F, 0.8F, 0.8F), "");
        ModWECUI.colorPolygonOutline = new ConfigValue<Color>("colorPolygonOutline", "config.worldedit-cui.key.colorPolygonOutline", Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorPolygonGrid = new ConfigValue<Color>("colorPolygonGrid", "config.worldedit-cui.key.colorPolygonGrid", Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        ModWECUI.colorEllipsoidCenter = new ConfigValue<Color>("colorEllipsoidCenter", "config.worldedit-cui.key.colorEllipsoidCenter", Side.CLIENT, new Color(0.8F, 0.8F, 0.2F), "");
        ModWECUI.colorEllipsoidGrid = new ConfigValue<Color>("colorEllipsoidGrid", "config.worldedit-cui.key.colorEllipsoidGrid", Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCylinderCenter = new ConfigValue<Color>("colorCylinderCenter", "config.worldedit-cui.key.colorCylinderCenter", Side.CLIENT, new Color(0.8F, 0.2F, 0.8F), "");
        ModWECUI.colorCylinderOutline = new ConfigValue<Color>("colorCylinderOutline", "config.worldedit-cui.key.colorCylinderOutline", Side.CLIENT, new Color(0.8F, 0.3F, 0.3F), "");
        ModWECUI.colorCylinderGrid = new ConfigValue<Color>("colorCylinderGrid", "config.worldedit-cui.key.colorCylinderGrid", Side.CLIENT, new Color(0.8F, 0.2F, 0.2F), "");
        this.config = new Config(event.getSuggestedConfigurationFile());
        this.config.addCategory(category);
        category.addValue(ModWECUI.colorCuboidPoint1);
        category.addValue(ModWECUI.colorCuboidPoint2);
        category.addValue(ModWECUI.colorCuboidOutline);
        category.addValue(ModWECUI.colorCuboidGrid);
        category.addValue(ModWECUI.colorPolygonPoint);
        category.addValue(ModWECUI.colorPolygonOutline);
        category.addValue(ModWECUI.colorPolygonGrid);
        category.addValue(ModWECUI.colorEllipsoidCenter);
        category.addValue(ModWECUI.colorEllipsoidGrid);
        category.addValue(ModWECUI.colorCylinderCenter);
        category.addValue(ModWECUI.colorCylinderOutline);
        category.addValue(ModWECUI.colorCylinderGrid);

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
