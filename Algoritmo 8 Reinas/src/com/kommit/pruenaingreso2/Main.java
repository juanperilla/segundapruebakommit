package com.kommit.pruenaingreso2;

import java.util.ArrayList;
import java.util.List;

public class Main {


	/**
	 * Representa el tablero de ajedrez.
	 * 
	 * Las posiciones que tengan el numero 0, seran
	 * posiciones sin reinas.
	 * 
	 * Las posiciones que tengan el numero 1,
	 * seran posiciones con reinas.
	 * 
	 * Las posiciones que tengan el numero 2,
	 * seran posiciones que esten en el rango
	 * de ataque de alguna reina.
	 */
	public static int [][] tablero =  {

			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 1
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 2
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 3
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 4
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 5
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 6
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 7
			{0, 0, 0 ,0 ,0 ,0 ,0 ,0 }, // 8

	};

	public static List<int[]> posicionesReinas = new ArrayList<>();

	public static void main(String[] args) {

		colocar8Reinas();
	}

	public static boolean colocar8Reinas(int c) {
		imprimirTablero();
		if(c == 8) {
			
			return true;
		}
		
		for(int n = 0; n < 8; n++) {
			if(c == 8) {
				return true;
			}
			if(colocarReina(c, n)) {

				
				if(!colocar8Reinas(++c)) {
					c--;
					desmarcarUltimaPosicionDeAtaque();
				}
			}
		}
		return false;
	}
	
	public static void colocar8Reinas() {
		colocar8Reinas(0);
	}
	
	public static boolean marcarPosicionesDeAtaque(int i, int j) {
		if(tablero[i][j] == 2)
			return false;

		// Marca las posiciones horizontales
		for(int n = 0; n < tablero.length; n++) {
			if(n != j && tablero[i][n] != 1)
				tablero[i][n] = 2;
			else if (tablero[i][n] == 1 && n != j ) {
				return false;
			}
		}
		// Marca las posiciones verticales
		for(int n = 0; n < tablero.length; n++) {
			if(n != i && tablero[n][j] != 1)
				tablero[n][j] = 2;
			else if (tablero[n][j] == 1 && n != i ) {
				return false;
			}
		}

		// Marca las posiciones diagonales
		for(int c = 0; 
				i + c < 8 || 
				i - c >= 0 ||
				j + c < 8 ||
				j - c >= 0
				;c++) {
			if(i + c < 8 && j + c < 8) {
				if(tablero[i+c][j+c] == 1) {
					return false;
				}
				tablero[i+c][j+c] = 2;
			}
			if(i - c >= 0 && j + c < 8) { 
				if(tablero[i-c][j+c] == 1) {
					return false;
				}
				tablero[i-c][j+c] = 2;
			}	
			if(i - c >= 0 && j - c >= 0) {
				if(tablero[i-c][j-c] == 1) {
					return false;
				}
				tablero[i-c][j-c] = 2;
			}
			if(i + c < 8 && j - c >= 0) {
				if(tablero[i+c][j-c] == 1) {
					return false;
				}
				tablero[i+c][j-c] = 2;
			}
		}
		tablero[i][j] = 1;
		
		return true;
	}

	public static boolean colocarReina(int i, int j) {
		
		if(marcarPosicionesDeAtaque(i, j)) {
			int[] coordenadas = {i, j};
			posicionesReinas.add(coordenadas);
			return true;
		} else {
			desmarcarPosicionesDeAtaque();
			return false;
		}
	}
	
	public static void desmarcarPosicionesDeAtaque() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = 0;
			}
		}
		
		for (int i = 0; i < posicionesReinas.size(); i++) {
			int[] coordenadas = posicionesReinas.get(i);
			marcarPosicionesDeAtaque(coordenadas[0], coordenadas[1]);
		}
	}

	public static void desmarcarUltimaPosicionDeAtaque() {
		posicionesReinas.remove(posicionesReinas.size() - 1);
		desmarcarPosicionesDeAtaque();
		
	}
	
	public static void imprimirTablero() {
		for(int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
