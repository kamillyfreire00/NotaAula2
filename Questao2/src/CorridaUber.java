public class CorridaUber {

    private double distancia;      // Distância percorrida em km
    private int tempoEspera;       // Tempo de espera em minutos
    private double tarifaBase;     // Tarifa base da corrida
    private double fatorDemanda;   // Fator de aumento em horários de alta demanda

    public CorridaUber(double distancia, int tempoEspera, double tarifaBase, double fatorDemanda) {
        this.distancia = distancia;
        this.tempoEspera = tempoEspera;
        this.tarifaBase = tarifaBase;
        this.fatorDemanda = fatorDemanda;
    }

    public double calcularValorCorrida() {
        return (distancia * 2) + (tempoEspera * 0.5) + (tarifaBase * fatorDemanda);
    }

    public void exibirDetalhesCorrida() {
        System.out.println("Detalhes da Corrida:");
        System.out.println("Distância percorrida: " + distancia + " km");
        System.out.println("Tempo de espera: " + tempoEspera + " minutos");
        System.out.println("Tarifa base: R$" + tarifaBase);
        System.out.println("Fator de demanda: " + fatorDemanda);
        System.out.println("Valor final da corrida: R$" + String.format("%.2f", calcularValorCorrida()));
    }

    public static void main(String[] args) {
        CorridaUber corrida = new CorridaUber(10, 5, 15, 1.2);
        corrida.exibirDetalhesCorrida();
    }
}
