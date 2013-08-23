
package me.heldplayer.mods.wecui;

import java.util.logging.Level;

import me.heldplayer.mods.wecui.client.ClientProxy;
import me.heldplayer.mods.wecui.client.region.CuboidRegion;
import me.heldplayer.mods.wecui.client.region.CylinderRegion;
import me.heldplayer.mods.wecui.client.region.EllipsoidRegion;
import me.heldplayer.mods.wecui.client.region.NullRegion;
import me.heldplayer.mods.wecui.client.region.PolygonRegion;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        String data = new String(packet.data);
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

}
