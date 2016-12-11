package com.CodingGrid.CounterCompact.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.CodingGrid.CounterCompact.Core;

public class CommandCountercompact implements CommandExecutor
{
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		  if(sender instanceof Player){
			  Player p = (Player) sender;
		  if(label.equalsIgnoreCase("countercompact")){
			  if(!p.isOp()){
				  p.sendMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + "§rCounterCompact BETA-1.0.0, by CodingGrid\n§8- §a§oHacking is like Mathematics, a waste of time §9~ LeadDev Speciality - Joost (CodingGrid)\n§8- §a§oDon't even try to cheat, you'll get beat! §9~ TeamLeader Speciality - Pyke (xMashh)§r");
				  return true;
			  }
			  p.sendMessage(Core.getInstance().getConfig().getString("plugin-prefix").replaceAll("&", "§") + "§rCounterCompact BETA-1.0.0, by CodingGrid\n§8- §a§oHacking is like Mathematics, a waste of time §9~ LeadDev Speciality - Joost (CodingGrid)\n§8- §a§oDon't even try to cheat, you'll get beat! §9~ TeamLeader Speciality - Pyke (xMashh)§r");
			  p.sendMessage("\n§rThank you for downloading CounterCompact, currently we support the following checks:\n- KillAura - Reach\n- Blink - Flight\n- Jesus - FastBreak");
			  return true;
		  }
		  }
		 return false; 
	  }

}
