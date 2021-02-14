package me.raider.poto.command.argument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleArgumentParser implements ArgumentParser {

    @Override
    public String[] parse(String[] args, int size) {

        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        for (int i = 0; i < size ; i++) {
            argsList.remove(0);
        }

        String[] newArgs = new String[args.length-size];

        for (int i = 0 ; i < newArgs.length ; i++) {
            newArgs[i]= argsList.get(i);
        }

        return newArgs;
    }



}
