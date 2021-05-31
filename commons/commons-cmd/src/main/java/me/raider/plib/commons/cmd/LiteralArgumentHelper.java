package me.raider.plib.commons.cmd;

import java.util.ArrayList;
import java.util.List;

public class LiteralArgumentHelper implements ArgumentHelper<LiteralCommandArgument> {

    private final CommandSupplierManager argumentManager;

    public LiteralArgumentHelper(CommandSupplierManager argumentManager) {
        this.argumentManager = argumentManager;
    }

    @Override
    public List<LiteralCommandArgument> toArguments(String[] args) {

        List<LiteralCommandArgument> literals = new ArrayList<>();

        for (String arg : args) {
            literals.add(new SimpleLiteralCommandArgument(argumentManager.getSupplier(String.class), arg));
        }

        return literals;
    }
}
