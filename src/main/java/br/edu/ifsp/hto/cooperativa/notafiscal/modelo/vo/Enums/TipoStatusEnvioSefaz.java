/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.Enums;

/**
 *
 * @author ht3036979
 */
public enum TipoStatusEnvioSefaz{
    
    Autorizado(1),
    Negado(2),
    NaoTransmitido(3);
    
    private final int value;
    
    TipoStatusEnvioSefaz(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoStatusEnvioSefaz fromValue(int value) {
        for (TipoStatusEnvioSefaz e : TipoStatusEnvioSefaz.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null; // Or throw an IllegalArgumentException
    }
}
