package me.raider.plib.commons.cmd.tree.traverse;

public class DefaultTraverseResult implements TraverseResult<CommandTreeTraverseResponse> {

    private final CommandTreeTraverseResponse traversionResponse;

    public DefaultTraverseResult(CommandTreeTraverseResponse traversionResponse) {
        this.traversionResponse = traversionResponse;
    }

    @Override
    public CommandTreeTraverseResponse getTraverseResult() {
        return traversionResponse;
    }
}
