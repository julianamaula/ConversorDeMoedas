public class Main {
    public static void main(String[] args) {
        String apiKey = "e0d1ef3e37a6cceed8723a7c"; // chave API CurrencyFreaks

        ApiCurrencyFreaks api = new ApiCurrencyFreaks(apiKey);
        ConversorDeMoedas conversor = new ConversorDeMoedas(api);
        Menu menu = new Menu(conversor);

        menu.iniciar();
    }
}
