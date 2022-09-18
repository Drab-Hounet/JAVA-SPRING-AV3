package com.rest.third.Model;

public class PersonnageDTO {
    private int id;
    private String name;
    private String type;
    private int life;

    /**
     * Construteur PersonnageDTO
     * @param id id du personnage
     * @param name le nom
     * @param type type de personnage
     * @param life niveau de sa vie
     */
    public PersonnageDTO(int id, String name, String type, int life) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
