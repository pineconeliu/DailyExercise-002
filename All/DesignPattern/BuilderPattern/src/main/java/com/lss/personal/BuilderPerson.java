package com.lss.personal;

import com.lss.self.inter.EquipmentBar;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true,fluent = true)
public class BuilderPerson {

    public String name;

    public  String faceShape;

    public   String body;

    public  String cloth;

    public BuilderPerson getFaceShape(EquipmentBar equipmentBar){
        faceShape = equipmentBar.toString();
        return this;
    }
    public BuilderPerson getBody(EquipmentBar equipmentBar){
        body = equipmentBar.toString();
        return this;
    }
    public BuilderPerson getCloth(EquipmentBar equipmentBar){
        cloth = equipmentBar.toString();
        return this;
    }

    public BuilderPerson(String name) {
        this.name = name;
    }

}
