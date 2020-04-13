package com.example.ev.SoKhop.Base;


import com.example.ev.SoKhop.Network.DataLoader;

public interface NetworkInterface {
	DataLoader getDataLoader(String id);
}
