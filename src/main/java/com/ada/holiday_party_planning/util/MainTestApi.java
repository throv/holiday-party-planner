package com.ada.holiday_party_planning.util;

public class MainTestApi {

    public static void main(String[] args) {

        APIGoogleTranslate apiGoogleTranslate = new APIGoogleTranslate();
        APIFunTranlation apiFunTranlation = new APIFunTranlation();
        String texto = "Eu odeio todos os humanos, aqueles malditos. Irei eliminar todos";

        String mensagemGoogle = apiGoogleTranslate.translateMensage(texto,"pt-br","en");
        String mensagemFunTeste = apiFunTranlation.tranlateFun(mensagemGoogle,"minion");
        String mensagemRetorno = apiGoogleTranslate.translateMensage(texto,"en","pt-br");

        System.out.println(texto);
        System.out.println(mensagemGoogle);
        System.out.println(mensagemFunTeste);
        //System.out.println(mensagemRetorno);

    }



}
