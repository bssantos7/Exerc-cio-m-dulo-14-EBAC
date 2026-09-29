package com.example;
import java.util.Scanner;

public class Hotel {
    private Reserva [] quartos;
    private int ocupacao;

    public Hotel(){
        this.quartos=new Reserva[10];
        this.ocupacao=0;
    }

    public Hotel(int quantidade){
        this.quartos= new Reserva[quantidade];
        this.ocupacao=0;
    }

    public Reserva[] getQuartos() {
        return quartos;
    }

    public void setQuartos(Reserva[] quartos) {
        this.quartos = quartos;
    }
    
    public void setOcupacao(int quantidade){
        this.ocupacao=quantidade;
    }

    public int getOcupacao(){
        return this.ocupacao;
    }

    private Reserva criarReserva(Scanner scanner){
        String nomeHospede;
        int opcao;
        String tipoQuarto="";
        double valorDiaria=0;
        int numeroDias=0;
        boolean repetir=true;
        double valorTotal=0;
        Reserva novareReserva;

        System.out.println("===CADASTRO DE NOVA RESERVA ===");
        System.out.println("Nome do Hóspede: ");
        nomeHospede=scanner.nextLine();
        System.out.println("Tipo de quarto. Digite:");
        System.out.println("1 - Standard - R$ 50,00");
        System.out.println("2 - Luxo - R$ 100,00");
        System.out.println("3 - Presidencial - R$ 250,00");
        opcao=Integer.parseInt(scanner.nextLine());

        repetir=true;
        
        do{
            switch (opcao) {
                case 1:
                    tipoQuarto="Standard";
                    valorDiaria=50;
                    repetir=false;
                    break;
                case 2:
                    tipoQuarto="Luxo";
                    valorDiaria=100;
                    repetir=false;
                    break;
                case 3:
                    tipoQuarto="Presidencial";
                    valorDiaria=250;
                    repetir=false;
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente");
                    repetir=true;
                    break;
            }
        }while(repetir==true);

        repetir=true;

        do{
            System.out.println("Numero de dias estadia: ");
            numeroDias=Integer.parseInt(scanner.nextLine());
            if(numeroDias<1){
                System.out.println("O numero de dias não pode ser menor que 1. tente novamente");
                repetir=true;
            }else{
                repetir=false;
            }
        }while (repetir==true);

        System.out.println("Confirma Reserva?");
        System.out.println("Nome: "+nomeHospede);
        System.out.println("Tipo de quarto: "+tipoQuarto);
        System.out.println("Numero de dias "+numeroDias);
        valorTotal=valorDiaria*numeroDias;
        System.out.println("Valor total="+valorTotal);
        System.out.println("--------------------");
        System.out.println("Digite 1 para sim ou 2 para não:");
        opcao=Integer.parseInt(scanner.nextLine());
        if(opcao==1){
            //cadastrar reserva
            novareReserva = new Reserva(nomeHospede, tipoQuarto, numeroDias, valorDiaria);
        }else{
            //cancelar operacao
            System.out.println("OPeracao cancelada pelo usuário");
            novareReserva=null;
        }
        
        return novareReserva;
    }

    public String adicionarReserva(Scanner scanner){
        String mensagem="";
        int i=this.getOcupacao();
        if(i<this.getQuartos().length){
            Reserva[] novalista = this.getQuartos();
            novalista[i]=this.criarReserva(scanner);
            i+=1;
            this.setOcupacao(i);
            mensagem="Reserva cadastrada com sucesso!";
        }else{
            mensagem="Hotel está lotado. não é possivel fazer a reserva";
        }
        return mensagem;
    }

    public String listarReservas(){
        String mensagem = "";
        Reserva [] lista=this.getQuartos();
        if(lista==null||this.getOcupacao()==0){
            mensagem="Não há reservas cadastradas para este hotel";
        }else{
            for(Reserva reserva:lista){
                if(reserva==null){
                    continue;
                }else{
                    double valorTotal =reserva.getValorDiaria()*reserva.getNumeroDias();
                    System.out.println(reserva+" - Valor total="+valorTotal);
                }
            }
            mensagem="FIm das reservas cadastradas";
        }
        return mensagem;
    }

    public void listarResultadoConsulta(Reserva[] resultado){
        for(Reserva reserva:resultado){
            System.out.println(reserva);
            System.out.println("---------");
        }
    }

    public String ordenarReservas(){
        //decrescente
        String mensagem="";
        Reserva[] lista = this.getQuartos();
        if(lista==null||this.getOcupacao()==0){
            mensagem="não há reservas cadastradas neste hotel";
        }else{
            for(int i=0;i<lista.length;i++){
                for(int j=i+1;j<lista.length;j++){
                    if(lista[j]==null){
                        continue;
                    }else if(lista[j].getNumeroDias()>lista[i].getNumeroDias()){
                        Reserva auxiliar = lista[j];
                        lista[j]=lista[i];
                        lista[i]=auxiliar;
                    }
                }
            }
            this.setQuartos(lista);
            this.listarReservas();
            mensagem="Lista de reservas ordenada com sucesso";
        }
        return mensagem;
    }

    public String buscarReservaPorNome(Scanner scanner){
        String mensagem="";
        String nome="";
        Reserva[] lista = this.getQuartos();
        Reserva[] resultado =new Reserva[this.getOcupacao()+1];
        int contador=0;
        boolean encontrou=false;
        
        if(lista==null||this.getOcupacao()==0){
            mensagem="Não há reservas cadastradas neste hotel";
        }else{
            System.out.println("Digite um nome para realizar a busca");
            nome=scanner.nextLine();
            nome=nome.toLowerCase();

            for(Reserva reserva:lista){
                String nomeReserva=reserva.getNomeHospede().toLowerCase();
                encontrou=nomeReserva.contains(nome);
                if(encontrou){
                    resultado[contador]=reserva;
                    contador++;
                }
            }

            if(contador==0){
                mensagem="a busca contendo o nome  "+nome+"não retornou resultados";
            }else{
                System.out.println("foram encontrados "+contador+" reservas contendo o nome"+nome);
                this.listarResultadoConsulta(resultado);
                mensagem="fim da busca";
            }
        }
        return mensagem;
    }
}
