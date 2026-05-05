/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import javax.swing.SwingUtilities;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.gui.VikingDesktopFrame;

/**
 *
 * @author test2023
 */
@Component
public class VikingListener {
    private VikingDesktopFrame gui;
 
    public void setGui(VikingDesktopFrame gui){
        this.gui = gui;
    }
    
    @EventListener
    public void onDataChanged(VikingDataChangedEvent event) {
        SwingUtilities.invokeLater(() -> {
            if (gui != null) {
                gui.refreshFromService();
            }
        });
    }  
}
