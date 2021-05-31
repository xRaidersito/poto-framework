package me.raider.plib.commons.cmd;

public class SimpleRangeCommandArgument<T extends Number> extends SimpleCommandArgument<T> implements RangeCommandArgument<T> {

    private final int minRange;
    private final int maxRange;

    public SimpleRangeCommandArgument(Class<T> clazz, ArgumentSupplier<T> supplier, int minRange, int maxRange) {
        super(clazz, supplier);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    @Override
    public boolean range(T number) {
        Number resolvedValue = resolveArgument(number);
        return resolvedValue.doubleValue() >= minRange && resolvedValue.doubleValue() <= maxRange;
    }
}
