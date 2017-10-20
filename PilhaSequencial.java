/*
Algoritmos e estrutura de dados 2
aluno: Ygor Matheus Lacerda de Melo
Trabalho pratico 4
*/

import java.io.*;

class Series{

	private String nome;
	private String formato;
	private String duracao;
	private String paisOrigem;
	private String idiomaOriginal;
	private String emissoraTelevisaoOriginal;
	private String transmissaoOriginal;
	private int numTemporadas;
	private int numEpisodios;

	public Series(){}

	public Series(String nome, String formato, String duracao, String paisOrigem, String idiomaOriginal, 
		String emissoraTelevisaoOriginal, String transmissaoOriginal, int  numTemporadas, int numEpisodios){
		
		this.nome = nome;
		this.formato = formato;
		this.duracao = duracao;
		this.paisOrigem = paisOrigem;
		this.idiomaOriginal = idiomaOriginal;
		this.emissoraTelevisaoOriginal = emissoraTelevisaoOriginal;
		this.transmissaoOriginal = transmissaoOriginal;
		this.numTemporadas = numTemporadas;
		this.numEpisodios = numEpisodios;          
	}

	/*
	 *Metodos sets e gets
	 */
    public void setNome(String nome){
      this.nome = nome;
    }
    public String getNome(){
      return this.nome;
    }//nome

    public void setFormato(String formato){
      this.formato = formato;
    }
    public String getFormato(){
      return this.formato;
    }//formato

	public void setDuracao(String duracao){
      this.duracao = duracao;
    }
    public String getDuracao(){
      return this.duracao;
    }//duracao

    public void setPaisOrigem(String paisOrigem){
    	this.paisOrigem = paisOrigem;
    }
    public String getPaisOrigem(){
    	return this.paisOrigem;
    }//paisOrigem

    public void setIdiomaOriginal(String idiomaOriginal){
    	this.idiomaOriginal = idiomaOriginal;
    }
    public String getIdiomaOriginal(){
    	return this.idiomaOriginal;
    }//idiomaOriginal

    public void setEmissoraTelevisaoOriginal(String emissoraTelevisaoOriginal){
    	this.emissoraTelevisaoOriginal = emissoraTelevisaoOriginal;
    }
    public String getEmissoraTelevisaoOriginal(){
    	return this.emissoraTelevisaoOriginal;
    }//emissoraTelevisaoOriginal

    public void setTransmissaoOriginal(String transmissaoOriginal){
    	this.transmissaoOriginal = transmissaoOriginal;
    }
    public String getTransmissaoOriginal(){
    	return this.transmissaoOriginal;
    }//transmissaoOriginal

    public void setNumTemporadas(int numTemporadas){
    	this.numTemporadas = numTemporadas;
    }
    public int getNumTemporadas(){
    	return this.numTemporadas;
    }//numTemporadas

    public void setNumEpisodios(int numEpisodios){
    	this.numEpisodios = numEpisodios;
    }
    public int  getNumEpisodios(){
    	return this.numEpisodios;
    }//numEpisodios

    /**
	 * [Metodo clone que cria copia dos atributos]
	 * @return [Efetua o retorno do clone]
	 */
    public Series clone(){
    	Series clone = new Series();
        clone.nome = this.nome;
        clone.formato = this.formato;
        clone.duracao = this.duracao;
        clone.paisOrigem = this.paisOrigem;
        clone.idiomaOriginal = this.idiomaOriginal;
        clone.emissoraTelevisaoOriginal = this.emissoraTelevisaoOriginal;
        clone.transmissaoOriginal = this.transmissaoOriginal;
        clone.numTemporadas = this.numTemporadas;
        clone.numEpisodios = this.numEpisodios;

        return clone;
    }//fim metodo clone

    public String removeTags(String linhaEntrada){
    	String resposta = "";

        for (int i = 0; i < linhaEntrada.length(); i++){
            if(linhaEntrada.charAt(i) == '&'){
                i +=6;
            }
            if(linhaEntrada.charAt(i)!='<'){
                resposta += linhaEntrada.charAt(i);
            }
        }
  
        if(resposta.charAt(0) == ' '){
            return resposta.substring(1, resposta.length());
        }else{
            return resposta;
        }
    }//fim removeTags

    public String removeUnderLine(String linhaEntrada){
        String resposta = "";
        for(int i = 0; i <= linhaEntrada.length(); i++){
            if(linhaEntrada.charAt(i) == ' '){
                resposta += ' ';
            }else{
                resposta += linhaEntrada.charAt(i);
            }
        }
        return resposta;  
    }

    public int converteString2Int(String linhaEntrada){
        String resposta = "";
        
        int i = 0;
	    while(i < linhaEntrada.length() && (linhaEntrada.charAt(i) == '0' || linhaEntrada.charAt(i) == '1' || 
            linhaEntrada.charAt(i) == '2' || linhaEntrada.charAt(i) == '3' ||
            linhaEntrada.charAt(i) == '4' || linhaEntrada.charAt(i) == '5' || 
		    linhaEntrada.charAt(i) == '6' || linhaEntrada.charAt(i) == '7' || 
		    linhaEntrada.charAt(i) == '8' || linhaEntrada.charAt(i) == '9')){
  	     	resposta += linhaEntrada.charAt(i);
		    i++;
	    }
	    return Integer.parseInt(resposta);
    }   

