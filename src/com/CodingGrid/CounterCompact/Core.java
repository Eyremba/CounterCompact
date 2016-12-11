package com.CodingGrid.CounterCompact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.CodingGrid.CounterCompact.check.combat.KillAuraACheck;
import com.CodingGrid.CounterCompact.command.CommandCountercompact;

import coms.lenis0012.bukkit.npc.NPC;
import coms.lenis0012.bukkit.npc.NPCFactory;

public class Core extends JavaPlugin{
	
	private static Core instance;
	  public static HashMap<UUID, NPC> NpcList = new HashMap();
	  public static List<String> TeamNpcNameList = new ArrayList();
	public void onEnable(){
		instance = this;
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new NPCFactory(this), this);
		Bukkit.getPluginManager().registerEvents(new KillAuraACheck(), this);
		Bukkit.getPluginManager().registerEvents(new Checks(), this);
		getCommand("countercompact").setExecutor(new CommandCountercompact());
	}
	
 public void onDisable(){
	 for (String s : TeamNpcNameList) {
	      if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s) != null)
	      {
	        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s).setPrefix("");
	        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s).setSuffix("");
	      }
	 }
 }
	
	public static Core getInstance(){
		return instance;
	}
	
	 public static boolean hasNPC(Player p)
	  {
	    return NpcList.containsKey(p.getUniqueId());
	  }
	  
	  public static void addNPC(Player p, NPC n)
	  {
	    if (NpcList.get(p.getUniqueId()) != null)
	    {
	      if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(getNPC(p).getBukkitEntity().getName()) != null)
	      {
	        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(getNPC(p).getBukkitEntity().getName()).setPrefix("");
	        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(getNPC(p).getBukkitEntity().getName()).setSuffix("");
	      }
	      ((NPC)NpcList.get(p.getUniqueId())).getBukkitEntity().remove();
	    }
	    NpcList.put(p.getUniqueId(), n);
	  }
	  
	  public static void removeNPC(Player p)
	  {
	    if (NpcList.get(p.getUniqueId()) != null) {
	      ((NPC)NpcList.get(p.getUniqueId())).getBukkitEntity().remove();
	    }
	    NpcList.put(p.getUniqueId(), null);
	  }
	  
	  public static NPC getNPC(Player p)
	  {
	    return (NPC)NpcList.get(p.getUniqueId());
	  }
	

}
