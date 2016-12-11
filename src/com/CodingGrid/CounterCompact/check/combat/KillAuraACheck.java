package com.CodingGrid.CounterCompact.check.combat;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.CodingGrid.CounterCompact.Core;
import com.CodingGrid.CounterCompact.check.HackData;
import com.CodingGrid.CounterCompact.util.StaffUtil;

import coms.lenis0012.bukkit.npc.NPC;
import coms.lenis0012.bukkit.npc.NPCDamageEvent;
import coms.lenis0012.bukkit.npc.NPCFactory;
import coms.lenis0012.bukkit.npc.NPCProfile;

public class KillAuraACheck implements Listener{
	
	 private static HashMap<String, HackData> playerData = new HashMap();
	  
	  public static HackData getDataFor(OfflinePlayer player)
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
	
	 public NPC spawnNpc(final Player p, String name, String skin)
	  {
	    NPCFactory factory = new NPCFactory(Core.getInstance());
	    NPCProfile profile = new NPCProfile(name);
	    final NPC npc = factory.spawnHumanNPC(p.getLocation(), profile);
	    npc.setGravity(false);
	    npc.getBukkitEntity().setAllowFlight(true);
	    npc.getBukkitEntity().setFlying(true);
	    npc.getBukkitEntity().setSprinting(true);
	    new Thread()
	    {
	      public void run()
	      {
	        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable()
	        {
	          public void run()
	          {
	        	  if(!p.isOnline()){
	        		  return;
	        	  }
	            if ((npc.getBukkitEntity().getPassenger() != null) && (npc.getBukkitEntity().getPassenger() == p)) {
	              npc.setYaw(p.getLocation().getYaw());
	            } else {
	              npc.lookAt(p.getLocation());
	            }
	          }
	        }, 0L, 1L);
	        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable()
	        {
	          public void run()
	          {
	        	  if(!p.isOnline()){
	        		  return;
	        	  }
	        	  Location find = new Location(p.getWorld(), p.getLocation().getX() - 1.0D, p.getLocation().getY() + 2.0D, p.getLocation().getZ());
	            if (!npc.getBukkitEntity().getLocation().equals(find))
	            {
	              npc.pathfindTo(find, 0.2D, 50.0D);
	            }
	          }
	        }, 0L, 15L);
	      }
	    }.start();
	    return npc;
	  }
	 public enum alphabet{
		 a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
	 }
	 
	 @EventHandler
	 public void onJoin(PlayerJoinEvent e){
		 if(e.getPlayer().hasPermission("countercompact.bypass")){
			 return;
		 }
		 for(Player p : Bukkit.getOnlinePlayers()){
			 if(p.equals(e.getPlayer())){
			        String name = p.getName();
			        String skin = p.getName();
			        NPC npc = spawnNpc(p, name, skin);
				 Core.addNPC(p, npc);
			 }
		 }
	 }
	 
	 @EventHandler
	 public void onQuit(PlayerQuitEvent e){
		 if(Core.hasNPC(e.getPlayer())){
			 Core.removeNPC(e.getPlayer());
		 }
	 }
	 
	 @EventHandler
	 public void onHurtNPC(NPCDamageEvent e){
		 if(e.getDamager() instanceof Player){
		 Player p = (Player) e.getDamager();
		 if(p.hasPermission("countercompact.bypass")){
			 return;
		 }
		 HackData data = getDataFor(p);
		 data.killaura_hits = (data.killaura_hits + 1);
		 if(data.killaura_hits > 7){
			 if(data.killaura_hits > 11){
				 String kick = Core.getInstance().getConfig().getString("killaura-kick").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "A").replaceAll("<diff>", String.valueOf(data.killaura_hits)).replaceAll("<allowed>", "null");
					StaffUtil.sendStaffMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + kick);
					String kickmsg = Core.getInstance().getConfig().getString("kick-message").replaceAll("&", "§").replaceAll("<check>", "KillAura").replaceAll("<type>", "A");
					p.kickPlayer(kickmsg);
				 return;
			 }
			 String not = Core.getInstance().getConfig().getString("killaura-notify").replace("&", "§").replaceAll("<name>", p.getName()).replaceAll("<type>", "A").replaceAll("<diff>", String.valueOf(data.killaura_hits)).replaceAll("<allowed>", "null");
			    StaffUtil.sendStaffMessage(not);
			 return;
		 }
		 }
	 }

}
