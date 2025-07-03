public class ConversorDeMoedas {
    private final ApiCurrencyFreaks api;

    public ConversorDeMoedas(ApiCurrencyFreaks api) {
        this.api = api;
    }

    public double converter(String from, String to, double valor) throws Exception {
        double taxa = api.obterTaxaCambio(from, to);
        return valor * taxa;
    }
}
