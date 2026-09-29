package com.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        
        Scanner scanner=new Scanner(System.in);
        int option=0;
        Hotel hotel;
        boolean continuar=true;
        String resposta="";

        
            System.out.println( "BEM VINDO AO PROGRAMA GERENCIADOR DE RESERVAS" );
            System.out.println("A quantidade padrão é 10. Deseja personalizar a quantidade de quartos? ");
            System.out.println("Digite:");
            System.out.println("1 - para sim");
            System.out.println("2 - para não");
            
            option=Integer.parseInt(scanner.nextLine());

            if(option==1){
                System.out.println("Informe a quantide máxima de quartos: ");
                option=Integer.parseInt(scanner.nextLine());
                hotel=new Hotel(option);
                continuar=true;

            }else if(option==2){
                hotel=new Hotel();
                continuar=true;

            }else{
                System.out.println("opção invalida. o programa usará a quantidade padrão de 10");
                hotel=new Hotel();
                continuar=true;
            }

        do{
            System.out.println("Menu principal");
            System.out.println("Selecione uma opção Digite:");
            System.out.println("1 - Cadastrar Nova Reserva");
            System.out.println("2 - Listar Reservas Feitas");
            System.out.println("3 - Ordenar Reservas (Decrescente)");
            System.out.println("4 - Buscar Reservas por nome");
            System.out.println("9 - Finalizar progama");

            option=Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    resposta=hotel.adicionarReserva(scanner);
                    continuar=true;
                    break;

                case 2:
                    resposta=hotel.listarReservas();
                    continuar=true;
                    break;
                
                case 3:
                    resposta=hotel.ordenarReservas();
                    continuar=true;
                    break;
                
                case 4:
                    resposta=hotel.buscarReservaPorNome(scanner);
                    continuar=true;
                    break;

                case 9:
                    System.out.println("Finalizado pelo usuário");
                    continuar=false;
                    break;
            
                default:
                    System.out.println("Opção invalida, tente novamente");
                    continuar=true;
                    break;
            }
        }while(continuar==true);
    }
}
