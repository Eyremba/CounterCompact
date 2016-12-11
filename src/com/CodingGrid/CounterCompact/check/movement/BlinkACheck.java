package com.CodingGrid.CounterCompact.check.movement;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class BlinkACheck {
	
	public static boolean check(Player p, PlayerTeleportEvent e){
		if(p.hasPermission("countercompact.bypass")){
			return false;
		}
		if(e.getCause() != TeleportCause.UNKNOWN){
			return false;
		}
		double newx = e.getFrom().getX() - e.getTo().getX();
		if((newx > 5.0) || (newx < -5.0)){
			e.setCancelled(true);
			return true;
		}
		return false;
	}

}
