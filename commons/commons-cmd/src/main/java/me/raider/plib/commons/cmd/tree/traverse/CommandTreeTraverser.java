package me.raider.plib.commons.cmd.tree.traverse;

import me.raider.plib.commons.cmd.CommandResult;
import me.raider.plib.commons.cmd.WrappedCommandResult;
import me.raider.plib.commons.cmd.tree.CommandNode;

import java.util.*;

@SuppressWarnings("unchecked")
public class CommandTreeTraverser implements TreeTraverser<WrappedCommandResult> {

    private final Map<String, Traverser<?>> traversers = new LinkedHashMap<>();
    private final CommandNode root;

    public CommandTreeTraverser(CommandNode root) {
        this.root = root;
    }

    @Override
    public Map<String, Traverser<?>> getTraverser() {
        return traversers;
    }

    @Override
    public void registerTraverser(String key, Traverser<?> traverser) {
        this.traversers.put(key, traverser);
    }

    @Override
    public CommandNode getRootNode() {
        return root;
    }

    @Override
    public Optional<WrappedCommandResult> traverseAll(TraversionObjectsProvider objectsProvider) {
        WrappedCommandResult commandResult = new WrappedCommandResult(new ArrayList<>());
        List<Object> objects = (List<Object>) objectsProvider.getProvider("arguments");
        CommandNode actual = root;
        int index = 0;
        for (String key : traversers.keySet()) {
            Traverser<List<Object>> traverser = (Traverser<List<Object>>) traversers.get(key);
            if (index>0) {
                objects.subList(0, index).clear();
            }
            CommandTreeTraverseResponse response = traverser
                    .traverse(actual, objects)
                    .getTraverseResult();

            actual = response.getNode();
            index = response.getIndex();

            WrappedCommandResult result = response.getCommandResult();

            if (result.getResult() != CommandResult.INVALID)
                commandResult.getResolvedArguments().addAll(result.getResolvedArguments());

            if (response.getResponseType()==TraverseResponseType.FINISH) {
                commandResult.setCommand(result.getCommand());
                commandResult.setResult(result.getResult());
                break;
            }
        }
        return Optional.of(commandResult);
    }
}
