package com.observatorioMirim.api.models.produto;

import java.util.ArrayList;

public final class ProdutoDtoCache {

    private static ArrayList<ProdutoDto> cache = new ArrayList<>();

    public static ArrayList<ProdutoDto> getCache() {
        return cache;
    }

    public static void setCache(ArrayList<ProdutoDto> cache) {
        ProdutoDtoCache.cache = cache;
    }
}
