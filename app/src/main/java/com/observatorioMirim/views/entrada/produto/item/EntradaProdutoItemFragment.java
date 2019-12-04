package com.observatorioMirim.views.entrada.produto.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.produto.Produto;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.api.models.produto.ProdutoDtoCache;
import com.observatorioMirim.api.models.produto.ProdutoDtoDB;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.utils.UnidadeProduto;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoListFragment;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaProdutoItemFragment extends Fragment {

    private TextView textViewNome;
    private TextInputEditText textInputMarca;
    private TextInputEditText textInputDiaValidade;
    private TextInputEditText textInputMesValidade;
    private TextInputEditText textInputAnoValidade;
    private TextInputEditText textInputQuantidade;
    private TextInputEditText textInputUnidade;
    private TextInputEditText textInputObservacao;
    private Button buttonDarEntrada;
    private Button buttonCancelarEntrada;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        ProdutoDto produto = (ProdutoDto) getArguments().getSerializable("produto");

        View view = inflater.inflate(R.layout.fragment_item_produto, container, false);

        textInputMarca = view.findViewById(R.id.entrada_produto_item_marca);
        textInputDiaValidade = view.findViewById(R.id.entrada_produto_item_validade_dia);
        textInputMesValidade = view.findViewById(R.id.entrada_produto_item_validade_mes);
        textInputAnoValidade = view.findViewById(R.id.entrada_produto_item_validade_ano);
        textInputQuantidade = view.findViewById(R.id.entrada_produto_item_quantidade);
        textInputUnidade = view.findViewById(R.id.entrada_produto_item_unidade);
        textInputObservacao = view.findViewById(R.id.entrada_produto_item_observacao);

        Spinner spinnerUnidade = view.findViewById(R.id.spinner1);
        UnidadeProduto[] items = UnidadeProduto.values();
        ArrayAdapter<UnidadeProduto> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidade.setAdapter(adapter);
        Spinner spinnerNome = view.findViewById(R.id.spinner_entrada);

        if(produto != null){
            spinnerNome.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(produto)));
        } else {
            API.getProdutos(getContext(), new Callback<List<Produto>>() {
                @Override
                public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                    spinnerNome.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, response.body()));
                }

                @Override
                public void onFailure(Call<List<Produto>> call, Throwable t) {

                }
            });
        }

        buttonDarEntrada = view.findViewById(R.id.entrada_produto_item_dar_entrada);

        buttonDarEntrada.setOnClickListener(o -> {

            try {

                String marca = textInputMarca.getText().toString();
                if(marca.isEmpty()){
                    throw new Exception("Informe a marca.");
                }

                String diaValidadeStr = textInputDiaValidade.getText().toString();
                if(diaValidadeStr.isEmpty()){
                    throw new Exception("Informe o dia de validade.");
                }

                int diaValidade = Integer.parseInt(diaValidadeStr);
                if(diaValidade < 1 || diaValidade > 31){
                    throw new Exception("O dia de validade é incorreto.");
                }

                String mesValidadeStr = textInputMesValidade.getText().toString();
                if(mesValidadeStr.isEmpty()){
                    throw new Exception("Informe o mês de validade.");
                }

                int mesValidade = Integer.parseInt(mesValidadeStr);
                if(mesValidade < 1 || mesValidade > 12){
                    throw new Exception("O mês de validade é incorreto.");
                }

                String anoValidadeStr = textInputAnoValidade.getText().toString();
                if(anoValidadeStr.isEmpty()){
                    throw new Exception("Informe o ano de validade.");
                }

                int anoValidade = Integer.parseInt(anoValidadeStr);
                if(anoValidade < 2015){
                    throw new Exception("O ano de validade é incorreto.");
                }

                LocalDate dataValidade;
                try {
                    dataValidade = LocalDate.of(anoValidade, mesValidade, diaValidade);
                }catch (Exception e){
                    throw new Exception("Não foi informado uma data de validade correta.");
                }

                String quantidadeStr = textInputQuantidade.getText().toString();
                if(quantidadeStr.isEmpty()){
                    throw new Exception("Informe a quantidade.");
                }

                BigDecimal quantidade = new BigDecimal(quantidadeStr);

                String unidade = spinnerUnidade.getSelectedItem().toString();
                if(unidade.isEmpty()){
                    throw new Exception("Informe a unidade.");
                }

                String observacao = textInputObservacao.getText().toString();

                produto.setMarca(marca);
                produto.setDataValidade(dataValidade);
                produto.setQuantidade(quantidade);
                produto.setUnidade(unidade);
                produto.setObservacao(observacao);
                produto.setEntrada(true);

                MainActivity main = (MainActivity) getActivity();

                ProdutoDtoDB.save(main, produto);
                EntradaProdutoList.open(main);

                Toast.makeText(main, "Produto adicionado com sucesso!", Toast.LENGTH_LONG).show();

            }catch (Exception e){
                SweetUtils.message(getActivity(), "Erro:", e.getMessage(), SweetAlertDialog.ERROR_TYPE);
            }

        });
        buttonCancelarEntrada = view.findViewById(R.id.cancelar_entrada);
        buttonCancelarEntrada.setOnClickListener(o -> getActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Produtos");
    }

    public static EntradaProdutoItemFragment newInstance(final ProdutoDto produto){

        EntradaProdutoItemFragment entradaProdutoListFragment = new EntradaProdutoItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("produto", produto);
        entradaProdutoListFragment.setArguments(args);

        return entradaProdutoListFragment;
    }

}
