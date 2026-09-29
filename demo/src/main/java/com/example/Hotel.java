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
            mensagem="Reserva cadastrada com sucesso!";
        }else{
            mensagem="Hotel está lotado. não é possivel fazer a reserva";
        }
        return mensagem;
    }
}
