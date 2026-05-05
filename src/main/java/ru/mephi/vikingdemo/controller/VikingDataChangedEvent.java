/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author svetl
 */
public class VikingDataChangedEvent extends ApplicationEvent{
    public VikingDataChangedEvent(Object source) {
        super(source);
    }
}
