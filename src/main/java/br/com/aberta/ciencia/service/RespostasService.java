package br.com.aberta.ciencia.service;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.*;
import br.com.aberta.ciencia.repository.CategoriaPerguntaRepository;
import br.com.aberta.ciencia.repository.GrauMaturidadeRepository;
import br.com.aberta.ciencia.repository.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RespostasService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    List<CategoriaPergunta> categoriaPergunta;

    private Usuario usuario;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RespostasRepository respostasRepository;


    @Autowired
    private CategoriaPerguntaService categoriaPerguntaService;

    @Autowired
    private PerguntasService perguntasService;


    @Autowired
    private GrauMaturidadeService grauMaturidadeService;

    GrauMaturidade grauMaturidadeUsuario;

    public Respostas save(HashMap<String,Object> respostas) throws ResourceNotFoundException {

        categoriaPergunta = categoriaPerguntaService.findAll();
        ArrayList arrayList = (ArrayList) respostas.get("respostasUsuario");
        AtomicReference<Double> total_pesquisa = new AtomicReference<>((double) 0);
        categoriaPergunta.forEach(cat -> {
            AtomicInteger qtdPergunta = new AtomicInteger();
            int valor_maximo_categoria = cat.getPontosPossiveisCategoriaPergunta();
            ArrayList questoes_categoria = new ArrayList();
            AtomicInteger valor_selecionado = new AtomicInteger();
            HashMap<Integer, Integer> selecao = new HashMap<>();
            HashMap<Integer, Integer> selecaoTotalAlternativas = new HashMap<>();
            HashMap<Integer, Double> selecaoValor = new HashMap<>();
            HashMap<Integer, Double> alternative = new HashMap<>();
            HashMap<Integer, Integer> true_false = new HashMap<>();

            arrayList.stream()
                    .forEach(obj -> {
                        if ( (((LinkedHashMap<?, ?>) obj).get("idCategoria").toString()).equals(cat.getId().toString()) ) { //verifica se ID categoria IGUAL categoria

                            if (! ((((LinkedHashMap<?, ?>) obj).get("tipoPergunta")).toString()).equals("ABERTA")) { //verifica se não é questão aberta (abertas não entram na conta)

                                qtdPergunta.getAndIncrement();  //se não for aberta incrementa a qtd de perguntas da categoria
                                Integer idPergunta = (Integer)(((LinkedHashMap<?, ?>) obj).get("idPergunta")); //pega  ID da pergunta para verificar a qtd de alternativas tem (SELECAO e ALTERNATIVE)
                                System.out.println(idPergunta);
                                if (! ((((LinkedHashMap<?, ?>) obj).get("tipoPergunta")).toString()).equals("TRUE_FALSE")) { //verifica de não é true ou false - para true ou false basta verificar a resposta
                                    try { //se não for TRUE_FALSE
                                        Long id = idPergunta.longValue();
                                        Perguntas pergunta = perguntasService.findById(id); //busca pergunta com ID e verifica a qtd de alternativas
                                        //  System.out.println("QTD Alternativas " + pergunta.getRespostasPossiveisPergunta().size()+" questao "+idPergunta);

                                        //se pergunta SELECAO
                                        if((((LinkedHashMap<?, ?>) obj).get("tipoPergunta")).toString().equals("SELECAO")) {
                                            int totalAlternativas = pergunta.getRespostasPossiveisPergunta().size();
                                            if (selecao.get(idPergunta) != null){
                                                if ((((((LinkedHashMap<?, ?>) obj).get("estado"))).toString()).equals("true") ) {
                                                    int qtdSelecao = selecao.get(idPergunta).intValue() + 1;
                                                    selecao.replace(idPergunta, qtdSelecao);
                                                }
                                                qtdPergunta.getAndDecrement();
                                            }else{
                                                selecaoTotalAlternativas.put(idPergunta,totalAlternativas);
                                                if ((((((LinkedHashMap<?, ?>) obj).get("estado"))).toString()).equals("true") ){
                                                    selecao.put(idPergunta,1);
                                                }else{
                                                    selecao.put(idPergunta,0);
                                                }

                                            }
                                        }
                                        //se pergunta Alternative
                                        if((((LinkedHashMap<?, ?>) obj).get("tipoPergunta")).toString().equals("ALTERNATIVE")) {

                                            Integer idAlternativa = (Integer)(((LinkedHashMap<?, ?>) obj).get("idAlternativa"));
                                            System.out.println("alternativa possiveis: " + pergunta.getRespostasPossiveisPergunta().size());
                                            double valorAlternativa = 0;
                                            System.out.println("alternativa : " + idAlternativa);
                                            if (pergunta.getRespostasPossiveisPergunta().size() == (idAlternativa+1)){
                                                // valorAlternativa = idAlternativa+1;
                                                valorAlternativa = 1;
                                            }else{
                                                if ((pergunta.getRespostasPossiveisPergunta().size() == 0) || (pergunta.getRespostasPossiveisPergunta().size() == 1) ) {
                                                   valorAlternativa = 0;
                                                }else{
                                                    valorAlternativa = 0.5;
                                                }

                                            }
                                            if (alternative.get(idPergunta) != null){
                                                alternative.replace(idPergunta, valorAlternativa);
                                            }else{
                                                alternative.put(idPergunta,valorAlternativa);
                                            }
                                        }
                                    } catch (ResourceNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }else{ // se for TRUE_FALSE
                                    if ((((((LinkedHashMap<?, ?>) obj).get("resposta"))).toString()).equals("true") ) {
                                        true_false.put(idPergunta,1);
                                    }else{
                                        true_false.put(idPergunta,0);
                                    }

                                }
                            }
                        }
                    });

            AtomicReference<Double> total_categoria = new AtomicReference<>((double) 0);

            double valor_maximo_questao = ((double) valor_maximo_categoria / (double) qtdPergunta.get());

            selecaoTotalAlternativas.forEach((key,value) -> { //seleção
                double valor_questao = (double)((valor_maximo_questao/value) * selecao.get(key));
                total_categoria.set(total_categoria.get() + valor_questao);
            });

            true_false.forEach((key,value) -> { // True False
                double valor_questao = (double)(valor_maximo_questao * true_false.get(key));
                total_categoria.set(total_categoria.get() + valor_questao);
            });

            alternative.forEach((key,value) -> { //Alternative
                System.out.println("key: " + key + ", value: " + value);
                double valor_questao = (double)(valor_maximo_questao * alternative.get(key));
                total_categoria.set(total_categoria.get() + valor_questao);
            });

            total_pesquisa.set(total_pesquisa.get() + total_categoria.get());
            System.out.println ("total  "+total_pesquisa.get());
            // qtdPergunta.set(0);

        });
        Integer totalPesquisaInteiro = total_pesquisa.get().intValue();
        grauMaturidadeUsuario = grauMaturidadeService.findByNivelMaturidade(totalPesquisaInteiro,totalPesquisaInteiro);
        usuario = usuarioService.findById(Long.valueOf((String) respostas.get("idUsuario")));

        Respostas montaResposta = new Respostas();

        montaResposta.setIdUsuario(Long.valueOf((String) respostas.get("idUsuario")));
        montaResposta.setInstituicaoUsuario(usuario.getInstituicaoUsuario());
        montaResposta.setRespostasUsuario(arrayList);
        montaResposta.setId(sequenceGeneratorService.generateSequence(Respostas.SEQUENCE_NAME));
        double scale = Math.pow(10, 2);
        montaResposta.setPontuacaoUsuario(Math.round(total_pesquisa.get()*scale)/scale); // truncado valor pra 2 casas decimais
        montaResposta.setGrauMaturidadeUsuario(grauMaturidadeUsuario);
        montaResposta.setDivulgaUsuario(usuario.getPermissaoDivulgacaoDadosUsuario());
        return respostasRepository.save(montaResposta);
    }


    //lista todas categorias
    public List<Respostas> findAll(){
        return respostasRepository.findAllByOrderByGrauMaturidadeUsuario();
    }

    public Respostas findRespostaUsuario(Long idUsuario) {
        return respostasRepository.findByIdUsuario(idUsuario);
    }


    //Atualiza divulga Usuario alteração cadastro
    public Respostas updateDivulgaCadastro(Long usuarioId, Boolean divulgaCadastro) throws ResourceNotFoundException {
        Respostas respostasUpdate = findRespostaUsuario(usuarioId);
        respostasUpdate.setDivulgaUsuario(divulgaCadastro);
        return respostasRepository.save(respostasUpdate);
    }

}