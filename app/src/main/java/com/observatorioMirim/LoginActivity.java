package com.observatorioMirim;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaAluno;
import com.observatorioMirim.api.models.entrada.EntradaItem;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.fornecedor.Fornecedor;
import com.observatorioMirim.api.models.produto.Produto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextInputLayout textUsuario;
    private TextInputLayout text_input;
    private TextInputEditText edit_text_usuario;
    private TextInputEditText edit_text_senha;
    private ImageView login_logo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        textUsuario = findViewById(R.id.password_text_input);
        text_input = findViewById(R.id.text_input);
        edit_text_usuario = findViewById(R.id.edit_text_usuario);
        edit_text_senha = findViewById(R.id.edit_text_senha);
        login_logo = findViewById(R.id.login_logo);


        Animation animation_esquerda = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
        Animation animation_esquerda_500Ms = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
        Animation animation_direita = AnimationUtils.loadAnimation(this, R.anim.move_para_direta);

        textUsuario.startAnimation(animation_esquerda);
        text_input.startAnimation(animation_esquerda);
        btnLogin.startAnimation(animation_esquerda_500Ms);
        login_logo.startAnimation(animation_direita);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.requireNonNull(edit_text_usuario.getText()).length() == 0 && Objects.requireNonNull(edit_text_senha.getText()).length() == 0){
                    System.out.println("Implementar Animação informando que o usuário e a senha devem ser preenchidos");
                    return;
                }

                final Escola escola = new Escola(edit_text_usuario.getText().toString(), edit_text_senha.getText().toString());

                API.getEscolaByCodigoSenha(escola.getUsuario_smart(), escola.getSenha_smart(), new Callback<Escola>() {
                    @Override
                    public void onResponse(Call<Escola> call, Response<Escola> response) {
                        if(response != null && response.body() != null){
                            if(response.body().getUsuario_smart().equals(escola.getUsuario_smart()) && response.body().getSenha_smart().equals(escola.getSenha_smart())){
                                //TODO: Criar classe observatorioUtils, salvar as chaves aqui, salvar o idConta e idEscola no share

                                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(it);
                                //TODO: Implementar uma mensagem informando que o login foi efetivado ou Bem Vindo!
                            }
                        } else {
                            System.out.println("Erro ao encontrar a escola");
                        }
                    }

                    @Override
                    public void onFailure(Call<Escola> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        //incluirProduto();
        //listarProdutos();
        //listarProdutoByNome();
        //listarProdutoById();
        //listarProdutoByCodigoBarras();

        //incluirFornecedor();
        //listarFornecedores();

        //incluirEntrada();
        //incluirAluno();
    }

    private void incluirItem() {
        final EntradaItem entradaItem = new EntradaItem();
        entradaItem.setIdConta(10);
        entradaItem.setIdEscola(9);
        entradaItem.setIdFornecedor(3);
        entradaItem.setIdProduto(3);
        entradaItem.setObservacao("Eaii tudo bom?");


        API.postEntradaItem(entradaItem, new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                System.out.println(response.body().getMensagem());
                System.out.println(response.body().getId());
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });
    }

    private void incluirEntrada() {
        final Entrada entradas = new Entrada();
        entradas.setIdConta(2);
        entradas.setIdEscola(1);
        entradas.setObservacao("Eaii tudo bom?");
        //entradas.setDataHora(LocalDateTime.now()); //É preenchido automaticamente
        entradas.setEntradaAlunos(Arrays.asList(new EntradaAluno(1, "Cássio", 2), new EntradaAluno(1, "Càssio", 2)));
        entradas.setEntradaItems(Collections.singletonList(new EntradaItem(1, 30, 18, "KG", new BigDecimal(1), "Item do Cássio", 2)));
        entradas.setIdSaida(1);
        entradas.setEntradaEntradas(new ArrayList<Entrada>());
        entradas.setEscola("Tento");//Setar pelo preferences?? não é obrigatório


        API.postEntrada(entradas, new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                System.out.println(response.body().getMensagem());
                System.out.println(response.body().getId());
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });
    }

    private void incluirAluno() {
        final EntradaAluno entradaAluno = new EntradaAluno();
        entradaAluno.setIdConta(10);
        entradaAluno.setIdEscola(9);
        entradaAluno.setNomeAluno("Cássio 3.0");

        API.postEntradaAluno(entradaAluno, new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                System.out.println(response.body().getMensagem());
                System.out.println(response.body().getId());
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });

    }


    private void listarProdutoByCodigoBarras() {
        API.getProdutoByCodigoBarras(10, 9, "7891149102488", new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                System.out.println(response.body().getNome());
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });
    }

    private void listarProdutoById() {
        API.getProdutoById(10, 9, 2, new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                System.out.println(response.body().getNome());
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });
    }

    private void listarProdutoByNome() {
        API.getProdutoByNome(10, 9, "Leite Milnutri", new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                System.out.println(response.body().getNome());
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                System.out.println("Deu erro");
            }
        });
    }


    private void listarFornecedores() {
        API.getFornecedorById(2, 1, 30, new Callback<Fornecedor>() {
            @Override
            public void onResponse(Call<Fornecedor> call, Response<Fornecedor> response) {
                System.out.println(response.body().getNome());
            }

            @Override
            public void onFailure(Call<Fornecedor> call, Throwable t) {
            }
        });
    }

    private void incluirFornecedor() {
        final Fornecedor[] fornecedores = new Fornecedor[1];
        fornecedores[0] = new Fornecedor();
        fornecedores[0].setIdConta(2);
        fornecedores[0].setIdEscola(1);
        fornecedores[0].setNome("Cássio Fornecedor");
        fornecedores[0].setCnpj("184145454554");
        fornecedores[0].setTelefone("489947455");

        API.postFornecedor(fornecedores, new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                System.out.println(response.body().getMensagem());
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {

            }
        });
    }

    private void listarProdutos() {
        API.getProdutos(2, 1, new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
               if(!response.body().isEmpty()){
                   for (Produto p : response.body()) {
                       System.out.println(p.getNome());
                   }
               }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void incluirProduto() {
        final Produto[] produtos = new Produto[1];
        produtos[0] = new Produto();
        produtos[0].setIdConta(10);
        produtos[0].setIdEscola(9);
        produtos[0].setNome("KKKKKKKKKK");
        produtos[0].setMarca("Lança");
        produtos[0].setUnidade("ML");
        produtos[0].setCodigoBarras("1263531253");


        API.postProdutos(produtos, new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                System.out.println(response.body().getMensagem());
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {

            }
        });
    }


}
