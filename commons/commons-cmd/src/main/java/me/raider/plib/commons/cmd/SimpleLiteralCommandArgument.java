package me.raider.plib.commons.cmd;

public class SimpleLiteralCommandArgument extends SimpleCommandArgument<String> implements LiteralCommandArgument {

    private final String requiredLiteral;

    public SimpleLiteralCommandArgument(ArgumentSupplier<String> supplier, String requiredLiteral) {
        super(String.class, supplier);
        this.requiredLiteral = requiredLiteral;
    }

    @Override
    public String getRequiredLiteral() {
        return requiredLiteral;
    }
}
