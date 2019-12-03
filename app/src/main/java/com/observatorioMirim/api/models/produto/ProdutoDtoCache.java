package com.observatorioMirim.api.models.produto;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;
import com.observatorioMirim.views.saida.list.SaidaList;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProdutoDtoCache {

    private static ArrayList<ProdutoDto> cache = new ArrayList<>();

    public static ArrayList<ProdutoDto> getCache() {
        return cache;
    }

    public static void setCache(ArrayList<ProdutoDto> cache) {
        ProdutoDtoCache.cache = cache;
    }

    public static void mergeDbApi(ArrayList<ProdutoDto> produtosDb, MainActivity mainActivity) {

        SweetUtils.loaderNativo(mainActivity, "Aguarde", "Sincronizando os produtos.");

        Map<Integer, ProdutoDto> dtoPorId = new HashMap<>();
        produtosDb.forEach( p -> {
            dtoPorId.put(p.getIdProduto(), p);
        });

        API.getSaidasHoje(new Callback<List<Saida>>() {
            @Override
            public void onResponse(Call<List<Saida>> call, Response<List<Saida>> response) {

                if(response == null || response.body() == null || response.body().isEmpty()){
                    //A entrada salva não está mais presente no dia atual
                    ProdutoDtoDB.deleteAll(mainActivity);

                    SweetUtils.cancelarLoaderNativo();

                    SaidaList.open(mainActivity);
                }else{
                    SweetUtils.cancelarLoaderNativo();

                    SweetUtils.confirmDialog(mainActivity, "Continuar Entrada", "Existe uma entrada que não foi terminada, você quer continuar de onde parou?", "Continuar", "Descartar",
                            (SweetAlertDialog sDialog) -> {

                                SweetUtils.loaderNativo(mainActivity, "Aguarde", "Sincronizando os produtos.");

                                sDialog.dismissWithAnimation();

                                response.body().forEach( s -> {
                                    if(s.getId().intValue() == produtosDb.get(0).getIdEntrada()){
                                        s.getSaidaItemList().forEach(i -> {
                                            ProdutoDto dto = dtoPorId.get(i.getIdProduto());

                                            if(dto == null){
                                                dto = new ProdutoDto(i);
                                            }

                                            cache.add(dto);
                                        });

                                        EntradaProdutoList.open(mainActivity);
                                    }
                                });

                                SweetUtils.cancelarLoaderNativo();

                            }, (SweetAlertDialog sDialog) -> {

                                ProdutoDtoDB.deleteAll(mainActivity);
                                SaidaList.open(mainActivity);

                                sDialog.dismissWithAnimation();

                            });

                }
            }

            @Override
            public void onFailure(Call<List<Saida>> call, Throwable t) {
                SweetUtils.cancelarLoaderNativo();
            }
        });
    }
}
