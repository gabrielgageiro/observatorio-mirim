package com.observatorioMirim.views.saida.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDao;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDto;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDtoDao;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.utils.Contexto;
import com.observatorioMirim.utils.SweetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaidaList {

    private static final String TITULO = "Entradas";

    public static void open(@NonNull final AppCompatActivity activity){

        SweetUtils.loaderNativo(activity, "Aguarde", "Carregando as entradas do dia.");

        Contexto.setIdEntradaAtual(activity, -1);

        Integer idEntradaDia = EntradaDtoDao.exitsEntradaDoDia(activity);
        if(idEntradaDia != null){
            Contexto.setIdEntradaAtual(activity, idEntradaDia);
            AbstractFragment.openFragmentFromActivity(activity, SaidaListFragment.newInstance(), TITULO, "SaidaListFragment");
        }else{
            API.getSaidasHoje(new Callback<List<Saida>>() {
                @Override
                public void onResponse(Call<List<Saida>> call, Response<List<Saida>> response) {

                    if(response != null && response.body() != null && !response.body().isEmpty()) {

                        Saida saida = response.body().get(0); //Só tem uma saída por dia

                        EntradaDto entradaDto = new EntradaDto(saida);
                        EntradaDtoDao.save(activity, entradaDto);
                        int entradaId = entradaDto.getId();

                        Contexto.setIdEntradaAtual(activity, entradaId);

                        saida.getSaidaItemList().forEach(s -> {
                            EntradaItemDto dto = new EntradaItemDto(s);
                            dto.setIdEntrada(entradaId);
                            EntradaItemDtoDao.save(activity, dto);
                        });
                    }

                    AbstractFragment.openFragmentFromActivity(activity, SaidaListFragment.newInstance(), TITULO, "SaidaListFragment");
                }

                @Override
                public void onFailure(Call<List<Saida>> call, Throwable t) {
                    SweetUtils.cancelarLoaderNativo();
                }
            });
        }

    }
}
