package me.raider.poto.provider;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import me.raider.poto.Provider;

import java.util.concurrent.Executors;

public class ListeningExecutorServiceProvider implements Provider<ListeningExecutorService> {

    private static final ListeningExecutorService PROVIDER = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    @Override
    public ListeningExecutorService get() {
        return PROVIDER;
    }
}
