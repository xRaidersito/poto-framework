package me.raider.plib.commons.cmd;

public class LiteralCommandArgument extends SimpleCommandArgument<String> {

    private final String requiredLiteral;

    public LiteralCommandArgument(ArgumentSupplier<String> supplier, String requiredLiteral) {
        super(String.class, supplier);
        this.requiredLiteral = requiredLiteral;
    }

    public String getRequiredLiteral() {
        return requiredLiteral;
    }
}
