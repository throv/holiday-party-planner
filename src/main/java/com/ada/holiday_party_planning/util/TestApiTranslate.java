package com.ada.holiday_party_planning.util;

public class TestApiTranslate {

    public static void main(String[] args) {

        String texto = "Eu odeio todos os humanos, aqueles malditos. Irei eliminar todos";

        String mensagemGoogle = APIGoogleTranslate.translateMensage(texto,"pt-br","en");
        String mensagemFunTeste = APIFunTranlation.tranlateFun(mensagemGoogle,"yoda");
        String mensagemRetorno = APIGoogleTranslate.translateMensage(mensagemFunTeste,"en","pt-br");

        System.out.println(texto);
        System.out.println(mensagemGoogle);
        System.out.println(mensagemFunTeste);
        System.out.println(mensagemRetorno);

    }



}
