package me.raider.poto.commons.cmd;

public class LiteralCommandArgument extends SimpleCommandArgument<String> {

    private final String requiredLiteral;

    public LiteralCommandArgument(CommandArgumentManager manager, String requiredLiteral) {
        super(String.class, manager);
        this.requiredLiteral = requiredLiteral;
    }

    public String getRequiredLiteral() {
        return requiredLiteral;
    }
}
