package com.CodingGrid.CounterCompact.check.movement;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import com.CodingGrid.CounterCompact.Core;
import com.CodingGrid.CounterCompact.check.HackData;
import com.CodingGrid.CounterCompact.util.StaffUtil;

public class JesusACheck {
	
	public static boolean check(Player p, HackData data, PlayerMoveEvent e){
		if(p.hasPermission("countercompact.bypass")){
			return false;
		}
		  if (p.getAllowFlight()) {
		      return false;
		    }
		    if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WATER_LILY) {
		      return false;
		    }
		    Block b = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
		    if ((b.getType() != Material.WATER) || 
		      (b.getRelative(BlockFace.NORTH).getType() != Material.WATER) || 
		      (b.getRelative(BlockFace.EAST).getType() != Material.WATER) || 
		      (b.getRelative(BlockFace.SOUTH).getType() != Material.WATER) || 
		      (b.getRelative(BlockFace.WEST).getType() != Material.WATER) || (b.getType() != Material.STATIONARY_WATER) || 
		      (b.getRelative(BlockFace.NORTH).getType() != Material.STATIONARY_WATER) || 
		      (b.getRelative(BlockFace.EAST).getType() != Material.STATIONARY_WATER) || 
		      (b.getRelative(BlockFace.SOUTH).getType() != Material.STATIONARY_WATER) || 
		      (b.getRelative(BlockFace.WEST).getType() != Material.STATIONARY_WATER)) {
		      return false;
		    }
		    if (!isWater(p)) {
		      return false;
		    }
		    data.jesus_violations = (data.jesus_violations + 1);
		    if(data.jesus_violations > 4){
		    	String kick = Core.getInstance().getConfig().getString("jesus-kick").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "A").replaceAll("<diff>", b.toString()).replaceAll("<allowed>", "null");
				StaffUtil.sendStaffMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + kick);
				String kickmsg = Core.getInstance().getConfig().getString("kick-message").replaceAll("&", "§").replaceAll("<check>", "Jesus").replaceAll("<type>", "A");
				p.kickPlayer(kickmsg);
				 e.setCancelled(true);
		    	return true;
		    }
		    String not = Core.getInstance().getConfig().getString("jesus-notify").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "A").replaceAll("<diff>", b.toString()).replaceAll("<allowed>", "null");
		    StaffUtil.sendStaffMessage(not);
		    e.setCancelled(true);
		return true;
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
