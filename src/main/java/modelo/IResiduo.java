package modelo;

/**
 * Contrato que deben cumplir todos los residuos del sistema.
 * Permite tratar cualquier tipo de residuo de forma homogénea (polimorfismo),
 * sin que el resto del sistema conozca las clases concretas.
 */
public interface IResiduo {

    /**
     * @return el identificador único del residuo (ej. "PLA-001")
     */
    String getID();

    /**
     * @return el peso del residuo en kilogramos
     */
    double getPeso();

    /**
     * @return una cadena descriptiva del tipo de residuo (ej. "Plástico")
     */
    String getTipo();
}