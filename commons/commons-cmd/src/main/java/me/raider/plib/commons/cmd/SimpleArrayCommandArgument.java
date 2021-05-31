package me.raider.plib.commons.cmd;

public class SimpleArrayCommandArgument extends SimpleCommandArgument<String> implements ArrayCommandArgument {

    public SimpleArrayCommandArgument(ArgumentSupplier<String> supplier) {
        super(String.class, supplier);
    }
}
