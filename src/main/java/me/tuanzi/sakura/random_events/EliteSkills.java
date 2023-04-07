package me.tuanzi.sakura.random_events;

public enum EliteSkills {

    LEVITATION("升空", 0),
    POTION("中毒", 0),
    HUNGER("饥饿", 0),
    BLINDNESS("失明", 0),
    WEAKNESS("虚弱", 0),
    WITHER("凋零",0),
    RELIFE("重生",0),
    DISARM("缴械", 0),
    NINJA("忍者",0),
    DEFENSE("绝对防御",0),
    SCORCHING("灼热",0),
    PUMMEL("重击",0),
    KNOCKBACK("击退",0),
    SPEED("冲锋",0)

    ;
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
