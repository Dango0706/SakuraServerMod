package me.tuanzi.sakura.random_events;

public enum EliteSkills {

    levitation("升空", 0),
    potion("中毒", 0),
    hunger("饥饿", 0),
    BLINDNESS("失明", 0),
    WEAKNESS("虚弱", 0),
    disarm("缴械", 0);
    private final String name;
    private final int level;

    EliteSkills(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
