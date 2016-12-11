package com.CodingGrid.CounterCompact;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.CodingGrid.CounterCompact.check.HackData;
import com.CodingGrid.CounterCompact.check.combat.ReachACheck;
import com.CodingGrid.CounterCompact.check.movement.BlinkACheck;
import com.CodingGrid.CounterCompact.check.movement.FlightACheck;
import com.CodingGrid.CounterCompact.check.movement.FlightBCheck;
import com.CodingGrid.CounterCompact.check.movement.JesusACheck;
import com.CodingGrid.CounterCompact.check.other.FastbreakACheck;

public class Checks implements Listener{
	
	private static HashMap<String, HackData> playerData = new HashMap();
	  public static HackData getDataFor(Player player)
	  {
	    String name = player.getName();
	    HackData cache = (HackData)playerData.get(name);
	    if (cache == null)
	    {
	      cache = new HackData();
	      playerData.put(name, cache);
	    }
	    
	    return cache;
	  }
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			ReachACheck.check(p, e);
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		HackData data = getDataFor(p);
		FlightACheck.check(p, data, e);
		JesusACheck.check(p, data, e);
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e){
		Player p = e.getPlayer();
		HackData data = getDataFor(p);
		FlightBCheck.check(p, data, e);
		BlinkACheck.check(p, e);
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e){
		Player p = e.getPlayer();
		HackData data = getDataFor(p);
		FastbreakACheck.check(p, data, e);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		 HackData cache = (HackData)playerData.get(e.getPlayer().getName());
		playerData.put(e.getPlayer().getName(), cache);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		playerData.remove(e.getPlayer().getName());
	}
}