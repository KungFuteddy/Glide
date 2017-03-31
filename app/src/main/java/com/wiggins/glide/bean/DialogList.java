package com.wiggins.glide.bean;

/**
 * @Description dialog数据集合
 * @Author 一花一世界
 */
public class DialogList {

    private String name;
    private String type;

    public DialogList(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DialogList{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
