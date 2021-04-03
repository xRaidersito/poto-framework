package me.raider.plib.commons.cmd;

import java.util.ArrayList;
import java.util.List;

public class LiteralArgumentProcessor implements ArgumentProcessor<LiteralCommandArgument> {

    private final CommandSupplierManager argumentManager;

    public LiteralArgumentProcessor(CommandSupplierManager argumentManager) {
        this.argumentManager = argumentManager;
    }

    @Override
    public List<LiteralCommandArgument> toArguments(String[] args) {

        List<LiteralCommandArgument> literals = new ArrayList<>();

        for (String arg : args) {
            literals.add(new LiteralCommandArgument(argumentManager.getSupplier(String.class), arg));
        }

        return literals;
    }
}
