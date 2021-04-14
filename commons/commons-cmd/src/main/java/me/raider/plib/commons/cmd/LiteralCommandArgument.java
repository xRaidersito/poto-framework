package me.raider.plib.commons.cmd;

public class LiteralCommandArgument extends SimpleCommandArgument<String> {

    public LiteralCommandArgument(ArgumentSupplier<String> supplier, String requiredLiteral) {
        super(String.class, supplier, true, requiredLiteral);
    }

}
