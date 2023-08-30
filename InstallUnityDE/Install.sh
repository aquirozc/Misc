#!/bin/bash
su
curl https://unity.ruds.io/repo.key | sudo pacman-key --add -
sudo pacman-key --lsign-key 3FB6809130A5DB7F
echo "[arch-unity]" >> /etc/pacman.conf
echo "SigLevel = Required DatabaseOptional" >> /etc/pacman.conf
echo "Server = https://unity.ruds.io/arch-unity" >> /etc/pacman.conf
pacman -Syyu
pacman -S unity