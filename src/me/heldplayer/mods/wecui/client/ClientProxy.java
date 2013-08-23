
package me.heldplayer.mods.wecui.client;

import me.heldplayer.mods.wecui.CommonProxy;
import me.heldplayer.mods.wecui.client.region.NullRegion;
import me.heldplayer.mods.wecui.client.region.Region;
import me.heldplayer.util.HeldCore.client.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy implements IConnectionHandler {

    public static Region selection;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        NetworkRegistry.instance().registerConnectionHandler(this);
    }

    @ForgeSubscribe
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().mcProfiler.startSection("WECUI");

        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        double offsetX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.partialTicks;
        double offsetY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.partialTicks;
        double offsetZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.partialTicks;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPolygonOffset(-3.0F, -3.0F);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_FOG);

        if (selection != null) {
            GL11.glLineWidth(3.0F);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            selection.render(0.2F, offsetX, offsetY, offsetZ);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            selection.render(0.8F, offsetX, offsetY, offsetZ);
        }

        GL11.glDisable(GL11.GL_FOG);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPolygonOffset(0.0F, 0.0F);
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();

        Minecraft.getMinecraft().mcProfiler.endSection();
    }

    @ForgeSubscribe
    public void onWorldLoad(final WorldEvent.Load event) {
        selection = new NullRegion();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (event.world.isRemote) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (NetworkRegistry.instance().isChannelActive("WECUI", (Player) MC.getPlayer())) {
                        Packet250CustomPayload packet = new Packet250CustomPayload("WECUI", "v|3".getBytes());
                        FMLClientHandler.instance().sendPacket(packet);

                        MC.getPlayer().sendChatMessage("/we cui");
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {}

    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return null;
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {

        //selection = new CuboidRegion();

    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {}

    @Override
    public void connectionClosed(INetworkManager manager) {}

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {}

}
