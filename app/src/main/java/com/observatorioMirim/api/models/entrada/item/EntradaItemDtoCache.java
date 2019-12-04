package com.observatorioMirim.api.models.entrada.item;

import java.util.ArrayList;

public final class EntradaItemDtoCache {

    private static ArrayList<EntradaItemDto> cache = new ArrayList<>();

    public static ArrayList<EntradaItemDto> getCache() {
        return cache;
    }

    public static void setCache(ArrayList<EntradaItemDto> cache) {
        EntradaItemDtoCache.cache = cache;
    }
}
