package com.CodingGrid.CounterCompact.check.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.CodingGrid.CounterCompact.Core;
import com.CodingGrid.CounterCompact.util.StaffUtil;

public class ReachACheck {
	
	public static boolean check(Player p, EntityDamageByEntityEvent e){
		if(e.getDamager().equals(p)){
			if(p.hasPermission("countercompact.bypass")){
				return false;
			}
			double diff = p.getLocation().distance(e.getEntity().getLocation());
			if((diff > 4.7) || (diff < -4.7)){
				String kick = Core.getInstance().getConfig().getString("reach-kick").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "A").replaceAll("<diff>", "" + diff).replaceAll("<allowed>", "<3.5");
				StaffUtil.sendStaffMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + kick);
				String kickmsg = Core.getInstance().getConfig().getString("kick-message").replaceAll("&", "§").replaceAll("<check>", "Reach").replaceAll("<type>", "A");
				p.kickPlayer(kickmsg);
				 e.setCancelled(true);
				return true;
			}
			return false;
		}
		return false;
	}

}
