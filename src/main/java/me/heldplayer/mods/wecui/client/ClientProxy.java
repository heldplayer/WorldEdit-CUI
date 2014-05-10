
package me.heldplayer.mods.wecui.client;

import me.heldplayer.mods.wecui.CommonProxy;
import me.heldplayer.mods.wecui.ModWECUI;
import me.heldplayer.mods.wecui.client.region.NullRegion;
import me.heldplayer.mods.wecui.client.region.Region;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.specialattack.forge.core.client.MC;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static Region selection;

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().mcProfiler.startSection("WECUI");

        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        double offsetX = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks;
        double offsetY = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks;
        double offsetZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks;

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

        if (ClientProxy.selection != null) {
            GL11.glLineWidth(3.0F);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            ClientProxy.selection.render(0.2F, offsetX, offsetY, offsetZ);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            ClientProxy.selection.render(0.8F, offsetX, offsetY, offsetZ);
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

    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        ClientProxy.selection = new NullRegion();

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

                    // if (NetworkRegistry.INSTANCE.isChannelActive("WECUI")) {
                    //Packet250CustomPayload packet = new Packet250CustomPayload("WECUI", "v|3".getBytes());
                    // FMLClientHandler.instance().sendPacket(packet);
                    ModWECUI.packetHandler.sendData("v|3");
                    MC.getPlayer().sendChatMessage("/we cui");
                    // }

                    PotionEffect effect = new PotionEffect(Potion.nightVision.id, 0, 0, false);
                    effect.setPotionDurationMax(true);
                    MC.getPlayer().addPotionEffect(effect);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