    public Series leitura(String nomeArquivo) throws Exception{
    	Series series = new Series();

    	String linhaEntrada = null;
    	int entradaInt;
        
        int teste;
    	
    	BufferedReader arquivoEntrada = new BufferedReader(new FileReader("/tmp/" + nomeArquivo));
  
        linhaEntrada = arquivoEntrada.readLine();

    	if(nomeArquivo.contains(".html")){
        	String aux = nomeArquivo.replace(".html", "");
        	setNome(aux.replace("_", " "));
        }
        /*if(linhaEntrada.contains(" (lista de episódios)")){
         	linhaEntrada.replace(" (lista de episódios)", "");
        }*/

        while((linhaEntrada = arquivoEntrada.readLine()) != null){
            if(linhaEntrada.contains(">Formato</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            formato = removeTags(linhaEntrada).trim();
            }
            else if(linhaEntrada.contains(">Duração</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            duracao = removeTags(linhaEntrada).trim();
            }
            else if(linhaEntrada.contains(">País de origem</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            paisOrigem = removeTags(linhaEntrada).trim();
            }
            else if(linhaEntrada.contains(">Idioma original</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            idiomaOriginal = removeTags(linhaEntrada);
            }
            else if(linhaEntrada.contains(">Emissora de televisão original</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            emissoraTelevisaoOriginal = removeTags(linhaEntrada).trim();
            }
            else if(linhaEntrada.contains(">Transmissão original</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            transmissaoOriginal = removeTags(linhaEntrada).trim();
            }
            else if(linhaEntrada.contains(">N.º de temporadas</td>")){
	            linhaEntrada = arquivoEntrada.readLine();
	            linhaEntrada = removeTags(linhaEntrada);
	            this.numTemporadas = converteString2Int(linhaEntrada);
            }
            else if(linhaEntrada.contains(">N.º de episódios</td>")){
                linhaEntrada = arquivoEntrada.readLine();
	            linhaEntrada = removeTags(linhaEntrada);
                this.numEpisodios = converteString2Int(linhaEntrada);
            }
    	}      
        Arq.close();

        return new Series(nome, formato, duracao, paisOrigem, idiomaOriginal, emissoraTelevisaoOriginal, transmissaoOriginal,
         numTemporadas, numEpisodios);
    }//fim leitura 

    public void imprimir(){
    	MyIO.println(this.nome + " " + this.formato + " " + this.duracao + " " + this.paisOrigem + " " + 
    			    this.idiomaOriginal + " " + this.emissoraTelevisaoOriginal+ " " + this.transmissaoOriginal + " " + 
    			    this.numTemporadas + " " + this.numEpisodios + "");
    }//fim imprimir
}//Series

class Pilha {
    private Series[] array;
    private int topo;

    /**
     * Construtor da classe.
     */
    public Pilha () {
        this(1000);
    }

    /**
     * Construtor da classe.
     * @param tamanho Tamanho da pilha.
     */
    public Pilha (int tamanho){
        array = new Series[tamanho];
        topo = 0;
    }

    /**
     * Insere um elemento na ultima posicao da pilha.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a pilha estiver cheia.
     */
    public void inserir(Series x) throws Exception {
        //validar insercao
        if(topo > array.length){
            throw new Exception("Erro ao inserir!");
        }
        array[topo] = x.clone();
        topo++;
    } 

    /**
     * Remove um elemento da ultima posicao da pilha.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a pilha estiver vazia.
     */
    public Series remover() throws Exception {
        //validar remocao
        if (topo == 0) {
            throw new Exception("Erro ao remover!");
        }
        topo--;
        Series resp = array[topo];
        return resp;
    }
 
    /**
     * Mostra os array separados por espacos.
     */
    public void mostrar (){
        for(int i = topo -1; i >= 0; i--){
            array[i].imprimir();
        }
    }

    /**
     * Retorna um boolean indicando se a pilha esta vazia
     * @return boolean indicando se a pilha esta vazia
     */
    public boolean isVazia() {
        return topo == 0; 
    }
}

class PilhaSequencial{

    public static void main(String[]args) throws Exception{
        
        Series series = new Series();
        Pilha pilha = new Pilha(1000);
        MyIO.setCharset("UTF-8");
        
        String [] nomeArquivo = new String[10000];
        
        int aux = 0;
        int posicao = 0;
        int numEntrada = 0;
        
        nomeArquivo[numEntrada] = MyIO.readLine();

        //Leitura da entrada padrao
        while(!nomeArquivo[numEntrada].equals("FIM")){
            series.leitura(nomeArquivo[numEntrada]);
            pilha.inserir(series);
            numEntrada++;
            nomeArquivo[numEntrada] = MyIO.readLine();
        }

        String entrada2;

        aux = Integer.parseInt(MyIO.readLine());

        for(int i = 0; i < aux; i++){
            entrada2 = MyIO.readLine();
            if(entrada2.charAt(0) == 'I'){
                series.leitura(entrada2.substring(2, entrada2.length()));
                pilha.inserir(series);
            }
            else if(entrada2.charAt(0) == 'R'){
                MyIO.println("(R) " + pilha.remover().getNome());
            }
        }
        pilha.mostrar();
    }//Fim main
}
