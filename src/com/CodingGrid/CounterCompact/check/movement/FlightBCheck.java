package com.CodingGrid.CounterCompact.check.movement;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.CodingGrid.CounterCompact.Core;
import com.CodingGrid.CounterCompact.check.HackData;
import com.CodingGrid.CounterCompact.util.StaffUtil;

public class FlightBCheck {

	public static boolean check(Player p, HackData data, PlayerTeleportEvent e){
		if(p.hasPermission("countercompact.bypass")){
			return false;
		}
		if(e.getCause() != TeleportCause.UNKNOWN){
			return false;
		}
		if (p.getAllowFlight()) {
		      return false;
		    }
		    if (e.getFrom().toVector().equals(e.getTo().toVector())) {
		      return false;
		    }
		    if (e.getFrom().getY() > e.getTo().getY()) {
		      return false;
		    }
		    Block b = p.getLocation().getBlock();
		    if ((b.getRelative(BlockFace.NORTH).getType() != Material.AIR) || 
		      (b.getRelative(BlockFace.EAST).getType() != Material.AIR) || (b.getRelative(BlockFace.SOUTH).getType() != Material.AIR) || 
		      (b.getRelative(BlockFace.WEST).getType() != Material.AIR) || (b.getRelative(BlockFace.UP).getType() != Material.AIR) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
		      return false;
		    }
		    if(isWater(p)){
		    	return false;
		    }
		    if ((b.getType().equals(Material.WATER)) || (b.getType().equals(Material.STATIONARY_WATER))) {
		      return false;
		    }
		
		    double off = e.getTo().getY() - e.getFrom().getY();
		    if(off > 0.43){
		    data.flight_violations = (data.flight_violations + 1);
		    if(data.flight_violations > 5){
		    	String kick = Core.getInstance().getConfig().getString("flight-kick").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "B").replaceAll("<diff>", "" + off).replaceAll("<allowed>", "0.41");
				StaffUtil.sendStaffMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + kick);
				String kickmsg = Core.getInstance().getConfig().getString("kick-message").replaceAll("&", "§").replaceAll("<check>", "Flight").replaceAll("<type>", "B");
				p.kickPlayer(kickmsg);
			e.setCancelled(true);
		    	return true;
		    }
		    String not = Core.getInstance().getConfig().getString("flight-notify").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "B").replaceAll("<diff>", "" + off).replaceAll("<allowed>", "0.41");
		    StaffUtil.sendStaffMessage(not);
			e.setCancelled(true);
		    return true;
		    }
		return false;
	}
	
	private static boolean isWater(Player p)
	  {
	    Material m = p.getLocation().getBlock().getType();
	    if ((m == Material.STATIONARY_WATER) || (m == Material.WATER)) {
	      return true;
	    }
	    return false;
	  }

}

