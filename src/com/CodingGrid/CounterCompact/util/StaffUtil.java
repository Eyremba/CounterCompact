package com.CodingGrid.CounterCompact.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StaffUtil {
	
	public static void sendStaffMessage(String content){
		for(Player staff : Bukkit.getOnlinePlayers()){
			if(staff.hasPermission("countercompact.notification")){
				staff.sendMessage(content);
			}
		}
	}
	
	public static void sendAnnouncement(String content){
		for(Player online : Bukkit.getOnlinePlayers()){
			online.sendMessage(content);
		}
	}

}
