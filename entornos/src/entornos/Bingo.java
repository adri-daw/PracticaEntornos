package entornos;

import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);

		int carton[][]=new int [3][9];
		int contador=0;

		for (int i=0; i<3; i++){// Inicializamos las filas a 0, 1 y 2 para luego hacer un do while para que no se repitan los numeros aleatorios
			for(int j=0; j<9; j++){
				carton[i][j]=contador;
			}
			contador++;
		}

		carton(carton); //Funcion donde creamos el carton
		ordenar(carton); //Funcion para ordenar la matriz por columnas
		eliminar(carton); //Funcion para eliminar los numeros hasta que queden 15
		
		System.out.println("Carton de bingo :)");


		//Mostramos la matriz
		for (int i2=0; i2<carton.length; i2++) {
			for (int i3=0; i3<carton[i2].length; i3++) {
				System.out.print(" | "); System.out.print (carton[i2][i3]); System.out.print(" | ");
			}
			System.out.println();
		}
	}


	//Crear carton de bingo

	static int[][] carton(int t[][]) {
		int numMin=0;
		int numMax=9;
		for (int i=0; i<3; i++){
			for(int j=0; j<9; j++){
				if(j==8) {
					numMax=90; //Para que el numero llegue hasta 90 (y no 89) en la ultima columna
				}
				do {
					t[i][j]=(int)(Math.random()*(numMax-numMin+1)+numMin); //Numeros al azar entre el numero minimo y el maximo
				}while(t[0][j]==t[1][j] ||// Para que no se repitan los numeros en la matriz
						t[2][j]==t[0][j] ||
						t[2][j]==t[1][j] ||
						t[i][j]==0); //Para que no salga el 0

				//Sumamos 10 a los rangos
				numMin=numMin+10;
				numMax=numMax+10;
			}
			//Inicializamos de nuevo los numeros para la siguiente fila
			numMin=0;
			numMax=9;
		}
		return t;
	}


	//Funcion para ordenar los numeros por columnas

	static int[][] ordenar(int t[][]) {
		int temporal=0;
		/*Con realizar el primer for 2 veces es necesario, ya que en el primer
		 * bucle ordena por pares la fila 2 y 1 y la 1 y 0, y el segundo bucle
		 * vuelve a ordenar por pares en caso de que el numero de la fila 0 sea
		 *  mayor que el de la fila 2*/

		for(int i=0;i<2;i++) {
			for(int j=0; j<9;j++) {
				if(t[2][j]<t[1][j]) {
					temporal=t[2][j];
					t[2][j]=t[1][j];
					t[1][j]=temporal;
				}
				if(t[1][j]<t[0][j]) {
					temporal=t[1][j];
					t[1][j]=t[0][j];
					t[0][j]=temporal;
				}
			}
		}
		return t;
	}


	//Funcion para eliminar 4 numeros de cada fila

	static int[][] eliminar(int t[][]) {
		int aleatorio=0;
		//El bucle recorre todas las filas y se hace 4 veces por fila
		for(int i=0;i<3;i++) {
			for(int j=0; j<4;j++) {
				do {
					aleatorio=(int)(Math.random()*(8-0+1));
				} while(t[i][aleatorio]==0); //Genera un indice aleatorio y le mete un 0 si no hay ya un 0
				t[i][aleatorio]=0;
			}
		}
		return t;
	}
}

