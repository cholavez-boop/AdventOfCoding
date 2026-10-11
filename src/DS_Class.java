public class DS_Class {
    private String name;
    private String subclass;
    private String triggeredAbility;
    private String heroicAbility;
    private String signatureAbility;

    public DS_Class(String name, String subclass, String triggeredAbility, String heroicAbility, String signatureAbility) {
        this.name = name;
        this.subclass = subclass;
        this.triggeredAbility = triggeredAbility;
        this.heroicAbility = heroicAbility;
        this.signatureAbility = signatureAbility;
    }

    public String getName() {
        return name;
    }

    public String getSubclass() {
        return subclass;
    }

    public String getTriggeredAbility() {
        return triggeredAbility;
    }

    public String getHeroicAbility() {
        return heroicAbility;
    }

    public String getSignatureAbility() {
        return signatureAbility;
    }
}
