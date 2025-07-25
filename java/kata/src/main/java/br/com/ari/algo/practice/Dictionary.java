package br.com.ari.algo.practice;

import br.com.ari.algo.structures.TrieWithTreeMap;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

    public static void main(String[] args) {
        var dict = loadDictionary();
        var scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao buscador de palavras maroto.");
        System.out.println("Procure palavras começando com qualquer letra ou prefixo.");
        System.out.println("Para sair, digite \\q\n");

        while (true) {
            System.out.print("Palavra começando com: ");
            String word = scanner.nextLine().trim();

            if (word.isBlank()) {
                continue;
            }

            if (word.equals("\\q")) {
                System.out.println("Saindo...");
                break;
            }
            
            long startTime = System.nanoTime();

            List<String> found = dict.find(word);

            long endTime = System.nanoTime();
            long elapsedTimeNanos = endTime - startTime;

            System.out.printf("Palavras encontradas: %d%n", found.size());
            System.out.printf("Tempo de execução do método find: %d nanosegundos (%f segundos)%n%n", elapsedTimeNanos, elapsedTimeNanos / 1_000_000. / 1_000.);
            System.out.println("Resultado:");
            System.out.println("==========");
            found.forEach(System.out::println);
            System.out.println();
        }
    }
    
    private static TrieWithTreeMap loadDictionary() {
        String[] words = new String[]{
            // Substantivos comuns
            "casa", "tempo", "ano", "dia", "coisa", "homem", "mulher", "vida", "mão", "parte", "vez", "olho", "lugar", "trabalho", "pessoa", "hora", "mundo", "forma", "caso", "governo", 
            "problema", "país", "ponto", "momento", "estado", "caminho", "tipo", "palavra", "fato", "porta", "cabeça", "pai", "mãe", "lado", "filho", "noite", "questão", "pé", "água", "terra", 
            "rua", "fim", "força", "nome", "sala", "corpo", "amigo", "mês", "ideia", "ar", "dinheiro", "poder", "amor", "arte", "livro", "resultado", "história", "tipo", "medo", "voz", 
            "cidade", "grupo", "futuro", "social", "processo", "sentido", "serviço", "frente", "linha", "projeto", "escola", "razão", "deus", "lei", "papel", "condição", "direito", "programa", "jogo", "sistema", 
            "ordem", "presidente", "empresa", "informação", "mercado", "número", "obra", "semana", "família", "experiência", "sociedade", "exemplo", "relação", "público", "segurança", "desenvolvimento", "educação", "efeito", "imagem", "ação", 
            "produção", "política", "atividade", "cultura", "função", "região", "situação", "movimento", "interesse", "área", "centro", "objetivo", "decisão", "população", "economia", "pesquisa", "qualidade", "saúde", "setor", "tecnologia", 
            "ambiente", "comunidade", "controle", "diferença", "espaço", "estrutura", "indústria", "material", "medida", "natureza", "nível", "origem", "posição", "presença", "princípio", "realidade", "recurso", "responsabilidade", "valor", "verdade", 
            "animal", "árvore", "boca", "braço", "cachorro", "carro", "céu", "chão", "coração", "dedo", "flor", "folha", "gato", "janela", "jardim", "lua", "mar", "mesa", "montanha", "nariz", 
            "nuvem", "ombro", "pássaro", "peixe", "planta", "praia", "rio", "roupa", "sangue", "sol", "telefone", "televisão", "teto", "vento", "viagem", "avião", "bola", "cadeira", "cama", "comida", 
            "computador", "criança", "dente", "doença", "estrela", "fogo", "guerra", "hospital", "hotel", "igreja", "jornal", "loja", "médico", "música", "notícia", "pedra", "ponte", "presente", "professor", "restaurante", 
            "rocha", "sapato", "teatro", "trem", "universidade", "vinho", "vizinho", "aula", "bebida", "bicicleta", "bolsa", "calça", "caneta", "celular", "cerveja", "chave", "chocolate", "cinema", "clube", "conta", 
            "cozinha", "documento", "escritório", "estrada", "festa", "filme", "foto", "futebol", "garfo", "garrafa", "gente", "internet", "lápis", "leite", "língua", "lixo", "loja", "mala", "mapa", "máquina", 
            "mercado", "metro", "moto", "ônibus", "página", "pão", "parque", "passagem", "perna", "piscina", "praça", "preço", "prédio", "prova", "quarto", "revista", "roupa", "sinal", "site", "sofá", 
            "supermercado", "tarefa", "teclado", "texto", "tijolo", "tinta", "toalha", "vidro", "vila", "vitamina", "academia", "açúcar", "aeroporto", "agenda", "alface", "almoço", "altura", "amizade", "aniversário", "apartamento", 
            
            // Verbos comuns
            "ser", "estar", "ter", "fazer", "dizer", "ir", "ver", "poder", "saber", "querer", "dar", "falar", "achar", "deixar", "passar", "ficar", "chegar", "partir", "voltar", "levar", 
            "gostar", "começar", "precisar", "pensar", "parecer", "olhar", "sentir", "encontrar", "tornar", "trazer", "ouvir", "pedir", "colocar", "existir", "entrar", "trabalhar", "viver", "sair", "chamar", "conseguir", 
            "contar", "correr", "criar", "perder", "receber", "responder", "seguir", "servir", "tirar", "tomar", "tratar", "usar", "vender", "abrir", "acabar", "ajudar", "andar", "aparecer", "beber", "buscar", 
            "cair", "comer", "comprar", "conhecer", "considerar", "continuar", "conversar", "crescer", "cuidar", "decidir", "deixar", "desenvolver", "dormir", "entender", "enviar", "escrever", "esperar", "estudar", "explicar", "fechar", 
            "jogar", "lembrar", "ler", "levantar", "ligar", "mostrar", "morrer", "mudar", "nascer", "oferecer", "pagar", "parar", "participar", "permitir", "preparar", "procurar", "produzir", "realizar", "reconhecer", "representar", 
            "resolver", "subir", "tentar", "terminar", "tocar", "viajar", "aceitar", "acreditar", "agradecer", "apresentar", "atender", "aumentar", "avisar", "brincar", "caminhar", "cantar", "carregar", "chorar", "combinar", "comparar", 
            "completar", "compreender", "comunicar", "confiar", "construir", "convidar", "cumprir", "dançar", "demonstrar", "descansar", "descobrir", "descrever", "desejar", "dirigir", "discutir", "dividir", "duvidar", "eliminar", "enfrentar", "ensinar", 
            "escolher", "esconder", "esquecer", "estabelecer", "evitar", "faltar", "funcionar", "ganhar", "garantir", "guardar", "imaginar", "importar", "incluir", "indicar", "iniciar", "interessar", "lançar", "limpar", "manter", "melhorar", 
            "mentir", "merecer", "morar", "nadar", "negar", "observar", "ocorrer", "odiar", "organizar", "pegar", "perceber", "perguntar", "pertencer", "planejar", "praticar", "preferir", "preocupar", "provar", "quebrar", "reclamar", 
            "reduzir", "referir", "refletir", "registrar", "repetir", "respeitar", "responder", "reunir", "revelar", "rir", "sofrer", "sonhar", "sorrir", "sugerir", "superar", "surgir", "surpreender", "transformar", "utilizar", "valorizar", 
            "variar", "verificar", "vestir", "visitar", "voar", "voltar", "abraçar", "acender", "acontecer", "acordar", "acusar", "adaptar", "adiantar", "admirar", "admitir", "adorar", "adquirir", "afetar", "afirmar", "agir", 
            "aguentar", "alcançar", "alegrar", "amar", "ampliar", "analisar", "animar", "anotar", "aplicar", "apoiar", "aprender", "aproveitar", "aproximar", "arrumar", "assustar", "atirar", "atrair", "atuar", "avaliar", "avançar", 
            
            // Adjetivos comuns
            "bom", "grande", "novo", "pequeno", "próprio", "último", "certo", "melhor", "maior", "único", "alto", "diferente", "social", "político", "público", "nacional", "difícil", "importante", "possível", "geral", 
            "simples", "forte", "especial", "principal", "capaz", "claro", "antigo", "velho", "longo", "branco", "preto", "azul", "verde", "vermelho", "amarelo", "bonito", "feio", "feliz", "triste", "fácil", 
            "rápido", "lento", "quente", "frio", "cheio", "vazio", "caro", "barato", "pesado", "leve", "doce", "amargo", "duro", "mole", "limpo", "sujo", "seco", "molhado", "aberto", "fechado", 
            "rico", "pobre", "jovem", "velho", "baixo", "magro", "gordo", "largo", "estreito", "profundo", "raso", "curto", "comprido", "redondo", "quadrado", "fino", "grosso", "macio", "áspero", "brilhante", 
            "opaco", "barulhento", "silencioso", "ocupado", "livre", "famoso", "desconhecido", "inteligente", "burro", "sábio", "ignorante", "educado", "mal-educado", "gentil", "rude", "calmo", "nervoso", "corajoso", "medroso", "honesto", 
            "desonesto", "justo", "injusto", "legal", "ilegal", "correto", "incorreto", "verdadeiro", "falso", "real", "imaginário", "natural", "artificial", "necessário", "desnecessário", "útil", "inútil", "completo", "incompleto", "perfeito", 
            "imperfeito", "seguro", "perigoso", "saudável", "doente", "vivo", "morto", "presente", "ausente", "próximo", "distante", "direito", "esquerdo", "superior", "inferior", "interno", "externo", "anterior", "posterior", "inicial", 
            "final", "positivo", "negativo", "ativo", "passivo", "rápido", "devagar", "fácil", "complicado", "simples", "complexo", "direto", "indireto", "exato", "aproximado", "absoluto", "relativo", "fixo", "móvel", "estável", 
            "instável", "permanente", "temporário", "constante", "variável", "regular", "irregular", "normal", "anormal", "comum", "raro", "típico", "atípico", "adequado", "inadequado", "suficiente", "insuficiente", "eficiente", "ineficiente", "eficaz", 
            "ineficaz", "prático", "teórico", "concreto", "abstrato", "específico", "geral", "global", "local", "universal", "particular", "total", "parcial", "máximo", "mínimo", "ótimo", "péssimo", "favorável", "desfavorável", "vantajoso", 
            "desvantajoso", "benéfico", "prejudicial", "agradável", "desagradável", "confortável", "desconfortável", "conveniente", "inconveniente", "atraente", "repulsivo", "interessante", "entediante", "divertido", "chato", "engraçado", "sério", "formal", "informal", "oficial", 
            "extraoficial", "legal", "ilegal", "legítimo", "ilegítimo", "justo", "injusto", "moral", "imoral", "ético", "antiético", "lógico", "ilógico", "racional", "irracional", "razoável", "irrazoável", "sensato", "insensato", "consciente", 
            
            // Advérbios e outras palavras
            "não", "sim", "mais", "menos", "muito", "pouco", "bem", "mal", "assim", "também", "só", "apenas", "mesmo", "já", "ainda", "sempre", "nunca", "agora", "depois", "antes", 
            "hoje", "ontem", "amanhã", "aqui", "ali", "lá", "dentro", "fora", "acima", "abaixo", "longe", "perto", "cedo", "tarde", "depressa", "devagar", "bastante", "demais", "quase", "talvez", 
            "certamente", "realmente", "provavelmente", "possivelmente", "finalmente", "principalmente", "especialmente", "geralmente", "normalmente", "raramente", "frequentemente", "ocasionalmente", "absolutamente", "completamente", "totalmente", "parcialmente", "exatamente", "aproximadamente", "precisamente", "vagamente", 
            "claramente", "obviamente", "evidentemente", "aparentemente", "supostamente", "definitivamente", "certamente", "seguramente", "verdadeiramente", "falsamente", "corretamente", "incorretamente", "adequadamente", "inadequadamente", "propriamente", "impropriamente", "legalmente", "ilegalmente", "moralmente", "imoralmente", 
            "formalmente", "informalmente", "oficialmente", "extraoficialmente", "publicamente", "privadamente", "abertamente", "secretamente", "diretamente", "indiretamente", "explicitamente", "implicitamente", "literalmente", "figurativamente", "pessoalmente", "impessoalmente", "individualmente", "coletivamente", "socialmente", "politicamente", 
            "economicamente", "culturalmente", "historicamente", "geograficamente", "cientificamente", "tecnicamente", "teoricamente", "praticamente", "logicamente", "psicologicamente", "fisicamente", "mentalmente", "emocionalmente", "intelectualmente", "verbalmente", "visualmente", "auditivamente", "manualmente", "automaticamente", "naturalmente", 
            "artificialmente", "voluntariamente", "involuntariamente", "conscientemente", "inconscientemente", "deliberadamente", "acidentalmente", "intencionalmente", "casualmente", "gradualmente", "repentinamente", "constantemente", "intermitentemente", "periodicamente", "regularmente", "irregularmente", "continuamente", "descontinuamente", "simultaneamente", "sucessivamente", 
            "anteriormente", "posteriormente", "atualmente", "recentemente", "antigamente", "futuramente", "temporariamente", "permanentemente", "provisoriamente", "definitivamente", "momentaneamente", "instantaneamente", "brevemente", "longamente", "eternamente", "infinitamente", "limitadamente", "ilimitadamente", "relativamente", "absolutamente",
            
            // Palavras adicionais para completar 1000 palavras
            "abacate", "abacaxi", "banana", "batata", "berinjela", "cebola", "cenoura", "laranja", "limão", "maçã", "mamão", "manga", "melancia", "morango", "pêssego", "tomate", "uva", "aluno", "professor", "médico",
            "engenheiro", "advogado", "arquiteto", "dentista", "enfermeiro", "farmacêutico", "psicólogo", "bombeiro", "policial", "motorista", "piloto", "cozinheiro", "garçom", "vendedor", "empresário", "atleta", "artista", "músico", "ator", "escritor",
            "pintor", "escultor", "fotógrafo", "jornalista", "cientista", "programador", "designer", "tradutor", "contador", "economista", "administrador", "secretário", "recepcionista", "telefonista", "carteiro", "eletricista", "mecânico", "pedreiro", "carpinteiro", "encanador",
            "jardineiro", "pescador", "agricultor", "fazendeiro", "padeiro", "açougueiro", "sapateiro", "costureiro", "barbeiro", "cabeleireiro", "manicure", "maquiador", "massagista", "personal", "treinador", "instrutor", "guia", "turista", "viajante", "estudante",
            
            // Mais 1000 palavras em português brasileiro
            // Substantivos - Alimentos e Bebidas
            "arroz", "feijão", "carne", "frango", "peixe", "salada", "sopa", "molho", "azeite", "vinagre", "sal", "pimenta", "alho", "cebola", "tomate", "alface", "cenoura", "batata", "milho", "ervilha",
            "lentilha", "grão", "farinha", "massa", "macarrão", "espaguete", "lasanha", "pizza", "sanduíche", "hambúrguer", "cachorro-quente", "queijo", "presunto", "salame", "mortadela", "iogurte", "manteiga", "margarina", "óleo", "refrigerante",
            "suco", "água", "café", "chá", "leite", "cerveja", "vinho", "whisky", "vodka", "rum", "champanhe", "coquetel", "sobremesa", "bolo", "torta", "pudim", "sorvete", "chocolate", "biscoito", "bolacha",
            
            // Substantivos - Casa e Mobiliário
            "apartamento", "casa", "mansão", "chalé", "cabana", "quarto", "sala", "cozinha", "banheiro", "garagem", "jardim", "quintal", "terraço", "varanda", "sacada", "escada", "elevador", "corredor", "porta", "janela",
            "telhado", "parede", "piso", "teto", "sofá", "poltrona", "cadeira", "mesa", "cama", "colchão", "travesseiro", "cobertor", "lençol", "edredom", "armário", "guarda-roupa", "estante", "prateleira", "gaveta", "espelho",
            "quadro", "tapete", "cortina", "persiana", "luminária", "abajur", "lustre", "vaso", "planta", "flor", "relógio", "despertador", "geladeira", "fogão", "forno", "micro-ondas", "liquidificador", "batedeira", "torradeira", "cafeteira",
            
            // Substantivos - Tecnologia
            "computador", "notebook", "tablet", "smartphone", "celular", "teclado", "mouse", "monitor", "impressora", "scanner", "webcam", "microfone", "fone", "alto-falante", "carregador", "bateria", "cabo", "pendrive", "disco", "memória",
            "processador", "placa", "software", "programa", "aplicativo", "sistema", "rede", "internet", "wifi", "bluetooth", "nuvem", "servidor", "banco", "dados", "arquivo", "pasta", "documento", "planilha", "apresentação", "vídeo",
            "áudio", "imagem", "foto", "câmera", "filmadora", "drone", "robô", "inteligência", "algoritmo", "código", "programação", "desenvolvimento", "design", "interface", "usuário", "experiência", "navegador", "site", "blog", "rede",
            
            // Substantivos - Profissões e Ocupações
            "médico", "enfermeiro", "dentista", "farmacêutico", "psicólogo", "terapeuta", "fisioterapeuta", "nutricionista", "veterinário", "biólogo", "químico", "físico", "matemático", "engenheiro", "arquiteto", "construtor", "eletricista", "encanador", "mecânico", "técnico",
            "professor", "diretor", "coordenador", "pedagogo", "estudante", "pesquisador", "cientista", "historiador", "geógrafo", "sociólogo", "antropólogo", "filósofo", "advogado", "juiz", "promotor", "delegado", "policial", "bombeiro", "militar", "segurança",
            "empresário", "empreendedor", "gerente", "supervisor", "coordenador", "analista", "consultor", "vendedor", "atendente", "recepcionista", "secretário", "assistente", "contador", "economista", "administrador", "bancário", "corretor", "investidor", "agricultor", "pescador",
            
            // Substantivos - Lugares
            "país", "estado", "cidade", "bairro", "rua", "avenida", "praça", "parque", "jardim", "floresta", "campo", "fazenda", "sítio", "montanha", "vale", "rio", "lago", "mar", "praia", "ilha",
            "deserto", "selva", "escola", "faculdade", "universidade", "biblioteca", "museu", "teatro", "cinema", "estádio", "ginásio", "academia", "hospital", "clínica", "consultório", "farmácia", "laboratório", "escritório", "empresa", "fábrica",
            "loja", "shopping", "supermercado", "mercado", "feira", "restaurante", "bar", "café", "padaria", "confeitaria", "hotel", "pousada", "resort", "aeroporto", "rodoviária", "estação", "metrô", "porto", "estrada", "rodovia",
            
            // Verbos
            "analisar", "calcular", "comparar", "competir", "completar", "complicar", "compor", "compreender", "comprometer", "comunicar", "conceder", "concentrar", "concluir", "concordar", "concorrer", "condenar", "conduzir", "conectar", "confeccionar", "conferir",
            "confessar", "confiar", "confirmar", "confrontar", "confundir", "congelar", "congratular", "conhecer", "conquistar", "conseguir", "conservar", "considerar", "consistir", "consolidar", "conspirar", "constituir", "construir", "consultar", "consumir", "contar",
            "contemplar", "contentar", "contestar", "continuar", "contrair", "contratar", "contribuir", "controlar", "convencer", "conviver", "convocar", "cooperar", "coordenar", "copiar", "correr", "corrigir", "corromper", "cortar", "costurar", "cozinhar",
            "criar", "criticar", "cruzar", "cuidar", "cultivar", "cumprir", "curar", "cursar", "curvar", "dançar", "danificar", "dar", "debater", "decidir", "declarar", "decorar", "dedicar", "defender", "definir", "deformar",
            "delegar", "deletar", "deliberar", "demonstrar", "denominar", "denunciar", "depender", "deplorar", "depositar", "depreciar", "derivar", "derrotar", "derrubar", "desabafar", "desafiar", "desagradar", "desaparecer", "desapontar", "desarmar", "desenvolver",
            
            // Adjetivos
            "abundante", "acessível", "adequado", "administrativo", "adorável", "adulto", "afetivo", "afiado", "africano", "agressivo", "agudo", "alemão", "alérgico", "aleatório", "alfabético", "alto", "amargo", "ambicioso", "ambíguo", "americano",
            "amigável", "amplo", "analítico", "ancestral", "angelical", "animado", "anônimo", "ansioso", "anterior", "antigo", "aparente", "apático", "apavorado", "aperfeiçoado", "apetitoso", "aplicado", "apressado", "apropriado", "aprovado", "aproximado",
            "aquático", "árabe", "arbitrário", "ardente", "argelino", "argentino", "árido", "aristocrático", "arrogante", "artesanal", "artificial", "artístico", "asiático", "áspero", "assustado", "assustador", "astuto", "atencioso", "atento", "ativo",
            "atlético", "atmosférico", "atômico", "atônito", "atraente", "atrasado", "atual", "audacioso", "audiovisual", "auditivo", "aumentado", "austero", "australiano", "autêntico", "autoritário", "autônomo", "avançado", "avaro", "aventureiro", "ávido",
            "azedo", "azul", "baixo", "balanceado", "barato", "bárbaro", "básico", "bege", "belga", "belo", "benéfico", "benigno", "bilateral", "bilíngue", "biológico", "bizarro", "bom", "bonito", "brilhante", "britânico",
            
            // Advérbios e outras palavras
            "abaixo", "acima", "adiante", "agora", "ainda", "além", "ali", "amanhã", "antes", "antigamente", "apenas", "após", "aqui", "assim", "atualmente", "atrás", "bastante", "bem", "brevemente", "cá",
            "cedo", "certamente", "cima", "como", "completamente", "constantemente", "contrariamente", "cuidadosamente", "debaixo", "demais", "dentro", "depois", "depressa", "desde", "devagar", "diariamente", "diretamente", "durante", "efetivamente", "enfim",
            "enquanto", "então", "entretanto", "especialmente", "exatamente", "exclusivamente", "extremamente", "facilmente", "felizmente", "finalmente", "fora", "frequentemente", "geralmente", "hoje", "imediatamente", "inclusive", "infelizmente", "inicialmente", "intencionalmente", "jamais",
            "já", "junto", "lá", "lentamente", "ligeiramente", "logo", "longe", "mais", "mal", "melhor", "menos", "muito", "não", "naturalmente", "necessariamente", "nem", "nenhures", "no", "novamente", "nunca",
            "obviamente", "onde", "ontem", "particularmente", "perto", "pior", "pouco", "praticamente", "precisamente", "primeiramente", "principalmente", "provavelmente", "quando", "quase", "rapidamente", "raramente", "realmente", "recentemente", "relativamente", "sempre",
            
            // Expressões e frases comuns
            "bom-dia", "boa-tarde", "boa-noite", "até-logo", "até-amanhã", "obrigado", "de-nada", "por-favor", "com-licença", "desculpe", "tudo-bem", "como-vai", "prazer-em-conhecê-lo", "seja-bem-vindo", "parabéns", "felicidades", "boa-sorte", "boa-viagem", "bom-apetite", "saúde",
            "tchau", "adeus", "olá", "oi", "alô", "socorro", "ajuda", "cuidado", "atenção", "perigo", "proibido", "permitido", "aberto", "fechado", "entrada", "saída", "empurre", "puxe", "pare", "siga",
            "vire", "direita", "esquerda", "frente", "atrás", "norte", "sul", "leste", "oeste", "centro", "em-cima", "embaixo", "dentro", "fora", "perto", "longe", "início", "fim", "meio", "lado",
            "primeiro", "último", "próximo", "anterior", "seguinte", "este", "esse", "aquele", "meu", "seu", "nosso", "vosso", "dele", "dela", "deles", "delas", "quem", "qual", "quando", "onde",
            "como", "porque", "para-que", "quanto", "quanto-tempo", "quanto-custa", "que-horas", "que-dia", "que-mês", "que-ano", "sim", "não", "talvez", "certamente", "provavelmente", "possivelmente", "nunca", "sempre", "às-vezes", "raramente",
            
            // Nomes brasileiros
            // Nomes masculinos
            "Miguel", "Arthur", "Bernardo", "Heitor", "Davi", "Lorenzo", "Théo", "Pedro", "Gabriel", "Enzo", "Matheus", "Lucas", "Benjamin", "Nicolas", "Guilherme", "Rafael", "Joaquim", "Samuel", "Enzo Gabriel", "João Miguel",
            "Henrique", "Gustavo", "Murilo", "Pedro Henrique", "Pietro", "Lucca", "Felipe", "João Pedro", "Isaac", "Benício", "Daniel", "Anthony", "Leonardo", "Davi Lucca", "Bryan", "Eduardo", "João Lucas", "Victor", "João", "Cauã",
            "Antônio", "Vicente", "Caleb", "Gael", "Bento", "Caio", "Emanuel", "Vinícius", "João Guilherme", "Davi Lucas", "Noah", "João Gabriel", "João Victor", "Luiz Miguel", "Francisco", "Kaique", "Otávio", "Augusto", "Levi", "Yuri",
            "Enrico", "Thiago", "Ian", "Victor Hugo", "Thomas", "Henry", "Luiz Felipe", "Ryan", "Arthur Miguel", "Davi Luiz", "Nathan", "Pedro Lucas", "Davi Miguel", "Raul", "Pedro Miguel", "Luiz Henrique", "Luan", "Erick", "Martin", "Bruno",
            "Rodrigo", "Luiz Gustavo", "Arthur Gabriel", "Breno", "Kauê", "Enzo Miguel", "Fernando", "Arthur Henrique", "Luiz Otávio", "Carlos Eduardo", "Tomás", "Lucas Gabriel", "André", "José", "Yago", "Danilo", "Anthony Gabriel", "Ruan", "Miguel Henrique", "Oliver",
            
            // Nomes femininos
            "Alice", "Sophia", "Helena", "Valentina", "Laura", "Isabella", "Manuela", "Júlia", "Heloísa", "Luiza", "Maria Luiza", "Lorena", "Lívia", "Giovanna", "Maria Eduarda", "Beatriz", "Maria Clara", "Cecília", "Eloá", "Lara",
            "Maria Júlia", "Isadora", "Mariana", "Emanuelly", "Ana Júlia", "Ana Luiza", "Ana Clara", "Melissa", "Yasmin", "Maria Alice", "Isabelly", "Lavínia", "Esther", "Sarah", "Elisa", "Antonella", "Rafaela", "Maria Cecília", "Liz", "Marina",
            "Nicole", "Maitê", "Isis", "Alícia", "Luna", "Rebeca", "Agatha", "Letícia", "Maria", "Gabriela", "Ana Laura", "Catarina", "Clara", "Ana Beatriz", "Vitória", "Olívia", "Maria Fernanda", "Emilly", "Maria Valentina", "Milena",
            "Maria Helena", "Bianca", "Larissa", "Mirella", "Maria Flor", "Allana", "Ana Sophia", "Clarice", "Pietra", "Maria Vitória", "Maya", "Laís", "Ayla", "Ana Lívia", "Eduarda", "Mariah", "Stella", "Ana", "Gabrielly", "Sophie",
            "Carolina", "Maria Laura", "Maria Heloísa", "Maria Sophia", "Fernanda", "Malu", "Analu", "Amanda", "Aurora", "Maria Isis", "Louise", "Heloise", "Ana Vitória", "Ana Cecília", "Ana Liz", "Joana", "Luana", "Antônia", "Isabel", "Bruna",
            
            // Nomes unissex e diversos
            "Alex", "Ariel", "Casey", "Chris", "Dakota", "Drew", "Eli", "Erin", "Ezra", "Finley", "Francis", "Gê", "Harley", "Hayden", "Jamie", "Jesse", "Jordan", "Jules", "Kai", "Kelly",
            "Kendall", "Lee", "Logan", "Morgan", "Nico", "Parker", "Quinn", "Reese", "Riley", "Robin", "Rowan", "Sam", "Sasha", "Shawn", "Sidney", "Skyler", "Taylor", "Toni", "Tracy", "Val",
            
            // Nomes de origem indígena brasileira
            "Abaeté", "Açucena", "Aimoré", "Aracy", "Araci", "Bartira", "Cauã", "Cauê", "Ceci", "Guaraci", "Iara", "Iberê", "Indira", "Ipê", "Iracema", "Iraê", "Itamar", "Jaci", "Janaína", "Jandira",
            "Jarí", "Jucá", "Jurema", "Maiara", "Moacir", "Moema", "Peri", "Potira", "Raoni", "Rudá", "Tainá", "Taís", "Tainá", "Tupã", "Ubirajara", "Ubiratã", "Yara", "Yuri", "Zaira", "Zumbi",
            
            // Nomes de origem africana
            "Abayomi", "Akin", "Amara", "Ayodele", "Aziza", "Badu", "Chima", "Dalila", "Dara", "Folami", "Gamba", "Imani", "Jabari", "Jelani", "Kali", "Kamau", "Kenia", "Kwame", "Layla", "Makena",
            "Mandisa", "Mosi", "Nala", "Nia", "Olufemi", "Oni", "Oya", "Paki", "Rudo", "Sefu", "Simba", "Tafari", "Thema", "Uzoma", "Zaire", "Zola", "Zuri", "Aisha", "Jamal", "Malik",
            
            // Nomes de origem italiana
            "Alessandro", "Alessandra", "Bianca", "Carla", "Carlo", "Dante", "Enzo", "Fabrizio", "Flavio", "Francesca", "Franco", "Gabriella", "Gianna", "Giovanni", "Giuseppe", "Isabella", "Lorenzo", "Luca", "Lucia", "Marco",
            "Matteo", "Nicola", "Paolo", "Pietro", "Roberto", "Romano", "Rosa", "Salvatore", "Sofia", "Valentina", "Vittoria", "Vittorio", "Adriano", "Adriana", "Antonella", "Antonio", "Carmela", "Chiara", "Dario", "Elisa",
            
            // Nomes de origem japonesa
            "Akemi", "Akira", "Ayumi", "Daiki", "Emi", "Hana", "Harumi", "Hideo", "Hikari", "Hiroshi", "Hitomi", "Isamu", "Jun", "Kaori", "Kazuo", "Keiko", "Ken", "Kiyoshi", "Kumiko", "Makoto",
            "Masao", "Michiko", "Minoru", "Naomi", "Noboru", "Rei", "Ren", "Ryo", "Sachiko", "Satoshi", "Sayuri", "Shigeru", "Takashi", "Tomoko", "Yoko", "Yoshi", "Yukio", "Yumi", "Yuri", "Yuji",
            
            // Nomes de origem alemã
            "Adolf", "Albert", "Alfredo", "Arnaldo", "Bruno", "Carlos", "Cristiano", "Dieter", "Edson", "Elmar", "Ernesto", "Fernando", "Frederico", "Germano", "Guilherme", "Günther", "Hans", "Helmut", "Henrique", "Herbert",
            "Hermann", "Horst", "Hugo", "Ingrid", "Irene", "Johanna", "Klaus", "Kurt", "Luís", "Manfred", "Matilde", "Otto", "Paulo", "Ricardo", "Roberto", "Rodolfo", "Rolf", "Ursula", "Walter", "Werner"
        };

        var dict = new TrieWithTreeMap();
        
        for (String word : words) {
            dict.insert(word);
        }
        
        return dict;
    }
    
}
