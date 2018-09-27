package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleGenerator {
    public static void main(String[] args) throws Exception{
        Schema schema=new Schema(1,"com.bobby");
        addNote(schema);
        new DaoGenerator().generateAll(schema,"E:\\AndroidStudioProjects\\MyDiary\\app\\src\\main\\src-gen");
    }
    private static void addNote(Schema schema){
        Entity note=schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("title").notNull();
        note.addStringProperty("body");
        note.addStringProperty("date");
    }
}
