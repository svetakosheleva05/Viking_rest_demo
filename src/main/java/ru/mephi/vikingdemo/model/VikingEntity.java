/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.model;

import java.util.List;

/**
 *
 * @author svetl
 */
public record VikingEntity (
    Integer id,
    String name,
    int age,
    int heightCm,
    HairColor hairColor,
    BeardStyle beardStyle,
    List<EquipmentItem> equipment
) {
}
