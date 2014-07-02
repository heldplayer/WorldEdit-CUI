
package me.heldplayer.mods.wecui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

import me.heldplayer.mods.wecui.client.ClientProxy;
import me.heldplayer.mods.wecui.client.region.CuboidRegion;
import me.heldplayer.mods.wecui.client.region.CylinderRegion;
import me.heldplayer.mods.wecui.client.region.EllipsoidRegion;
import me.heldplayer.mods.wecui.client.region.NullRegion;
import me.heldplayer.mods.wecui.client.region.PolygonRegion;
import net.specialattack.forge.core.Objects;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class PacketHandler {

    private final FMLEventChannel channel;
    private final String channelName;

    public PacketHandler(String channelName) {
        this.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(channelName);
        this.channelName = channelName;
        this.channel.register(this);
    }

    @SubscribeEvent
    public void onClientCustomPacket(ClientCustomPacketEvent event) {
        ByteBuf buffer = event.packet.payload();
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        String data = new String(bytes);
        Objects.log.log(Level.INFO, data);
        String[] args = data.split("\\|");

        if (args[0].equals("s")) {
            if (args[1].equals("cuboid")) {
                ClientProxy.selection = new CuboidRegion();
            }
            else if (args[1].equals("polygon2d")) {
                ClientProxy.selection = new PolygonRegion();
            }
            else if (args[1].equals("ellipsoid")) {
                ClientProxy.selection = new EllipsoidRegion();
            }
            else if (args[1].equals("cylinder")) {
                ClientProxy.selection = new CylinderRegion();
            }
            else {
                ClientProxy.selection = new NullRegion();
            }
        }
        else if (args[0].equals("p")) {
            int id = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);
            int z = Integer.parseInt(args[4]);
            ClientProxy.selection.setPoint(id, x, y, z);
        }
        else if (args[0].equals("p2")) {
            int id = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            ClientProxy.selection.setPoint(id, x, 0, z);
        }
        else if (args[0].equals("e")) {
            int id = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);
            int z = Integer.parseInt(args[4]);
            ClientProxy.selection.setPoint(id, x, y, z);
        }
        else if (args[0].equals("mm")) {
            int min = Integer.parseInt(args[1]);
            int max = Integer.parseInt(args[2]);
            ClientProxy.selection.setMinMax(min, max);
        }
        else if (args[0].equals("cyl")) {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            double radiusX = Double.parseDouble(args[4]);
            double radiusZ = Double.parseDouble(args[5]);
            ClientProxy.selection.setPoint(0, x, y, z);
            ClientProxy.selection.setRadius(radiusX, 0, radiusZ);
        }
    }

    public void sendData(String data) {
        ByteBuf buffer = Unpooled.buffer();
        final Charset UTF_8_CHARSET = Charset.forName("UTF-8");
        byte[] bytes = data.getBytes(UTF_8_CHARSET);
        buffer.capacity(bytes.length);
        buffer.writeBytes(bytes);
        FMLProxyPacket packet = new FMLProxyPacket(buffer, this.channelName);
        this.channel.sendToServer(packet);
    }

}
